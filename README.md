# Employee Management System

A complete, production-ready, **fully deployed** employee management application with a professional **REST API backend** and a **responsive web frontend**. Built with Spring Boot 3.2.5, Spring Data JPA, MySQL 8, and vanilla HTML/CSS/JavaScript.

## 🚀 Live Demo

**Frontend (GitHub Pages):** https://roobakm.github.io/employee-management-system/

**Backend API (Railway):** https://employee-management-system-production-b757.up.railway.app/api/v1/employees

**GitHub Repository:** https://github.com/RoobakM/employee-management-system

---

## Project Description

This is a full-stack HR management system with both backend and frontend components:

**Backend (REST API):**
Exposes a complete REST API for CRUD operations on employee records, with input validation, global error handling, and consistent JSON responses.

**Frontend (Web Dashboard):**
A modern, responsive single-page application for managing employees through a visual interface. Features include pagination, sorting, searching, and beautiful UI with modals for add/edit operations.

The codebase follows a standard **layered architecture** for maintainability:

```
┌─────────────────────────────────────────────────────┐
│           Web UI (HTML/CSS/JavaScript)              │
│        (Pagination, Sorting, Search, Modals)        │
└──────────────────┬──────────────────────────────────┘
                   │ HTTP/JSON
┌──────────────────▼──────────────────────────────────┐
│        REST API (Spring Boot Controller)            │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│  Service Layer (Business Logic & Validation)        │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│   Repository Layer (Spring Data JPA)                │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│        MySQL Database (InnoDB)                      │
└─────────────────────────────────────────────────────┘
```

**Key Design Principles:**
- **DTO Pattern** — API contract is separate from entity model
- **Input Validation** — bean validation (`@NotBlank`, `@Email`, `@Positive`, etc.)
- **Centralized Exception Handling** — consistent error responses
- **Consistent API Responses** — every endpoint returns `{ success, message, data, timestamp }`
- **CORS Enabled** — frontend on different origin can freely call the API
- **Professional Frontend** — responsive design, modal dialogs, pagination, sorting

## Tech Stack

| Component      | Technology                          |
|----------------|------------------------------------|
| **Backend Language** | Java 17                              |
| **Backend Framework** | Spring Boot 3.2.5                    |
| **API** | REST API with Spring MVC                   |
| **Data Access** | Spring Data JPA (Hibernate)          |
| **Database** | MySQL 8                              |
| **Validation** | Spring Boot Starter Validation       |
| **Build Tool** | Maven                                |
| **Boilerplate** | Lombok                               |
| **Testing** | Spring Boot Starter Test (JUnit 5)   |
| **Frontend** | Vanilla HTML5, CSS3, JavaScript (ES6) |
| **UI/UX** | Responsive design, Modals, Animations |

## Features

### 🔧 Backend Features
✅ Full CRUD operations (Create, Read, Update, Delete)
✅ Search employees by name (first or last name, partial match)
✅ Input validation with detailed error messages
✅ Global exception handling for consistent error responses
✅ Unique email constraint (no duplicates)
✅ CORS enabled for cross-origin requests
✅ Clean, production-ready REST API

### 🎨 Frontend Features
✅ **Responsive Design** — works on desktop, tablet, and mobile
✅ **Pagination** — default 10 rows per page, configurable (10/25/50)
✅ **Column Sorting** — click headers to sort any column ascending/descending
✅ **Search** — real-time search by employee name
✅ **Add Employee** — modal form with validation
✅ **Edit Employee** — inline editing with auto-populated form
✅ **Delete Employee** — delete with confirmation dialog
✅ **Beautiful UI** — gradient header, smooth animations, modals
✅ **Loading States** — spinner while fetching data
✅ **Error Handling** — user-friendly error messages

## API Endpoints

Base URL: `http://localhost:8080/api/v1/employees`

