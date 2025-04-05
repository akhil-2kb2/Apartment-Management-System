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

## 🧱 Backend Structure – Spring Boot (Gradle)
apartment-management-system/ └── backend/ ├── src/main/java/com/ams/ │ ├── config/ # Security Configuration │ │ └── SecurityConfig.java │ ├── controller/ # REST Controllers │ │ ├── ApartmentController.java │ │ ├── AuthController.java │ │ ├── DriverController.java │ │ ├── EntryExitController.java │ │ ├── FamilyMemberController.java │ │ ├── OwnerController.java │ │ ├── TenantController.java │ │ ├── VehicleController.java │ │ └── VisitorController.java │ ├── dto/ # DTOs for request/response │ ├── entity/ # JPA Entity Models │ ├── exception/ # GlobalExceptionHandler.java │ ├── filter/ # JwtAuthenticationFilter.java │ ├── repository/ # Spring Data Repositories │ ├── service/ # Business Logic │ ├── util/ # JwtUtil.java, PasswordGenerator.java │ ├── App.java │ └── ApartmentManagementSystemApplication.java ├── src/main/resources/ │ ├── application.properties └── request.rest # Postman-like REST test scripts
---


---

## 💻 Frontend Structure – Next.js 14 + Tailwind CSS 4

apartment-management-system/ └── frontend/ ├── public/ ├── src/ │ ├── app/ # Next.js App Router │ │ ├── page.tsx, layout.tsx │ ├── features/ │ │ ├── apartments/ │ │ ├── owners/ │ │ ├── tenants/ │ │ ├── vehicles/ │ │ ├── visitors/ │ │ ├── drivers/ │ │ ├── familyMembers/ │ │ └── auth/ │ ├── components/ # Shared UI (Sidebar, Navbar, etc.) │ ├── services/ # Centralized API Wrappers │ ├── shared/ # Utils, Hooks, Constants │ ├── context/ # App & Auth Context ├── tailwind.config.js ├── postcss.config.mjs ├── tsconfig.json ├── package.json

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


---

## ⚙️ How to Run

### 📦 Backend Setup

```bash
cd backend
./gradlew bootRun



App runs on: http://localhost:3000

🔐 Authentication & Roles
Role	Can Create	Can Manage	Can View Everything
ADMIN	SECRETARY	-	✅
SECRETARY	SECURITY	Owners, Tenants, Vehicles, Families	✅
SECURITY	—	Entry/Exit logs, Visitors, Drivers	✅
🔑 Authentication handled using JWT, with secure login and role mapping.

✅ Key Features
📋 Role-Based Registration & Login

🏘️ Apartment CRUD

👤 Owner & Tenant Management

🚗 Vehicle linked to Visitor/Owner/Tenant/Family

🛂 Visitor Check-in/Check-out

🚦 Entry/Exit Logs (Driver + Vehicle)

👨‍👩‍👧‍👦 FamilyMember Auto-create

📎 Validation & Exception Handling

🌐 REST APIs + DTO Model Mapping

🧪 Testing
request.rest — Run with IntelliJ HTTP Client

✅ Status: All endpoints tested manually & integrated

🧰 Tech Stack
Category	Tech
Backend	Java 17, Spring Boot, Spring Security
Database	MySQL
Frontend	Next.js 14 (App Router), React, TypeScript
Styling	Tailwind CSS 4
Auth	JWT, Role-based Access Control
Build Tools	Gradle, npm
🧱 Database Schema (MySQL)
users, user_roles, owners, tenants, apartments

visitors, vehicles, drivers, entry_exit_logs

family_members (linked via owner_id or tenant_id)

🔮 Future Enhancements
📊 Admin dashboard with charts

📸 Visitor photo capture

🧠 AI-based occupancy prediction

🛠️ PWA mobile app integration

📄 License
MIT © 2025 – Akhilesh Yadav

👨‍💻 Author
Developed with ❤️ by Akhilesh Yadav
Full Stack Java Developer | Passionate about Modern Architecture & Clean Code

🚀 Ready to scale your apartment administration? Clone this repo and get started today!

yaml
Copy
Edit

---

### ✅ Now What?

- Save as `README.md`
- Add `LICENSE`, `.gitignore`, and `.env.example` if needed
- Let me know when you're ready to go module-by-module frontend ✨

Let’s keep crushing it 🚀






