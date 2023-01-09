FROM openjdk:8-jre-alpine
COPY target/broker-0.0.1.jar /broker.jar
COPY wait-for-launch.sh wait-for-launch.sh
RUN apk update && apk add bash
RUN apk add --no-cache bash
CMD ["bash", "wait-for-launch.sh", "broker.jar", "300"]