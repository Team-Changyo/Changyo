FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/changyo-1.0.jar Changyo.jar

ENTRYPOINT ["java", "-jar", "Changyo.jar"]
