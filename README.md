---

````markdown
# ClintWilde-DocMe360

A lightweight Spring Boot application using SQLite, Docker, and REST APIs to manage Templates and Notifications.

---

## 📦 Technologies Used

- Spring Boot  
- SQLite3  
- Docker  
- JPA/Hibernate  
- Postman (for API testing)  
- JUnit (for unit testing)  

---

## 🚀 Getting Started

This project runs entirely inside Docker—no need to install Java or Maven locally.

### 1. Clone the Repository

```bash
git clone https://github.com/cwilde1/ClintWilde-DocMe360.git
cd ClintWilde-DocMe360
```

### 2. Build the Docker Image

```bash
docker build -t docme360-app .
```

### 3. Run the Docker Container

```bash
docker run -p 8080:8080 --name docme360-container docme360-app
```

The application will be accessible at:  
📍 `http://localhost:8080`

---

## 🧪 API Testing with Postman

To test the API, use the provided Postman collection:

👉 **[Download Postman Collection](./postman/ClintWilde-DocMe360.postman_collection.json)**

### Steps:

1. Import the collection into Postman.
2. Start by creating a Template (required for Notifications).
3. Use the included sample requests to send Notifications and query data.

---

## 📌 Step 1: Create a Template

You must create a Template before sending Notifications, since each Notification requires a valid `template_id`.

### Template `POST` Example

```json
{
  "body": "Hello, (personal). How are you today?"
}
```

**Endpoint:**  
`POST /template`  
Content-Type: `application/json`

---

## 🔔 Step 2: Create a Notification

Once a Template is created, you can reference its ID to send a Notification.

### Notification `POST` Example

```json
{
  "phoneNumber": "1234567890",
  "template": { "id": 1 },
  "personalization": "Bob"
}
```

**Endpoint:**  
`POST /notification`  
Content-Type: `application/json`

---

## 📚 Full API Endpoints

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

## ✅ Unit Testing (Optional)

To run backend unit tests:

```bash
./mvnw test
```
