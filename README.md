<h1 align="center">🏢 Apartment Management System</h1>
<p align="center">A complete modern web solution for managing residential apartments using <strong>Spring Boot</strong> and <strong>Next.js</strong>.</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue.svg"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.2-green.svg"/>
  <img src="https://img.shields.io/badge/Next.js-14-black.svg"/>
  <img src="https://img.shields.io/badge/TailwindCSS-4.0-blue.svg"/>
</p>

---

## 🚀 About the Project

The **Apartment Management System** is a full-stack web application designed to streamline the administration of apartment complexes including:

- 🔐 User Authentication (JWT)
- 🏘️ Apartment, Owner, Tenant, Vehicle, Visitor Management
- 🚦 Entry/Exit Log with Vehicle, Driver Tracking
- 👨‍👩‍👧‍👦 Family Member auto-mapping
- 🧠 Role-based Access: `ADMIN`, `SECRETARY`, `SECURITY`

---

## 🗂️ Project Structure


---

## 💡 Features

| Module           | Description                                                   | Access Roles                        |
|------------------|---------------------------------------------------------------|-------------------------------------|
| 👤 Auth           | Admin creates Secretary, Secretary creates Security          | Admin > Secretary > Security        |
| 🏢 Apartment       | Manage apartments, link to owners/tenants                    | Secretary                           |
| 👨 Owner          | Manage owners, assign apartments, auto-create self as family | Secretary                           |
| 👩 Tenant         | Manage tenants, assign apartments                            | Secretary                           |
| 🚗 Vehicle        | Assign vehicles to visitor, owner, tenant, or family         | Secretary, Security (view)          |
| 🙋 Visitor        | Check-in/out visitor, link vehicle if applicable             | Security                            |
| 📋 Entry/Exit     | Logs for vehicles & drivers for all user types               | Security                            |
| 👨‍👩‍👧‍👦 FamilyMember | Auto-managed via owner/tenant creation                      | Secretary                           |
| 🚙 Driver         | Manage entry drivers (linked to vehicle log)                 | Secretary                           |

---

## 🔐 Authentication

✔️ JWT Based  
✔️ Role-Based Method Protection  
✔️ Token Injection via Spring Security Filter  
✔️ Username/Password + Role check on login

---

## ⚙️ Setup Instructions

### 📦 Backend – Spring Boot + Gradle

```bash
cd backend
./gradlew bootRun
