<h1 align="center">🏢 Apartment Management System</h1>
<p align="center">A modern web solution for managing residential apartments using <strong>Spring Boot</strong>, <strong>Next.js</strong>, and <strong>Tailwind CSS</strong>.</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue.svg"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.2-green.svg"/>
  <img src="https://img.shields.io/badge/Next.js-14-black.svg"/>
  <img src="https://img.shields.io/badge/TailwindCSS-4.0-blue.svg"/>
</p>

---

## 🚀 About the Project

The **Apartment Management System** is a full-stack web application designed to simplify apartment complex management, featuring:

- 🔐 **User Authentication** (JWT)
- 🏘️ **Apartment, Owner, Tenant, Vehicle, Visitor Management**
- 🚦 **Entry/Exit Logs** with Vehicle & Driver Tracking
- 👨‍👩‍👧‍👦 **Family Member Auto-Mapping**
- 🧠 **Role-Based Access**: `ADMIN`, `SECRETARY`, `SECURITY`

---

## 🧱 Backend – Spring Boot + Gradle

```bash
apartment-management-system/
└── backend/
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/ams/
    │       │       ├── config/             # Security Configurations
    │       │       ├── controller/         # REST API Controllers
    │       │       ├── dto/                # DTOs for API I/O
    │       │       ├── entity/             # JPA Entities
    │       │       ├── exception/          # Global Exception Handling
    │       │       ├── filter/             # JWT Filter
    │       │       ├── repository/         # Spring Data JPA Repos
    │       │       ├── service/            # Business Logic
    │       │       ├── util/               # JWT Utils & Helpers
    │       │       └── ApartmentManagementSystemApplication.java
    │       └── resources/
    │           └── application.properties
    └── request.rest                       # IntelliJ HTTP Client Tests
```

---

## 💻 Frontend – Next.js 14 + Tailwind CSS 4

```bash
apartment-management-system/
└── frontend/
    ├── public/
    └── src/
        ├── app/                 # Next.js App Router Pages
        │   ├── layout.tsx
        │   └── page.tsx
        ├── components/          # Reusable UI Components (Navbar, Sidebar)
        ├── features/            # Module-wise Logic & Pages
        │   ├── apartments/
        │   ├── owners/
        │   ├── tenants/
        │   ├── vehicles/
        │   ├── visitors/
        │   ├── drivers/
        │   ├── familyMembers/
        │   └── auth/
        ├── services/            # API Wrappers
        ├── shared/              # Hooks, Constants, Utilities
        └── context/             # Global State (App + Auth Context)
    ├── tailwind.config.js
    ├── postcss.config.mjs
    ├── tsconfig.json
    └── package.json
```

---

## 💡 Features

| Module           | Description                                                   | Access Roles                        |
|------------------|---------------------------------------------------------------|-------------------------------------|
| 👤 **Auth**       | Admin creates Secretary, Secretary creates Security          | Admin > Secretary > Security        |
| 🏢 **Apartment**  | Manage apartments, link to owners/tenants                    | Secretary                           |
| 👨 **Owner**      | Manage owners, assign apartments, auto-create self as family | Secretary                           |
| 👩 **Tenant**     | Manage tenants, assign apartments                            | Secretary                           |
| 🚗 **Vehicle**    | Assign vehicles to visitor, owner, tenant, or family         | Secretary, Security (view)          |
| 🙋 **Visitor**    | Check-in/out visitor, link vehicle if applicable             | Security                            |
| 📋 **Entry/Exit** | Logs for vehicles & drivers for all user types               | Security                            |
| 👨‍👩‍👧‍👦 **FamilyMember** | Auto-managed via owner/tenant creation                       | Secretary                           |
| 🚙 **Driver**     | Manage entry drivers (linked to vehicle log)                 | Secretary                           |

---

## 🔐 Authentication

- ✔️ **JWT-Based Authentication**
- ✔️ **Role-Based Method Protection**
- ✔️ **Token Injection via Spring Security Filter**
- ✔️ **Username/Password + Role Check on Login**

---

## 🧰 Tech Stack

| 🔧 Category     | 💻 Technology                                         |
|----------------|--------------------------------------------------------|
| **Backend**    | Java 17, Spring Boot 3, Spring Security, Gradle        |
| **Frontend**   | Next.js 14 (App Router), React 18, TypeScript          |
| **Styling**    | Tailwind CSS 4, PostCSS                                |
| **Database**   | MySQL                                                  |
| **Authentication** | JWT (JSON Web Token), Role-Based Access Control |
| **Dev Tools**  | IntelliJ IDEA, VS Code, Postman, REST Client           |
| **Build Tools**| Gradle, npm                                            |
| **API Design** | RESTful APIs, DTO Mappings                             |
| **Version Control** | Git, GitHub                                      |

---

## 🧪 Testing

> All modules have been **manually tested** end-to-end.  
> Use the built-in `.rest` file for automated testing in IntelliJ.

<details>
  <summary>🧪 How to Test Using IntelliJ HTTP Client</summary>

1. Open `request.rest` from the `/backend` folder.
2. IntelliJ will display clickable REST buttons beside each request.
3. Hit send and test responses from your running backend server.
4. Verify JWT tokens, response structures, and error handling.

</details>

---

## 👨‍💻 Author

Developed with  by:

### **Akhilesh Yadav**  
**Full Stack Java Developer** | Passionate about Modern Architecture & Clean Code

- 🧠 Believer in solving real-world problems with code  
- 🔄 Obsessed with modularity, reusability & clean architecture  
- 📫 Connect with me: [LinkedIn](https://www.linkedin.com/in/akhileshyadavak2kb2/)  
- 🌐 Portfolio: [portfolio](https://portfolio-akhilesh-yadav.vercel.app/)  
- 🐙 GitHub: [github.com](https://github.com/akhil-2kb2)

---

## 🔮 Future Enhancements

- 📊 Admin dashboard with charts
- 📸 Visitor photo capture
- 🧠 AI-based occupancy prediction
- 🛠️ PWA mobile app integration

---



||  🚀 Ready to scale your apartment administration? Clone this repo and get started today! ||


---
