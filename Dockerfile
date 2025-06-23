# Start from a Maven image to build the project
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

# Run tests during the build
RUN ./mvnw test

# Then package the application
RUN ./mvnw clean package -DskipTests

# Use a smaller JRE-only image for the runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]