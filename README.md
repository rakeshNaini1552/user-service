
# User Service

## 📌 Overview
User Service is responsible for:
- **User registration and authentication**
- **JWT token generation & validation**
- Provides secured APIs for user details
- Registers itself with **Eureka Server**
- Can be routed via **API Gateway**

---

## 🛠 Tech Stack
- Spring Boot
- Spring Security + JWT
- Spring Data JPA (optional database)
- Eureka Client

---

## ⚙️ Configuration
📄 **application.yml**
```yaml
server:
  port: 8081

spring:
  application:
    name: USER-SERVICE

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
🚀 How to Run
Ensure Eureka Server is running at http://localhost:8761

Run UserServiceApplication

Access service via http://localhost:8081

🔑 APIs
POST /auth/register → Create new user(Not yet added)

POST /auth/login → Authenticate & receive JWT

GET /users/{id} → Get user details (JWT required)

For secured APIs, include:

Authorization: Bearer <JWT_TOKEN>
🔗 Dependencies
Requires Eureka Server for service discovery

Routed via API Gateway (lb://USER-SERVICE)

✅ How It Fits
Provides authentication for Order Service

JWT validated by JwtRequestFilter before controller execution