| Method | Endpoint                  | Description                                  |
|--------|----------------------------|-----------------------------------------------|
| POST   | `/api/v1/employees`        | Add a new employee                           |
| GET    | `/api/v1/employees`        | Get all employees                            |
| GET    | `/api/v1/employees/{id}`   | Get a single employee by ID                  |
| GET    | `/api/v1/employees/search?name={name}` | Search employees by first/last name (partial match) |
| PUT    | `/api/v1/employees/{id}`   | Update an existing employee                  |
| DELETE | `/api/v1/employees/{id}`   | Delete an employee                           |

### Request body (POST / PUT)

```json
{
  "firstName": "Anjali",
  "lastName": "Rao",
  "email": "anjali.rao@example.com",
  "phoneNumber": "9876543210",
  "department": "Engineering",
  "designation": "Backend Developer",
  "salary": 65000,
  "dateOfJoining": "2024-03-15"
}
```

### Example success response

```json
{
  "success": true,
  "message": "Employee created successfully",
  "data": {
    "id": 1,
    "firstName": "Anjali",
    "lastName": "Rao",
    "email": "anjali.rao@example.com",
    "phoneNumber": "9876543210",
    "department": "Engineering",
    "designation": "Backend Developer",
    "salary": 65000.0,
    "dateOfJoining": "2024-03-15"
  },
  "timestamp": "2026-06-21T10:15:30"
}
```

### Example error response (e.g. GET /api/v1/employees/999)

```json
{
  "success": false,
  "message": "Employee not found with id: 999",
  "timestamp": "2026-06-21T10:16:02"
}
```

