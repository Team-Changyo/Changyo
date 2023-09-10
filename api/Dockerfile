FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/api-1.0.jar ShinhanApi.jar

ENTRYPOINT ["java", "-jar", "ShinhanApi.jar"]
