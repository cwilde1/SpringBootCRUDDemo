````markdown
# ClintWilde-DocMe360

A lightweight Spring Boot application using SQLite, Docker, and REST APIs to manage Templates and Notifications.

---

## 📦 Technologies Used

- Spring Boot  
- SQLite3  
- Docker  
- JPA/Hibernate  
- Postman  
- JUnit  

---

## 🚀 Getting Started

This project runs entirely inside Docker.

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

Access the application at:  
📍 `http://localhost:8080`

---

## 🧪 API Testing

Use the provided Postman collection:  
👉 **[Download Postman Collection](https://github.com/cwilde1/ClintWilde-DocMe360/blob/main/postman/ClintWilde-DocMe360.postman_collection.json)**

1. Import the collection into Postman.
2. Create a Template.
3. Use its ID to send a Notification.

---

## 📌 Step 1: Create a Template

```json
{
  "body": "Hello, (personal). How are you today?"
}
```

`POST /template`  
Content-Type: `application/json`

---

## 🔔 Step 2: Create a Notification

```json
{
  "phoneNumber": "1234567890",
  "template": { "id": 1 },
  "personalization": "Bob"
}
```

`POST /notification`  
Content-Type: `application/json`

---

## 📚 API Endpoints

| Method | URL                  | Description                                            |
| ------ | -------------------- | ------------------------------------------------------ |
| GET    | `/template`          | Get all templates                                      |
| GET    | `/template/{id}`     | Get a specific template                                |
| POST   | `/template`          | Create a template                                      |
| PUT    | `/template/{id}`     | Update a template                                      |
| PATCH  | `/template/{id}`     | Partially update a template                            |
| DELETE | `/template/{id}`     | Delete a template                                      |
| GET    | `/notification`      | Get all notifications                                  |
| GET    | `/notification/{id}` | Get a specific notification                            |
| POST   | `/notification`      | Create a notification                                  |

---

## ✅ Unit Testing

```bash
./mvnw test
```
````
