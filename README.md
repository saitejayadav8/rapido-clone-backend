# Rapido Clone Backend - Auth Service

## Project Overview
This project is part of a production-ready microservices backend system for a Rapido-like ride booking platform.

Current service implemented:
- Auth Service

## Tech Stack
- Java 17
- Spring Boot 3.5.14
- PostgreSQL
- Docker
- Maven
- Spring Security
- REST APIs

## Features Implemented
- Spring Boot backend setup
- PostgreSQL database connection
- Dockerized PostgreSQL setup
- REST Health API
- Spring Security configuration
- Maven project structure
- GitHub integration

## Project Structure

src/main/java/com/rapido/auth_service/

├── controller  
├── config  
├── service  
├── repository  
├── entity  
├── dto  
├── security  
├── exception  
└── util  

## API Endpoint

### Health Check API

```http
GET /health
