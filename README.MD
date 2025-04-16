# Loan Management System (LMS)

This project implements a simplified **Loan Management System (LMS)** with a complete backend solution offering RESTful APIs for customer onboarding, loan request and status tracking, and integrates (mocked) with an external scoring engine. The application is containerized using Docker for easy setup and deployment.

---

## 📚 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Technology Stack](#technology-stack)
- [Setup and Installation](#setup-and-installation)
- [Environment Configuration](#environment-configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Drawbacks and Limitations](#drawbacks-and-limitations)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

## 📌 Project Overview

The Loan Management System is designed to handle the complete lifecycle of a loan—from customer registration to loan processing and tracking. It simulates an interaction with an external scoring engine and offers a Dockerized setup for seamless local development and deployment.

---

## ✅ Features

- Customer onboarding and subscription
- Loan request creation and duplicate prevention
- Loan status tracking
- Resilient scoring engine simulation via `WebClient` and `resilience4j`
- Full Docker support
- Structured exception handling and logging
- API documentation with Swagger UI

---

## 🏛️ System Architecture

- **Controller Layer** – Handles API requests.
- **Service Layer** – Contains business logic.
- **Repository Layer** – Interfaces with PostgreSQL.
- **Model Layer** – Domain entities: `Customer`, `Loan`.
- **Integration Layer** – Simulated external service calls with retries using `WebClient`.

---

## 🛠️ Technology Stack

| Technology        | Description                              |
|-------------------|------------------------------------------|
| Java 17           | Main programming language                |
| Spring Boot 3.x   | Backend framework                        |
| Spring Data JPA   | ORM layer                                |
| WebClient         | Reactive HTTP client                     |
| Resilience4j      | Retry, rate limiting, and circuit breaker|
| PostgreSQL        | Relational database                      |
| Docker            | Containerization                         |
| Docker Compose    | Orchestration                            |
| Maven             | Dependency management                    |
| Swagger UI        | API documentation                        |

---

## 🧰 Setup and Installation

### Prerequisites

- Docker + Docker Compose

### Clone and Run

```bash
git clone <repository_url>
cd <project_directory>
docker-compose up --build -d
```

Access the app at: `http://localhost:8080`

Access Swagger UI documentation at: `http://localhost:8080/swagger-ui/index.html`

---

## ⚙️ Environment Configuration

Create a `.env` file with:

```env
DB_NAME=loan_db
DB_USERNAME=postgres
DB_PASSWORD=your_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

---

## 🚀 Running the Application

```bash
docker-compose up --build -d
```

To stop:

```bash
docker-compose down
```

---

## 📡 API Documentation

The API is documented using Swagger UI and is accessible at:

```
http://[ip-address]:8080/swagger-ui/index.html
```

### 1. 📥 Subscribe Customer

**POST** /api/subscribe-params?customerNumber={customerNumber}&name={customerName}


Registers a customer into the system.

#### Query Parameters

| Name            | Type   | Required | Description           |
|-----------------|--------|----------|-----------------------|
| `customerNumber`| String | ✅       | Unique customer ID    |
| `customerName`  | String | ✅       | Unique customer name  | 


#### Example Request

```http
POST /api/subscribe-params?customerNumber=318411216&name=TestUser
```

#### Success Response

```json
{
  "message": "Customer subscribed successfully",
  "customerNumber": "318411216",
  "customerName": "TestUser"
}

```

**Status:** `201 Created`

#### Error Responses

- `400 Bad Request`: Missing or malformed parameter.
- `409 Conflict`: Customer already exists.

---

### 2. 💸 Request Loan

**POST** `/api/loanRequest?customerNumber={customerNumber}&amount={amount}`

Initiates a loan request for a customer.

#### Query Parameters

| Name            | Type    | Required | Description           |
|-----------------|---------|----------|-----------------------|
| `customerNumber`| String  | ✅       | Customer ID           |
| `amount`        | Decimal | ✅       | Loan amount requested |

#### Example Request

```http
POST /api/loanRequest?customerNumber=318411216&amount=1500

```

#### Success Response

```json
{
  "loanId": "1",
  "customerNumber": "318411216",
  "amount": 1500,
  "status": "PENDING"
}

```

**Status:** `201 Created`

#### Error Responses

- `404 Not Found`: Customer does not exist.
- `409 Conflict`: Existing active loan already in progress.

---

### 3. 🔍 Get Loan Status

**GET** `/api/loanStatus?customerNumber={customerNumber}`

Returns the status of the customer's most recent loan.

#### Query Parameters

| Name            | Type   | Required | Description           |
|-----------------|--------|----------|-----------------------|
| `customerNumber`| String | ✅       | Customer ID           |

#### Example Request

```http
GET GET /api/loanStatus?customerNumber=318411216

```

#### Success Response

```json
{
  "loanId": "1",
  "status": "APPROVED"
}


```

**Status:** `200 OK`

#### Error Response

- `404 Not Found`: No loan found for the customer.

---

### 4. 📊 Get Transaction Data

**GET** `/api/transaction-data?customerNumber={customerNumber}`

Provides dummy transaction data from the mock scoring engine.

#### Query Parameters

| Name            | Type   | Required | Description           |
|-----------------|--------|----------|-----------------------|
| `customerNumber`| String | ✅       | Customer ID           |

#### Example Response

```json
{
  "customerNumber": "318411216",
  "transactions": [
    {
      "date": "2024-04-01",
      "amount": 1000,
      "type": "credit"
    },
    {
      "date": "2024-04-03",
      "amount": 500,
      "type": "debit"
    }
  ]
}


```

**Status:** `200 OK`

---

## 🧪 Testing

```bash
chmod +x testscript.sh
./testscript.sh
```

The script performs:

- Subscription
- Loan request
- Status check
- Duplicate prevention validation

---

## ⚠️ Drawbacks and Limitations

- Scoring engine and bank integrations are mocked due to API unreachability
- No security layer (JWT/OAuth)
- No observability tools (metrics/logs visualization)

---

## 🚧 Future Improvements

- ✅ Add auth layer (JWT, OAuth2)
- 🔁 Real external API integration
- 📊 Observability with Prometheus/Grafana
- ⚙️ Circuit breakers and fallback responses

---

## 👤 Author

**Marvin Wandati**

Connect on [LinkedIn](https://www.linkedin.com/in/marvin-wandati)  
GitHub: [@Wandati](https://github.com/Wandati)
