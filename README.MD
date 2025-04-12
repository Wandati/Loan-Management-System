# Loan Management System (LMS)

This project implements a simplified **Loan Management System (LMS)** as part of a technical assessment. It offers a complete backend solution with RESTful APIs for customer onboarding, loan request and status tracking, and integrates (mocked) with an external scoring engine. The application is containerized using Docker for easy setup and deployment.

---

## 📚 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Technology Stack](#technology-stack)
- [Setup and Installation](#setup-and-installation)
- [Environment Configuration](#environment-configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Drawbacks and Limitations](#drawbacks-and-limitations)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

## 📌 Project Overview

The Loan Management System is designed to handle the complete lifecycle of a loan—from customer registration to loan processing and tracking. It simulates an interaction with an external scoring engine and offers a Dockerized setup for seamless local development and deployment.

This system supports:

- Customer onboarding and subscription.
- Loan request creation and duplicate prevention.
- Mock scoring engine simulation to determine loan eligibility.
- Loan status retrieval.
- Docker-based deployment and Postgres integration.

---

## ✅ Features

### 👤 Customer Subscription
Allows a new customer to register into the system using a unique customer number.

### 💰 Loan Request
Enables a customer to submit a loan request, specifying the desired loan amount.

### 🔁 Duplicate Loan Prevention
Prevents the same customer from submitting multiple loan applications simultaneously.

### 🔍 Loan Status Tracking
Enables a customer to check the status of their loan request at any time.

### 🧪 Mock Scoring Engine
Simulates an external scoring service to evaluate loan eligibility based on dummy transaction data.

### 📦 Dockerized Setup
Includes `Dockerfile` and `docker-compose.yml` to easily build and run the system with PostgreSQL.

### ⚠️ Exception Handling
Implements structured exception classes to handle common errors like duplicate requests or invalid customers.

---

## 🏛️ System Architecture

The system follows a **layered architecture**:

1. **Controller Layer**
   - Handles all REST API requests.
   - Maps incoming HTTP requests to corresponding service methods.

2. **Service Layer**
   - Contains business logic such as loan processing, customer registration, scoring engine interactions.

3. **Repository Layer**
   - Uses Spring Data JPA to interact with PostgreSQL.
   - Manages Customer and Loan entities.

4. **Model Layer**
   - Defines the domain objects like `Customer` and `Loan`.

5. **Mock Integration Layer**
   - Simulates the scoring engine and transaction history provider.

---

## 🛠️ Technology Stack

| Technology        | Description                              |
|-------------------|------------------------------------------|
| Java 17           | Main programming language                |
| Spring Boot 3.x   | Backend framework                        |
| Spring Data JPA   | ORM layer                                |
| PostgreSQL        | Relational database                      |
| Docker            | Containerization                         |
| Docker Compose    | Multi-container orchestration            |
| Maven             | Build and dependency management tool     |

---

## 🧰 Setup and Installation

### 📋 Prerequisites

Ensure the following tools are installed:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### 📥 Clone the Repository

```bash
git clone <repository_url>
cd <project_directory>
```

---

## ⚙️ Environment Configuration

Create a `.env` file in the root of the project:

```dotenv
DB_NAME=loan_db
DB_USERNAME=postgres
DB_PASSWORD=your_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

Replace values with your actual database credentials.

---

## 🚀 Running the Application

Use Docker Compose to start both the application and the PostgreSQL database:

```bash
docker-compose up --build -d
```

Once running, access the application at:

```
http://localhost:8080
```

To stop the application:

```bash
docker-compose down
```

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/subscribe?customerNumber={customerNumber}` | Registers a new customer |
| `POST` | `/api/loanRequest?customerNumber={customerNumber}&amount={amount}` | Requests a new loan |
| `GET`  | `/api/loanStatus?customerNumber={customerNumber}` | Checks the current loan status |
| `GET`  | `/api/transaction-data?customerNumber={customerNumber}` | Provides mocked transaction data |

---

## 🧪 Testing

A bash script is provided to simulate an end-to-end test:

### 🧾 Test Script Usage

1. Make the script executable:

```bash
chmod +x testscript.sh
```

2. Run the script:

```bash
./testscript.sh
```

### ✅ What It Does

- Registers a new customer.
- Submits a loan request.
- Checks the loan status.
- Validates prevention of duplicate loan requests.

---

## ⚠️ Drawbacks and Limitations

- **Mocked Services:** The scoring engine and core banking integrations are currently mocked. Real implementations would require secure SOAP/REST integration.
- **No Security:** Lacks authentication/authorization (e.g., OAuth2, JWT).
- **Minimal Validation:** Input validation is basic and should be hardened for production.
- **Limited Error Handling:** Needs more granular exception flows and logging.
- **Single Node Deployment:** Not optimized for high availability or load balancing.
- **Missing Monitoring:** No metrics or health checks are included.

---

## 🚧 Future Improvements

- 🔐 **Add Security** – Implement authentication and authorization (JWT or OAuth2).
- 📉 **Real Scoring Engine Integration** – Replace mock scoring with live scoring API.
- 🧼 **Improve Validation** – Enhance request validations with annotations and custom logic.
- 🧪 **Expand Testing** – Add unit and integration test coverage.
- 📈 **Monitoring and Metrics** – Integrate Prometheus, Grafana, or Spring Boot Actuator.
- ☁️ **Cloud Readiness** – Prepare for deployment to cloud environments like AWS or GCP.
- ♻️ **Transactional Safety** – Improve transaction management with isolation and rollback support.

---

## 👤 Author

**Marvin Wandati**

Feel free to reach out with any questions or suggestions!


