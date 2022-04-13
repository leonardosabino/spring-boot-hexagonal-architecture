FROM maven:3.8.1-openjdk-11 AS build
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN mvn clean install

FROM openjdk:11-jdk-slim-buster
EXPOSE 8080 9090
RUN mkdir /app
COPY --from=build /src/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app/app.jar"]

