# Complete Spring Boot Project - Setup & Testing Guide

## 📁 Project Location
```
c:\Users\devsi\OneDrive\Desktop\6th sem\full stack\spring-student-app
```

## 📋 Project Overview

This is a complete Spring Boot Student Management Application built following three difficulty levels:

### EASY - Project Initialization & Structure ✅
- *Created*: Maven-based Spring Boot project
- *Created*: Proper layered architecture with separate packages
- *Created*: All dependencies in pom.xml
- *Created*: Database configuration in application.properties

### MEDIUM - REST APIs & DTO Validation ✅
- *Created*: 5 CRUD REST endpoints
- *Created*: StudentDTO with validation annotations (@NotBlank, @Email, @Min, @Max)
- *Created*: StudentService with business logic
- *Created*: StudentController with @Valid annotation

### HARD - Global Exception Handling ✅
- *Created*: ResourceNotFoundException custom exception
- *Created*: ErrorResponse structured error class
- *Created*: GlobalExceptionHandler with @ControllerAdvice
- *Created*: Multiple exception handlers for different error scenarios

---

## 🚀 Quick Start

### Step 1: Open Project in IDE
```bash
# Open in IntelliJ IDEA or VS Code
# File → Open → spring-student-app folder
```

### Step 2: Build Project
```bash
# Using Maven (in terminal)
mvn clean install
```

### Step 3: Run Application
```bash
# Option 1: From IDE (right-click StudentappApplication.java → Run)
# Option 2: From terminal
mvn spring-boot:run
```

### Step 4: Verify Application Started
```
Expected output:
2024-03-30 10:30:45.123  INFO 1234 --- [main] StudentappApplication: Started StudentappApplication
Tomcat started on port(s): 8080 (http)
```

---

## 📡 API Testing Guide

### Method 1: Using Postman

#### 1️⃣ Create Student (POST)
**URL:** `http://localhost:8080/students`
**Method:** POST
**Headers:** `Content-Type: application/json`

**Request Body:**
```json
{
  "name": "Alice Johnson",
  "email": "alice@example.com",
  "age": 22
}
```

**Expected Response:** 201 Created
```json
{
  "id": 1,
  "name": "Alice Johnson",
  "email": "alice@example.com",
  "age": 22
}
```

---

#### 2️⃣ Get All Students (GET)
**URL:** `http://localhost:8080/students`
**Method:** GET

**Expected Response:** 200 OK
```json
[
  {
    "id": 1,
    "name": "Alice Johnson",
    "email": "alice@example.com",
    "age": 22
  },
  {
    "id": 2,
    "name": "Bob Smith",
    "email": "bob@example.com",
    "age": 25
  }
]
```

---

#### 3️⃣ Get Student by ID (GET)
**URL:** `http://localhost:8080/students/1`
**Method:** GET

**Expected Response:** 200 OK
```json
{
  "id": 1,
  "name": "Alice Johnson",
  "email": "alice@example.com",
  "age": 22
}
```

---

#### 4️⃣ Update Student (PUT)
**URL:** `http://localhost:8080/students/1`
**Method:** PUT
**Headers:** `Content-Type: application/json`

**Request Body:**
```json
{
  "name": "Alice Updated",
  "email": "alice.updated@example.com",
  "age": 23
}
```

**Expected Response:** 200 OK
```json
{
  "id": 1,
  "name": "Alice Updated",
  "email": "alice.updated@example.com",
  "age": 23
}
```

---

#### 5️⃣ Delete Student (DELETE)
**URL:** `http://localhost:8080/students/1`
**Method:** DELETE

**Expected Response:** 200 OK
```json
"Student deleted successfully"
```

---

### Method 2: Using cURL (Command Line)

#### Create Student
```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Charlie Brown","email":"charlie@example.com","age":20}'
```

#### Get All Students
```bash
curl http://localhost:8080/students
```

#### Get Student by ID
```bash
curl http://localhost:8080/students/1
```

#### Update Student
```bash
curl -X PUT http://localhost:8080/students/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Name","email":"updated@example.com","age":24}'
```

#### Delete Student
```bash
curl -X DELETE http://localhost:8080/students/1
```

---

## 🔴 Testing Exception Handling

### Test 1: Invalid Email Format (400 Bad Request)
**URL:** `http://localhost:8080/students`
**Method:** POST

**Request Body:**
```json
{
  "name": "Test User",
  "email": "invalid-email-format",
  "age": 22
}
```

**Expected Response:** 400 Bad Request
```json
{
  "timestamp": "2024-03-30T10:45:30.123456",
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email should be valid"
  },
  "path": "/students"
}
```

---

### Test 2: Age Below 18 (400 Bad Request)
**URL:** `http://localhost:8080/students`
**Method:** POST

**Request Body:**
```json
{
  "name": "Young Person",
  "email": "young@example.com",
  "age": 15
}
```

**Expected Response:** 400 Bad Request
```json
{
  "timestamp": "2024-03-30T10:46:15.654321",
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "age": "Age must be at least 18"
  },
  "path": "/students"
}
```

---

### Test 3: Missing Name Field (400 Bad Request)
**URL:** `http://localhost:8080/students`
**Method:** POST

**Request Body:**
```json
{
  "email": "test@example.com",
  "age": 22
}
```

**Expected Response:** 400 Bad Request
```json
{
  "timestamp": "2024-03-30T10:47:00.789012",
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "name": "Student name is required"
  },
  "path": "/students"
}
```

---

### Test 4: Student Not Found (404 Not Found)
**URL:** `http://localhost:8080/students/999`
**Method:** GET

