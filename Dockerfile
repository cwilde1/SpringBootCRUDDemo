# Use a minimal base image with Java 17
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file (make sure it's built already)
COPY target/*.jar app.jar

# Ensure the data directory exists for SQLite persistence
RUN mkdir -p /app/data

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
