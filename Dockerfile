FROM gradle:7.3.3-jdk17 AS build
COPY . .
RUN gradle clean build -x test
FROM eclipse-temurin:17-alpine
COPY --from=build /target/Baby-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
