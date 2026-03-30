# Spring Boot Student Management Application

A complete Spring Boot application demonstrating layered architecture, RESTful APIs, DTO validation, and global exception handling.

## Project Structure

```
spring-student-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/studentapp/
│   │   │   ├── entity/           # Database entities
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── repository/       # JPA repositories
│   │   │   ├── service/          # Business logic
│   │   │   ├── controller/       # REST endpoints
│   │   │   └── exception/        # Custom exceptions & global handler
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Features

### Easy - Project Structure & Setup
- ✅ Maven-based Spring Boot project
- ✅ Proper layered architecture
- ✅ Database configuration (H2 in-memory DB)
- ✅ All dependencies configured

### Medium - REST APIs & DTO Validation
- ✅ 5 REST endpoints (CRUD operations):
  - `POST /students` - Create student
  - `GET /students` - Get all students
  - `GET /students/{id}` - Get student by ID
  - `PUT /students/{id}` - Update student
  - `DELETE /students/{id}` - Delete student
- ✅ DTO with validation annotations:
  - `@NotBlank` - Required fields
  - `@Email` - Email validation
  - `@Min/@Max` - Age validation
- ✅ Input validation at controller layer using `@Valid`

### Hard - Global Exception Handling
- ✅ Custom `ResourceNotFoundException` for missing records
- ✅ Structured `ErrorResponse` class with:
  - Timestamp
  - HTTP status code
  - Error message
  - Request path
- ✅ `@ControllerAdvice` with multiple exception handlers:
  - Handles `ResourceNotFoundException` → 404 NOT FOUND
  - Handles validation errors → 400 BAD REQUEST with field-wise errors
  - Handles generic exceptions → 500 INTERNAL SERVER ERROR

## Technologies Used

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Web** - REST APIs
- **Spring Data JPA** - Database operations
- **Spring Validation** - Input validation
- **H2 Database** - In-memory database
- **Lombok** - Reduce boilerplate code
- **Maven** - Build tool

## Prerequisites

- JDK 17 or higher installed
- Maven 3.6+ installed
- IDE: IntelliJ IDEA or VS Code

## Getting Started

### 1. Clone/Extract the Project
```bash
cd spring-student-app
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start at `http://localhost:8080`

### 4. Access H2 Database Console (Optional)
```
http://localhost:8080/h2-console
```
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### Create Student
```bash
POST http://localhost:8080/students
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 22
}
```

### Get All Students
```bash
GET http://localhost:8080/students
```

### Get Student by ID
```bash
GET http://localhost:8080/students/1
```

### Update Student
```bash
PUT http://localhost:8080/students/1
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane@example.com",
  "age": 23
}
```

### Delete Student
```bash
DELETE http://localhost:8080/students/1
```

## Testing the Application

### Success Scenario
```bash
# Create a student
POST http://localhost:8080/students
{
  "name": "Alice Smith",
  "email": "alice@example.com",
  "age": 25
}

# Response: 201 Created
```

### Validation Error (Bad Request)
```bash
POST http://localhost:8080/students
{
  "name": "Bob",
  "email": "invalid-email",
  "age": 15
}

# Response: 400 Bad Request
{
  "timestamp": "2024-03-30T10:15:30.123456",
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email should be valid",
    "age": "Age must be at least 18"
  },
  "path": "/students"
}
```

### Resource Not Found (Not Found)
```bash
GET http://localhost:8080/students/999

# Response: 404 Not Found
{
  "timestamp": "2024-03-30T10:16:45.654321",
  "status": 404,
  "message": "Student not found with ID: 999",
  "path": "/students/999"
}
```

## Project Files Description

| File | Purpose |
|------|---------|
| `StudentappApplication.java` | Main Spring Boot application entry point |
| `Student.java` | Entity class representing student in database |
| `StudentDTO.java` | Data Transfer Object with validation |
| `StudentRepository.java` | JPA repository for database operations |
| `StudentService.java` | Business logic layer |
| `StudentController.java` | REST controller with endpoints |
| `ResourceNotFoundException.java` | Custom exception for missing resources |
| `ErrorResponse.java` | Structured error response class |
| `GlobalExceptionHandler.java` | Central exception handling using @ControllerAdvice |
| `application.properties` | Application configuration |

## Key Learnings

### Easy Level
- Spring Boot project structure and initialization
- Dependency management with Maven
- Basic Spring Boot configuration

### Medium Level
- RESTful API design with proper HTTP methods
- DTO pattern for data transfer
- Validation using JSR-380 annotations
- Controller layer implementation

### Hard Level
- Global exception handling with @ControllerAdvice
- Custom exception creation
- Structured error responses
- Handling multiple exception types
- Field-level validation error messages

## Testing Tools

Use any of these tools to test the APIs:
- **Postman** - Full-featured API testing
- **Thunder Client** - VS Code extension
- **cURL** - Command-line tool
- **Browser** - For GET requests

## Troubleshooting

### Issue: Application won't start
- Ensure JDK 17+ is installed
- Check Maven is installed: `mvn --version`
- Try: `mvn clean install`

### Issue: Port 8080 already in use
- Change port in `application.properties`:
  ```properties
  server.port=8081
  ```

### Issue: Database errors
- Delete H2 database file if it exists
- Application uses in-memory H2, so it resets on restart

## Next Steps

To extend this project:
1. Add pagination to GET all students
2. Add search/filter functionality
3. Implement JWT authentication
4. Add logging with SLF4J
5. Add unit tests with JUnit and Mockito
6. Deploy to cloud (AWS, Azure, etc.)

## License

This project is open source and available for educational purposes.
