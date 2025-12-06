![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)
![MongoDB](https://img.shields.io/badge/MongoDB-Database-green)
![JWT](https://img.shields.io/badge/Security-JWT-blue)
![Maven](https://img.shields.io/badge/Build-Maven-yellow)
![License](https://img.shields.io/badge/License-MIT-purple)
#  RideShare Backend – Spring Boot & MongoDB

A complete **Ride Sharing Backend API** built using **Spring Boot, MongoDB, JWT Authentication, Input Validation, and Global Exception Handling**.  
This project supports **user registration, login, ride booking, driver acceptance, and ride completion** with role-based access.

---

## 🛠️ Tech Stack

- Java 17  
- Spring Boot 3.2.5  
- MongoDB  
- Spring Security  
- JWT Authentication  
- Hibernate Validator  
- Maven  
- Tomcat Embedded Server  

---

##  Project Folder Structure

```
src/main/java/com/Sanskriti/Rapido/
 ├── model/
 ├── repository/
 ├── service/
 ├── controller/
 ├── config/
 ├── dto/
 ├── exception/
 └── util/
```

---

##  User Roles

- **ROLE_USER** → Passenger  
- **ROLE_DRIVER** → Driver  

---

##  Features Implemented

### 🔐 Authentication
- User Registration (BCrypt password encryption)  
- User Login with JWT Token  
- Role-based access control  

### 🚕 Ride Management
- User can request a ride  
- Driver can view all pending rides  
- Driver can accept a ride  
- User/Driver can complete a ride  
- User can view their own rides  

### 🛡 Security
- JWT authentication on every secured API  
- Stateless authentication  
- Custom JWT filter  

### 🧾 Validation & Exception Handling
- DTO based validation  
- Global exception handling  
- Meaningful API error responses  

---

##  JWT Flow

```
Login → Receive JWT Token → Add Token in Header → Access Protected APIs
```

### Header Format:
```
Authorization: Bearer <your-token>
```

---

## 📌 API Endpoints

###  Public APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register user/driver |
| POST | `/api/auth/login` | Login and get JWT |

---

###  USER APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/rides` | Request a ride |
| GET | `/api/v1/user/rides` | View own rides |
| POST | `/api/v1/rides/{id}/complete` | Complete ride |

---

###  DRIVER APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/driver/rides/requests` | View pending rides |
| POST | `/api/v1/driver/rides/{id}/accept` | Accept a ride |

---

##  Sample Request Bodies

###  Register
```json
{
  "username": "john",
  "password": "1234",
  "role": "ROLE_USER"
}
```

###  Login
```json
{
  "username": "john",
  "password": "1234"
}
```

###  Create Ride
```json
{
  "pickupLocation": "Koramangala",
  "dropLocation": "Indiranagar"
}
```

---

##  Sample CURL Commands

### Register User
```bash
curl -X POST http://localhost:9000/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"john","password":"1234","role":"ROLE_USER"}'
```

### Register Driver
```bash
curl -X POST http://localhost:9000/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"driver1","password":"abcd","role":"ROLE_DRIVER"}'
```

### Login
```bash
curl -X POST http://localhost:9000/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"john","password":"1234"}'
```

### Create Ride
```bash
curl -X POST http://localhost:9000/api/v1/rides \
-H "Authorization: Bearer <TOKEN>" \
-H "Content-Type: application/json" \
-d '{"pickupLocation":"A","dropLocation":"B"}'
```

---

## 📊 Database Used

- MongoDB (Local)  
- Collections:  
  - `users`  
  - `rides`  

---

## ▶️ How to Run the Project

1. Install **Java 17**
2. Install and start **MongoDB**
3. Clone the repository  
4. Open in **IntelliJ IDEA**
5. Run:
   ```bash
   mvn spring-boot:run
   ```
6. Server runs at:
   ```
   http://localhost:9000
   ```

---
---

##  API Testing Screenshots

> Below are the screenshots of API testing done using Postman / Curl:

### 🔐 User Registration
 <img width="1600" height="1030" alt="image" src="https://github.com/user-attachments/assets/c7c1551d-83c4-4e5d-adcc-e2cf89205c37" />

### User Registered Sucessfully
 <img width="1600" height="987" alt="image" src="https://github.com/user-attachments/assets/23e54d24-e577-4fe5-b507-5543de2244bf" />

### 🔑 User Login
 <img width="1600" height="1001" alt="image" src="https://github.com/user-attachments/assets/4bb16387-3bec-4273-8d5b-3b119d54a8ce" />

### 🚕 Create Ride
 <img width="1600" height="931" alt="image" src="https://github.com/user-attachments/assets/059df771-7300-4040-b569-07c08d8168a6" />

### The token received during login will be used in authorization for creating the ride.
<img width="1600" height="931" alt="image" src="https://github.com/user-attachments/assets/5fc6180c-5534-4f32-8410-13c656019ad2" />

### 🚗 Driver View Pending Rides
 <img width="1600" height="931" alt="image" src="https://github.com/user-attachments/assets/dca9bec9-16b7-4a7c-ac31-ccc53278a293" />

###  Driver Accept Ride
 <img width="1600" height="931" alt="image" src="https://github.com/user-attachments/assets/48390924-aed7-4527-8fa3-47590295cef4" />

### 🏁 Complete Ride
 <img width="1600" height="931" alt="image" src="https://github.com/user-attachments/assets/00ed32a9-cbbf-4dcd-a565-6f1e760f7bd9" />

### 📄 User Ride History
<img width="1600" height="931" alt="image" src="https://github.com/user-attachments/assets/60bc9a97-f279-4454-affb-8082108d8950" />

---


##  Output Status

-  User Registration Working  
-  JWT Login Working  
-  Ride Request Working  
-  Driver Accept Working  
-  Ride Completion Working  
-  Role Authorization Working  

---

## 📄 Author

**Sanskriti**  
Backend Developer – Spring Boot & MongoDB  
GitHub: https://github.com/Sanskriti10247
