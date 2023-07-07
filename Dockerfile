FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} capitole-challenge-0.0.1.jar
ENTRYPOINT ["java","-jar","/capitole-challenge-0.0.1.jar"]