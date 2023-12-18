FROM gradle:7.4.0-jdk17 AS build
COPY . .
RUN gradle clean build -x test
FROM eclipse-temurin:17-alpine
COPY --from=build /Baby-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
