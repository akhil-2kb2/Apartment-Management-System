# 🏢 Apartment Management System

A full-stack **Apartment Management System** designed to manage owners, tenants, vehicles, visitors, entry-exit logs, and more. Built with **Spring Boot**, **MySQL**, **JWT Authentication**, and **Next.js (React + Tailwind CSS)** for a smooth modern UI experience.

---

## 📁 Project Structure




---

## 🛠️ Technologies Used

### 📦 Backend
- Spring Boot 3
- Spring Security + JWT
- JPA + Hibernate
- MySQL
- Lombok
- Maven

### 💻 Frontend
- Next.js 14 (App Router)
- React + TypeScript
- Tailwind CSS 4
- Axios

---

## 🔐 Authentication System

- Role-Based Access: `ADMIN`, `SECRETARY`, `SECURITY`
- Token-Based Auth using JWT
- Dynamic Token validation via Spring Security Filter
- Endpoint restrictions via `@PreAuthorize`

---

## 🚀 Getting Started

### ✅ Prerequisites
- Node.js (18+)
- MySQL
- Java 17+
- Maven

---

### 🧪 Backend Setup

```bash
cd backend
# configure your MySQL in application.properties
./mvnw spring-boot:run
