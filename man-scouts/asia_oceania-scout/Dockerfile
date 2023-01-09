FROM openjdk:8-jre-alpine
COPY target/asia_oceania-scout-0.0.1-SNAPSHOT.jar/ asia_oceania-scout.jar
COPY wait-for-launch.sh wait-for-launch.sh
RUN apk update && apk add bash
RUN apk add --no-cache bash
CMD ["bash", "wait-for-launch.sh", "asia_oceania-scout.jar", "90"]