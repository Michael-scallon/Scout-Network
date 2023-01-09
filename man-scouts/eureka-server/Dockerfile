FROM openjdk:8-jre-alpine
COPY target/eureka-server-0.0.1-SNAPSHOT.jar/ eureka-server.jar
RUN apk update && apk add bash
RUN apk add --no-cache bash
CMD ["java", "-jar", "eureka-server.jar"]