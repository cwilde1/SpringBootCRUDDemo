
# DocMe360

A Spring Boot web application for managing Notifications and Templates using SQLite database.

---

## Table of Contents

- [Project Overview](#project-overview)  
- [Technologies Used](#technologies-used)  
- [Prerequisites](#prerequisites)  
- [Setup and Run Locally](#setup-and-run-locally)  
- [Running with Docker](#running-with-docker)  
- [API Endpoints](#api-endpoints)  
- [Testing](#testing)  
- [Design Decisions](#design-decisions)  
- [Assumptions](#assumptions)  
- [References](#references)  

---

## Project Overview

This application provides REST endpoints to manage Notifications and Templates, storing data in an embedded SQLite database. Notifications personalize Template messages by substituting placeholders.

---

## Technologies Used

- Java 17
- Spring Boot 2.7.x
- SQLite 3
- Docker
- JUnit (unit testing)
- Lombok (for getters/setters)
- SLF4J + Logback (logging)

---

## Prerequisites

- Java JDK 17 (if running locally without Docker)
- Maven (for building the project locally)
- Docker (optional, for containerized deployment)

---

## Setup and Run Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/cwilde1/ClintWilde-DocMe360.git
   cd ClintWilde-DocMe360
   ```

2. Build the project using Maven:

   ```bash
   mvn clean package
   ```

3. Run the application:

   ```bash
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

4. The application runs by default on [http://localhost:8080](http://localhost:8080)

---

## Running with Docker

1. Build the Docker image:

   ```bash
   docker build -t docme360-app .
   ```

2. Run the Docker container:

   ```bash
   docker run -d -p 8080:8080 --name docme360-container docme360-app
   ```

3. Access the app at [http://localhost:8080](http://localhost:8080)

4. To stop the container:

   ```bash
   docker stop docme360-container
   ```

5. To remove the container:

   ```bash
   docker rm docme360-container
   ```

---

## API Endpoints

### Notifications

| Method | Endpoint             | Description                                   | Status Code |
| ------ | -------------------- | --------------------------------------------- | ----------- |
| GET    | `/notification`      | Get all notifications                         | 200         |
| GET    | `/notification/{id}` | Get a notification by ID (with `content`)     | 200         |
| POST   | `/notification`      | Create a new notification                     | 201         |

### Templates

| Method | Endpoint         | Description               | Status Code |
| ------ | ---------------- | ------------------------- | ----------- |
| GET    | `/template`      | Get all templates         | 200         |
| GET    | `/template/{id}` | Get a template by ID      | 200         |
| POST   | `/template`      | Create a new template     | 201         |
| PATCH  | `/template/{id}` | Update a template         | 200         |
| DELETE | `/template/{id}` | Delete a template         | 204         |

---

## Testing

- Unit tests are included and can be run with:

  ```bash
  mvn test
  ```

---

## Design Decisions

- Used SQLite for simplicity and ease of local setup.
- Spring Data JPA abstracts database interactions.
- Custom SQLite dialect to support specific SQL nuances.
- Docker enables zero-dependency deployment.
- Logging via SLF4J for easy debugging.

---

## Assumptions

- No authentication required as per project instructions.
- Notification `content` field computed dynamically when fetching by ID.
- Template body max length set to 1000 characters.
- Notification personalization can be nullable.

---

## References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [SQLite Documentation](https://sqlite.org/index.html)
- [Docker Documentation](https://docs.docker.com/)
- [OpenAPI Specification](https://www.openapis.org/)
- [Lombok](https://projectlombok.org/)

---

## Contact

For any questions, please reach out to Clint at clint.wilde@gmail.com.

