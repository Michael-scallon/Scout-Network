FROM openjdk:8-jre-alpine
COPY target/field-scout-0.0.1-SNAPSHOT.jar/ field-scout.jar
COPY wait-for-launch.sh wait-for-launch.sh
RUN apk update && apk add bash
RUN apk add --no-cache bash
CMD ["bash", "wait-for-launch.sh", "field-scout.jar", "360"]