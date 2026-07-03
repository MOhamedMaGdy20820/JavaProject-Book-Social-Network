# 📚 Book Social Network: Real-time Collaboration Platform

[![Java Version](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.0-green?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-18-red?style=for-the-badge&logo=angular)](https://angular.io/)
[![Keycloak](https://img.shields.io/badge/Keycloak-Identity-blue?style=for-the-badge&logo=keycloak)](https://www.keycloak.org/)
[![Docker Compose](https://img.shields.io/badge/Docker-Compose_Ready-blue?style=for-the-badge&logo=docker)](https://www.docker.com/)

This repository represents a robust social platform for book enthusiasts. It showcases a modern architecture featuring **real-time notifications** via WebSockets, secured with **Keycloak OIDC**, and fully containerized for seamless development and deployment.

---

## 🏛️ System Architecture & Workflow

The platform follows a modular approach, ensuring high performance and decoupling through reactive communication patterns.

* **`BSN-API`**: Core Spring Boot microservice managing books, transactions, and user feedback.
* **`BSN-UI`**: Angular 18 frontend leveraging SSR for performance and WebSockets for interactivity.
* **`Real-time Engine`**: Implements `STOMP` protocol over WebSockets for instant transaction updates (borrowing/returning books).
* **`Identity & Access`**: Keycloak instance handling OpenID Connect and JWT validation.

---

## 🛠️ Tech Stack & Key Implementations

### 1. Backend & Communication
* **Java 17 & Spring Boot 3.x**: Core development framework.
* **Spring WebSockets (STOMP)**: Real-time event propagation between users and owners.
* **Hibernate (JPA)**: Reliable ORM for persistent transaction history.

### 2. Security & IAM
* **Keycloak**: OIDC provider for centralized identity management.
* **Spring Security OAuth2**: Protecting downstream APIs and enforcing authorization policies.

### 3. Frontend & SSR
* **Angular 18**: Reactive frontend with SSR support for better SEO and load times.
* **ngx-toastr**: User experience optimization with professional notification overlays.
* **SockJS & StompJS**: Bridging the gap between the browser and the WebSocket message broker.

### 4. DevOps & CI/CD
* **Docker Compose**: Orchestrating the BSN ecosystem (`API`, `PostgreSQL`, `Keycloak`, `MailDev`).
* **GitHub Actions**: Automated CI/CD pipeline targeting a self-hosted **Linux VM runner**.
* **Deployment**: Automated build-and-deploy cycle ensuring consistency from local dev to production.

---

## 🚀 How to Run Locally

1. **Spin Up the Infrastructure (Keycloak, DB, MailDev):**
    ```bash
    docker compose up -d
    ```
2. **Start the Backend:**
    ```bash
    cd book-network
    ./mvnw spring-boot:run
    ```
3. **Start the Frontend:**
    ```bash
    cd book-network-ui
    npm install
    ng serve
    ```

---

## 🏗️ DevOps Highlights: Custom CI/CD
* **Self-Hosted Runner**: Provisioned and configured a custom **Linux VM** to act as a private `GitHub Actions` runner.
* **Automated Pipeline**: The CI/CD pipeline triggers on main branch pushes, handles image builds, and executes secure deployments to the custom-provisioned server, significantly reducing manual intervention.

---

👨‍💻 **Developed by [Mohamed Magdy](https://github.com/MOhamedMaGdy20820)**