A ready-to-use set of sample requests is included in **`requests.http`**
(works out of the box with IntelliJ IDEA's HTTP client, or copy into Postman).

## How to Run

### Access the Live Application (No Setup Needed!) 🌐

**The application is already deployed and live online:**

1. **Open the Frontend:** https://roobakm.github.io/employee-management-system/
2. **View your 4 employees:** Jahir Husain, Yogesh Ramu, Umar Shabab, Gopal R
3. **Try these features:**
   - Add a new employee
   - Edit employee details
   - Delete employees
   - Search by name
   - Sort by clicking column headers
   - Change rows per page (10, 25, 50)

That's it! No installation needed. Everything works in your browser.

---

### Run Locally (For Development)

If you want to run this project on your own machine:

#### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8 running locally (or a remote instance)
- A modern web browser (Chrome, Firefox, Safari, Edge)

#### Backend Setup

**Step 1: Clone the repository**
```bash
git clone https://github.com/RoobakM/employee-management-system.git
cd employee-management-system
```

**Step 2: Configure the database**
Edit `src/main/resources/application.properties` and set your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

The `createDatabaseIfNotExist=true` flag means you don't need to create the schema manually — MySQL will create it on first run. The `allowPublicKeyRetrieval=true` is needed for MySQL 8's default authentication.

**Step 3: Build and run the backend**
```bash
mvn clean install
mvn spring-boot:run
```

You should see `Tomcat started on port(s): 8080 (http)` near the end. The API is now live at `http://localhost:8080`.

#### Frontend Setup (Local)

**Step 1: Update the API URL**
Edit `index.html` and change:
```javascript
const API_URL = 'http://localhost:8080/api/v1/employees';
```

**Step 2: Open in your browser**
Keep the backend running, then open `index.html` in your web browser by double-clicking it in Windows Explorer.

---

### Quick Test (Verify Live API)

Test your live Railway API with this PowerShell command:

```powershell
Invoke-RestMethod -Uri "https://employee-management-system-production-b757.up.railway.app/api/v1/employees" -Method Get | ConvertTo-Json -Depth 10
```

You should see your 4 employees in JSON format.

## Deployment Architecture

This project is deployed across two platforms for optimal performance and accessibility:

### Frontend Hosting (GitHub Pages)
- **URL:** https://roobakm.github.io/employee-management-system/
- **Cost:** Free forever
- **Technology:** Static HTML/CSS/JavaScript
- **Auto-deployed:** Whenever you push to GitHub

### Backend Hosting (Railway)
- **URL:** https://employee-management-system-production-b757.up.railway.app
- **Cost:** Free tier ($5/month credit, sufficient for low-traffic apps)
- **Technology:** Java Spring Boot + MySQL
- **Database:** MySQL 8 (managed database)
- **Auto-deployed:** From GitHub repo

### Data Flow
```
User Browser (GitHub Pages) 
    ↓ HTTP/CORS
Railway API (Spring Boot)
    ↓ JDBC
Railway MySQL Database
```

## Project Structure

```
employee-management-system/
├── pom.xml
├── index.html                          ← Web frontend (open in browser)
├── requests.http                       ← Sample API requests
├── README.md
├── .gitignore
└── src
    ├── main
    │   ├── java/com/example/ems
    │   │   ├── EmsApplication.java     ← Spring Boot entry point
    │   │   ├── controller/             ← REST endpoints
    │   │   │   └── EmployeeController.java
    │   │   ├── service/                ← Business logic
    │   │   │   ├── EmployeeService.java (interface)
    │   │   │   └── impl/EmployeeServiceImpl.java
    │   │   ├── repository/             ← Data access (JPA)
    │   │   │   └── EmployeeRepository.java
    │   │   ├── entity/                 ← JPA entities
    │   │   │   └── Employee.java
    │   │   ├── dto/                    ← Data transfer objects
    │   │   │   └── EmployeeDTO.java
    │   │   ├── response/               ← API response wrapper
    │   │   │   └── ApiResponse.java
    │   │   ├── exception/              ← Custom exceptions & handler
    │   │   │   ├── ResourceNotFoundException.java
    │   │   │   ├── DuplicateResourceException.java
    │   │   │   └── GlobalExceptionHandler.java
    │   │   └── config/                 ← Spring configs
    │   │       └── WebConfig.java (CORS setup)
    │   └── resources
    │       └── application.properties   ← MySQL connection
    └── test
        └── java/com/example/ems
            └── EmsApplicationTests.java
```

## Possible Next Steps

- **🔐 Backend Security** — Add Spring Security + JWT authentication for admin-only operations
- **📊 Analytics Dashboard** — Add endpoint and frontend component for salary statistics, department breakdown, etc.
- **🔍 Advanced Filtering** — Add filtering by department, salary range, or joining date in the API and frontend
- **📱 Mobile App** — Build a React Native or Flutter mobile companion app
- **🔄 Migrations** — Replace `ddl-auto=update` with Flyway/Liquibase for production-grade schema management
- **📄 API Documentation** — Add Swagger/OpenAPI documentation via `springdoc-openapi`
- **🐳 Docker** — Containerize with Docker + Docker Compose for easy deployment
- **⚡ Performance** — Add caching (Redis), pagination on the backend, or a search engine (Elasticsearch)
- **🧪 Testing** — Expand test coverage with unit tests (Mockito) and integration tests
- **🔔 Notifications** — Add email notifications when employees are added/updated/deleted
- **📤 Data Export** — Add CSV/Excel export functionality for employee lists

## Environment Variables (Production)

For production deployments, use environment variables instead of hardcoding credentials:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Then set these before running:
```bash
export DB_URL=jdbc:mysql://prod-db-host:3306/employee_db
export DB_USERNAME=prod_user
export DB_PASSWORD=your_secure_password
mvn spring-boot:run
```

## Current Data

The live database currently contains **4 employees:**

| ID | Name | Email | Department | Designation | Salary | Joining Date |
|----|------|-------|-----------|-------------|--------|--------------|
| 2 | Jahir Husain | jack@gmail.com | Engineering | Backend Developer | ₹68,000 | 2024-03-15 |
| 3 | Yogesh Ramu | yoge@gmail.com | Deploy | Dev Ops | ₹80,000 | 2026-05-20 |
| 4 | Umar Shabab | Shabab04@gmail.com | Developer | Full Stack | ₹60,000 | 2026-06-05 |
| 5 | Gopal R | gopalyogi@gmail.com | Marketing | Editor | ₹40,000 | 2026-07-10 |

---

## Deployment Status

✅ **Live & Working**
- Frontend: Deployed on GitHub Pages
- Backend: Deployed on Railway
- Database: Active with live data
- Data Synchronization: Real-time

**Last Updated:** June 2026

## License

This project is open source and available for learning/portfolio use.
