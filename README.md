Here's your updated `README.md` with a **direct link to the Postman collection** placed near the top for easy access right after running the app:

---

````markdown
# ClintWilde-DocMe360

A Spring Boot application using SQLite, Docker, and REST APIs to manage Templates and Notifications.

---

## 🚀 Quick Start

After compiling and deploying the application, use the Postman collection to test API endpoints easily.

👉 **[Download the Postman Collection](./postman/ClintWilde-DocMe360.postman_collection.json)**

1. Compile and deploy the app using Docker (see steps below).
2. Import the collection into Postman.
3. Use the provided requests to create Templates and Notifications.

---

## 📦 Technologies Used

- Spring Boot  
- SQLite3  
- Docker  
- JPA/Hibernate  
- Postman (for testing)  
- JUnit (unit tests)  

---

## 🔧 How to Run (No Local Java or Maven Required)

You only need Docker installed. Everything else runs inside the container.

### 1. Clone this repository

```bash
git clone https://github.com/cwilde1/ClintWilde-DocMe360.git
cd ClintWilde-DocMe360
```

### 2. Build the Docker image

```bash
docker build -t docme360-app .
```

### 3. Run the Docker container

```bash
docker run -p 8080:8080 --name docme360-container docme360-app
```

The app is now accessible at:
📍 `http://localhost:8080`

---

## 📌 First Step: Create a Template

Before creating notifications, you **must create at least one Template**, because Notifications require a valid `template_id`.

### Template `POST` JSON example

```json
{
  "body": "Hello, (personal). How are you today?"
}
```

### Endpoint

`POST /template`  
Content-Type: `application/json`

---

## 🔔 Notification `POST` JSON example

Once a Template is created, you can reference its ID in the notification request:

```json
{
  "phoneNumber": "1234567890",
  "template": { "id": 1 },
  "personalization": "Bob"
}
```

### Endpoint

`POST /notification`  
Content-Type: `application/json`

---

## 🔌 Full API Endpoints

| Method | URL                  | Description                                            |
| ------ | -------------------- | ------------------------------------------------------ |
| GET    | `/template`          | Get all templates                                      |
| GET    | `/template/{id}`     | Get a specific template                                |
| POST   | `/template`          | Create a template                                      |
| PUT    | `/template/{id}`     | Update a template                                      |
| PATCH  | `/template/{id}`     | Partially update a template                            |
| DELETE | `/template/{id}`     | Delete a template                                      |
| GET    | `/notification`      | Get all notifications                                  |
| GET    | `/notification/{id}` | Get a specific notification (includes `content` field) |
| POST   | `/notification`      | Create a notification                                  |

---

## 🧪 Testing

Use Postman (collection linked at the top) or any API tool to test the endpoints.

To run unit tests (optional):

```bash
./mvnw test
```