**Expected Response:** 404 Not Found
```json
{
  "timestamp": "2024-03-30T10:48:30.234567",
  "status": 404,
  "message": "Student not found with ID: 999",
  "path": "/students/999"
}
```

---

### Test 5: Update Non-Existent Student (404 Not Found)
**URL:** `http://localhost:8080/students/999`
**Method:** PUT

**Request Body:**
```json
{
  "name": "Ghost Student",
  "email": "ghost@example.com",
  "age": 25
}
```

**Expected Response:** 404 Not Found
```json
{
  "timestamp": "2024-03-30T10:49:15.567890",
  "status": 404,
  "message": "Student not found with ID: 999",
  "path": "/students/999"
}
```

---

## 🧪 Unit Tests

Run the integrated tests to verify everything works:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=StudentappApplicationTests

# Run with coverage
mvn test jacoco:report
```

---

## 📚 Important Files & Their Purpose

| File | Location | Purpose |
|------|----------|---------|
| **StudentappApplication.java** | src/main/java/.../ | Main Spring Boot entry point |
| **Student.java** | .../entity/ | JPA Entity - Database model |
| **StudentDTO.java** | .../dto/ | Data Transfer Object with validation |
| **StudentRepository.java** | .../repository/ | JPA Repository for CRUD operations |
| **StudentService.java** | .../service/ | Business logic layer |
| **StudentController.java** | .../controller/ | REST API endpoints |
| **ResourceNotFoundException.java** | .../exception/ | Custom exception for missing resources |
| **GlobalExceptionHandler.java** | .../exception/ | Central exception handling |
| **ErrorResponse.java** | .../exception/ | Structured error response |
| **application.properties** | src/main/resources/ | Application configuration |
| **pom.xml** | Root | Maven dependencies and build config |

---

## 🔧 Configuration

**File:** `application.properties`

```properties
# Server runs on port 8080
server.port=8080

# H2 In-Memory Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=create-drop  # Creates tables on startup
spring.jpa.show-sql=true  # Shows SQL queries in console
```

---

## 🌐 H2 Database Console

Access the database console:

**URL:** `http://localhost:8080/h2-console`

**Login Details:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

**View Students Table:**
```sql
SELECT * FROM students;
```

---

## 📝 Layered Architecture Summary

```
┌─────────────────────────────────────────────────┐
│  REST Client (Postman, Browser, cURL)           │
└────────────────┬────────────────────────────────┘
                 │ HTTP Request/Response
┌────────────────▼────────────────────────────────┐
│  Controller Layer (StudentController)           │
│  ├─ @PostMapping, @GetMapping, etc.            │
│  └─ @Valid annotation for validation           │
└────────────────┬────────────────────────────────┘
                 │ Method calls
┌────────────────▼────────────────────────────────┐
│  Service Layer (StudentService)                 │
│  ├─ Business logic                             │
│  ├─ Data transformation                        │
│  └─ Exception throwing                         │
└────────────────┬────────────────────────────────┘
                 │ JPA calls
┌────────────────▼────────────────────────────────┐
│  Repository Layer (StudentRepository)           │
│  ├─ Database CRUD operations                   │
│  └─ JpaRepository interface                    │
└────────────────┬────────────────────────────────┘
                 │ SQL queries
┌────────────────▼────────────────────────────────┐
│  Data Layer (H2 In-Memory Database)            │
│  └─ students table with CRUD data              │
└─────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────┐
│  Global Exception Handler (@ControllerAdvice)   │
│  ├─ Catches all exceptions from layers         │
│  ├─ Returns structured JSON responses          │
│  └─ HTTP status codes (400, 404, 500)          │
└─────────────────────────────────────────────────┘
```

---

## ✅ Verification Checklist

- [ ] Project created at correct location
- [ ] Application starts successfully on port 8080
- [ ] Can create a student via POST
- [ ] Can retrieve all students via GET
- [ ] Can retrieve specific student via GET /{id}
- [ ] Can update student via PUT
- [ ] Can delete student via DELETE
- [ ] Validation errors return 400 Bad Request
- [ ] Invalid student ID returns 404 Not Found
- [ ] Error responses have proper structure (timestamp, status, message, path)
- [ ] H2 console accessible at /h2-console
- [ ] Unit tests pass

---

## 🎓 Learning Outcomes

### Easy Level ✅
- ✓ Spring Boot project structure
- ✓ Maven dependency management
- ✓ Layered architecture pattern
- ✓ Basic Spring configuration

### Medium Level ✅
- ✓ RESTful API design
- ✓ Http methods (GET, POST, PUT, DELETE)
- ✓ DTO pattern for data transfer
- ✓ JSR-380 validation annotations
- ✓ Request body validation with @Valid

### Hard Level ✅
- ✓ Custom exception creation
- ✓ @ControllerAdvice global exception handling
- ✓ Structured error responses
- ✓ Multiple exception handlers
- ✓ Status codes (400, 404, 500)
- ✓ Field-level error messages

---

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Port 8080 already in use | Change `server.port=8081` in application.properties |
| Application won't start | Check JDK 17+ installed: `java -version` |
| Maven build fails | Run `mvn clean install` or check pom.xml |
| H2 console error | Ensure H2 dependency in pom.xml |
| Validation not working | Verify `@Valid` in controller |
| Exception handler not triggered | Check `@ControllerAdvice` class location |

---

## 📞 Support

For issues or questions, refer to:
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Jakarta Validation](https://jakarta.ee/specifications/bean-validation/)

---

**Created:** March 30, 2026
**Status:** ✅ Complete and Ready for Testing
