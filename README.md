# Employee Management System

A simple, production-style REST API for managing employee records — built with
Spring Boot, Spring Data JPA, and MySQL. Supports full CRUD operations plus
search by name, with input validation, centralized error handling, and a
consistent JSON response envelope.

## Project Description

This project models a small HR backend. It exposes a REST API that lets a
client (Postman, a frontend app, another service, etc.) create, read, update,
delete, and search employee records stored in a MySQL database. The codebase
follows a standard layered architecture so it's easy to extend:

```
Controller  -->  Service (interface + impl)  -->  Repository  -->  Database
   (HTTP)            (business logic)            (Spring Data JPA)
```

Key design choices:
- **DTO layer** — the API never exposes the JPA entity directly; `EmployeeDTO`
  decouples the public contract from the persistence model.
- **Bean validation** (`@NotBlank`, `@Email`, `@Positive`, etc.) on the DTO
  rejects bad input before it ever reaches the database.
- **Global exception handler** turns "employee not found," "duplicate email,"
  and validation failures into clean, consistent JSON error responses instead
  of stack traces.
- **Consistent response envelope** (`ApiResponse<T>`) — every endpoint returns
  `{ success, message, data, timestamp }`, so clients can handle responses
  uniformly.

## Tech Stack

| Layer            | Technology                          |
|-------------------|--------------------------------------|
| Language          | Java 17                              |
| Framework         | Spring Boot 3.2.5                    |
| Data Access       | Spring Data JPA (Hibernate)          |
| Database          | MySQL 8                              |
| Validation        | Spring Boot Starter Validation       |
| Build Tool        | Maven                                |
| Boilerplate       | Lombok                               |
| Testing           | Spring Boot Starter Test (JUnit 5)   |

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

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8 running locally (or update the connection URL for a remote instance)

### 1. Clone the repository
```bash
git clone https://github.com/<your-username>/employee-management-system.git
cd employee-management-system
```

### 2. Configure the database
Open `src/main/resources/application.properties` and update the credentials
to match your MySQL setup:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
```
You don't need to create the `employee_db` schema manually —
`createDatabaseIfNotExist=true` handles that, and Hibernate
(`ddl-auto=update`) creates the `employees` table automatically on first run.

> For anything beyond local development, avoid committing real credentials —
> use environment variables instead, e.g.
> `spring.datasource.password=${DB_PASSWORD}`.

### 3. Build and run
```bash
mvn clean install
mvn spring-boot:run
```
The API will be available at `http://localhost:8080`.

### 4. Test it
```bash
curl -X POST http://localhost:8080/api/v1/employees \
  -H "Content-Type: application/json" \
  -d '{
        "firstName": "Anjali",
        "lastName": "Rao",
        "email": "anjali.rao@example.com",
        "phoneNumber": "9876543210",
        "department": "Engineering",
        "designation": "Backend Developer",
        "salary": 65000,
        "dateOfJoining": "2024-03-15"
      }'
```

## Project Structure

```
employee-management-system/
├── pom.xml
├── requests.http
├── README.md
└── src
    ├── main
    │   ├── java/com/example/ems
    │   │   ├── EmsApplication.java
    │   │   ├── controller/EmployeeController.java
    │   │   ├── service/EmployeeService.java
    │   │   ├── service/impl/EmployeeServiceImpl.java
    │   │   ├── repository/EmployeeRepository.java
    │   │   ├── entity/Employee.java
    │   │   ├── dto/EmployeeDTO.java
    │   │   ├── response/ApiResponse.java
    │   │   ├── exception/ResourceNotFoundException.java
    │   │   ├── exception/DuplicateResourceException.java
    │   │   ├── exception/GlobalExceptionHandler.java
    │   │   └── config/WebConfig.java
    │   └── resources/application.properties
    └── test/java/com/example/ems/EmsApplicationTests.java
```

## Possible Next Steps

- Add pagination/sorting to `GET /api/v1/employees`
- Add Spring Security with JWT auth for admin-only write operations
- Replace `ddl-auto=update` with Flyway/Liquibase migrations
- Add Swagger/OpenAPI docs via `springdoc-openapi`
- Containerize with Docker + docker-compose (app + MySQL)

## License

This project is open source and available for learning/portfolio use.
