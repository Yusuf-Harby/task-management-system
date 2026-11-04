# Task Management System

A RESTful backend application built with Java and Spring Boot to manage team tasks, assignments, priorities, and task statuses.

## Features

- Create, retrieve, update, and delete tasks.
- Assign tasks to team members using an assignee ID.
- Update task status (`TODO`, `IN_PROGRESS`, `COMPLETED`).
- Set task priority (`LOW`, `MEDIUM`, `HIGH`).
- Filter tasks by status and assignee ID.
- Validate incoming request data.
- Centralized exception handling with consistent error responses.
- API documentation using Swagger UI.
- PostgreSQL database integration.

## Tech Stack

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Bean Validation
- Swagger / OpenAPI

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/tasks` | Create a task |
| GET | `/api/tasks` | Retrieve all tasks |
| GET | `/api/tasks/{id}` | Retrieve a task by ID |
| PUT | `/api/tasks/{id}` | Update a task |
| PATCH | `/api/tasks/{id}/status` | Update task status |
| DELETE | `/api/tasks/{id}` | Delete a task |

### Filtering

The tasks endpoint supports filtering by status and assignee ID.

Examples:

- `/api/tasks?status=TODO`
- `/api/tasks?assigneeId=1`
- `/api/tasks?status=TODO&assigneeId=1`

## Getting Started

### Prerequisites

- Java JDK 25
- PostgreSQL
- Git

### Database Setup

1. Install and start PostgreSQL.
2. Create a database named `task_management`.
3. Set the `DB_PASSWORD` environment variable to your PostgreSQL password.

The application uses the following database configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task_management
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
```

### Run the Application

Make sure the `DB_PASSWORD` environment variable is set before running the application.

On Linux and macOS:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application runs at:

`http://localhost:8080`

## API Documentation

After starting the application, access Swagger UI at:

`http://localhost:8080/swagger-ui/index.html`

## Error Handling

The application provides consistent error responses for validation failures, invalid request data, and resources that cannot be found.

## Project Status

The core backend functionality is implemented and tested locally.

## Author

Yusuf Harby