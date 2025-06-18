# Start from official OpenJDK image
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the jar built by Maven into the container
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]