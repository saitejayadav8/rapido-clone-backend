# 🚖 Rapido Clone Backend

A production-style backend application that simulates the core functionalities of a ride-booking platform like Rapido using Java Spring Boot Microservices.

---

# 📌 Project Overview

This project is designed to demonstrate how a real-world ride booking platform works using a scalable microservices architecture.

The backend includes user authentication, ride booking, driver management, notifications, analytics, search, and event-driven communication using Kafka.

---

# 🏗️ Architecture

- Microservices Architecture
- API Gateway
- Service Discovery
- Event-Driven Communication (Kafka)
- PostgreSQL Database
- Redis Cache
- Elasticsearch
- Docker & Docker Compose

---

# 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- PostgreSQL
- Redis
- Apache Kafka
- Elasticsearch
- Docker
- Maven
- Git & GitHub

---

# 📦 Microservices

- API Gateway
- Authentication Service
- User Service
- Driver Service
- Ride Service
- Search Service
- Notification Service
- Analytics Service

---

# ✨ Features

## Authentication
- User Registration
- User Login
- JWT Authentication
- Role-Based Authorization

## Ride Management
- Book Ride
- Accept Ride
- Start Ride
- Complete Ride
- Ride Status Tracking

## Driver Management
- Driver Registration
- Driver Availability
- Driver Location Updates

## Search Service
- Nearby Driver Search
- Geospatial Search
- Elasticsearch Integration

## Notification Service
- Ride Notifications
- Kafka Event Consumer
- Event-Based Messaging

## Analytics Service
- Ride Analytics
- Payment Analytics
- Dashboard Metrics
- Business Reports

---

# 📂 Project Structure

```
rapido-clone-backend
│
├── api-gateway
├── auth-service
├── user-service
├── driver-service
├── ride-service
├── search-service
├── notification-service
├── analytics-service
├── docker-compose.yml
└── README.md
```

---

# ⚙️ How to Run

## Clone Repository

```bash
git clone https://github.com/TejashwarRao/rapido-clone-backend.git
```

## Go to Project

```bash
cd rapido-clone-backend
```

## Start Docker

```bash
docker-compose up -d
```

## Run Services

```bash
mvn spring-boot:run
```

---

# 🔄 System Flow

1. User logs in using JWT Authentication.
2. User books a ride.
3. Ride Service searches nearby drivers.
4. Driver accepts the ride.
5. Kafka publishes ride events.
6. Notification Service sends notifications.
7. Analytics Service stores ride data.
8. Search Service updates driver locations.

---

# 📈 Current Progress

✅ Authentication Service Completed

✅ User Service Completed

✅ Driver Service Completed

✅ Ride Service Completed

✅ Kafka Integration Completed

✅ Search Service with Elasticsearch Completed

✅ Notification Service Completed

✅ Analytics Platform Completed

---

# 🎯 Learning Outcomes

Through this project, I learned:

- Microservices Architecture
- REST API Development
- JWT Authentication
- Apache Kafka
- PostgreSQL
- Redis
- Elasticsearch
- Docker
- Event-Driven Architecture
- Analytics Dashboard Design
- Git & GitHub Workflow

---

# 📌 Future Enhancements

- Payment Gateway Integration
- Kubernetes Deployment
- CI/CD Pipeline
- Monitoring with Prometheus & Grafana
- Distributed Tracing
- Machine Learning for Ride Prediction

---

# 👨‍💻 Author

**SAITEJA**

Java Backend Developer

GitHub:
https://github.com/saitejayadav8

---

# ⭐ Project Status

**Completed (Learning & Development Project)**

This project demonstrates a scalable backend architecture for a ride-booking platform using modern Java technologies and microservices.
