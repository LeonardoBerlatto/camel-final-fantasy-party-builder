FROM openjdk:11-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENV SPRING_PROFILES_ACTIVE="docker"

ENTRYPOINT ["java","-jar","/app.jar"]