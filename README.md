# Thats So Maven - Distributed System for Scouting Players.

The distributed system developed by That's So Maven is intended for use by professional soccer teams to get up-to-date stats on players from around the world.

Our service employs scouts across different continents to maintain a local register of player stats (e.g. goals, appearances). Each scout runs a RESTful service with full CRUD endpoints, allowing player data to be updated by field scouts from the region. The data maintained by these scouts is then queried by a centralised broker. The broker also runs a RESTful endpoint, with a browser-based client being used to query the data available and be consumed by the end user. Only authenticated users can access the service, with an authentication service preventing unknown users from accessing the data.

To address the need for scale, the service makes use of a Netflix Eureka server. Scouts register themselves with Eureka, with the broker then retrieving the scouts' socket addresses from Eureka. This allows scouts to be added or removed from the services, without the need to manually update socket addresses at the broker.

As for fault tolerance, the broker utilises a Redis server to cache data retrieved from the scouts for an extended period of time (60 seconds in this repo for demonstration purposes). This ensures that data is available for a window, even if there are issues at one or more scouts in retrieving their data. Redis also helps performance. With a significant amount of player updates occurring on match days, the broker's cache delivers available data of sufficient accuracy, without the need for lengthy query times between scouts located around the globe.

- [Project Video](https://drive.google.com/file/d/1dOPES4n_6v73e0Pn84WJl3Te-EoEA3c7/view?usp=sharing)
- [Project Report](https://gitlab.com/comp30220/2022/thats-so-maven/-/blob/main/thats-so-maven-project-report.pdf)

## Getting Started

### Pre-requisites

To run the service, the following must be installed on your machine:

- Docker (Linux) / Docker Desktop (Windows, Mac OS)
- Maven
- Git

### Installation Instructions

To stand up the project locally, do the following:

1. Clone the repo by running `git clone https://gitlab.com/comp30220/2022/thats-so-maven.git`
2. Navigate to the downloaded `thats-so-maven` repo in your terminal and run `cd man-scouts`.
3. Run `mvn install` to generate the required `.jar` files for each module.
4. Run `cd ..` to return back to root folder of `thats-so-maven`.
5. Run `docker compose up` to stand up the images listed in `docker-compose.yml` as containers.

An optional module for demonstration purposes `field-scout` can also be stood up in the container network. This runs some updates on a single player to demonstrate how the broker utilises caching. To stand up this image, run steps 1-4 above, then run `docker compose --profile demo up`

### Using the Service

To access the service, do the following:

1.  Open your preferred browser, then navigate to `localhost:3000`.
2.  To access the service, you must create an account or login. To do this, click the 'signup' or 'login' button in the toolbar.
3.  Enter your user name and password, then click 'Sign In'.

You will now be brought to the authenticated section of the client and can search for players which meet your desired criteria.

### Note on Standing-up with Docker

Please allow anywhere between 5-10 minutes for `docker-compose` to stand-up all containers. Three images from Docker Hub (mysql-server, redis and open-jdk) must be downloaded on the first run, with each image being 100+ MB in size.

Some modules in the `man-scouts` directory contain a `wait-for-launch.sh` script. This delays the execution of these modules' `.jar` files until other modules they depend on have been completely stood up. The sequence of image execution is as follows:

- Launched immediately: mysql-server, api, web, redis, eureka-server
- Launched after delay of 90 seconds: euro-scout, africa-scout, americas-scout, asia_oceania-scout (all depend on mysql-server, eureka-server)
- Launched after delay of 5 minutes: broker (depends on scouts, eureka-server, redis)
- Launched after delay of 6 minutes (if demo profile active): field-scout

While the timing of these images has been orchestrated as generously as possible, older devices may not stand-up dependent images in time. To troubleshoot this, you can extend the delay for a particular module or modules. For example, if the broker is failing to launch due to a delay in standing up the scouts, the following can be done to resolve the issue:

1. Navigate to the `broker` module in your preferred IDE.
2. Open the Dockerfile. Change the last argument in ln 6 to be a different unit of time (e.g. `CMD ["bash", "wait-for-launch.sh", "broker.jar", "600"]`
3. From a terminal, navigate to the `broker` directory and run `mvn clean install`.
4. Remove the existing Docker image for `broker` by running `docker rmi thats-so-maven-broker` in the terminal (from any directory).
5. In the terminal, run `cd ../..` to navigate to the root directory. Then, stand up the container network by running `docker compose up`.

## Authors

Developed by Bryan Agar, Jack Mulligan and Michael Scallon (thats-so-maven) for the final project of COMP41720 Distributed Systems.
