#stage build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package

#stage run
FROM openjdk:17-alpine AS runing
COPY --from=build /app/target/spring-boot-docker.jar .
EXPOSE 8080
CMD ["java", "-jar", "spring-boot-docker.jar"]