# Start from a Maven image to build the project
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

# Use system Maven instead of ./mvnw
RUN mvn test
RUN mvn clean package -DskipTests

# Use lightweight runtime image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
RUN mkdir -p /app/data
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
