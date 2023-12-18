# Use the official Gradle image as a base image
FROM gradle:6.8-jdk11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the working directory
COPY build.gradle .
COPY src ./src

# Build the application
RUN gradle build

# Create a lightweight image with only the JRE and the built application
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/build/libs/my-app.jar ./app.jar

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]
