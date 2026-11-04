# Task Management System

A RESTful backend application built with Java and Spring Boot to manage tasks, assignments, priorities, and task statuses.

## Live Demo

**[Open Live API Documentation (Swagger UI)](https://task-management-system-52tf.onrender.com/swagger-ui/index.html)**

The backend is deployed on Render and uses a PostgreSQL database hosted on Neon.

> This project is a backend REST API. It does not include a frontend user interface. Swagger UI is provided for API documentation and testing.

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
- Cloud database persistence.

## Tech Stack

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Bean Validation
- Swagger / OpenAPI
- Docker
- Neon (Cloud PostgreSQL)
- Render (Deployment)

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

### Live API Example

Base URL:

`https://task-management-system-52tf.onrender.com`

Example requests:

- `GET https://task-management-system-52tf.onrender.com/api/tasks`
- `GET https://task-management-system-52tf.onrender.com/api/tasks/1`

For all available endpoints and interactive testing, visit the [Live Swagger UI](https://task-management-system-52tf.onrender.com/swagger-ui/index.html).

## Getting Started

### Prerequisites

- Java JDK 25
- PostgreSQL
- Git

### Database Setup

Create a PostgreSQL database for the application.

For local development, you can use:

- Database name: `task_management`
- Username: `postgres`
- Host: `localhost`
- Port: `5432`

For cloud deployment, the application uses PostgreSQL hosted on Neon.

### Environment Variables

The application reads database configuration from environment variables:

| Variable | Description |
|---|---|
| `DB_HOST` | PostgreSQL database host |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `PORT` | Application port (defaults to `8080`) |

Never commit database passwords or other secrets to the repository.

### Run the Application

Make sure the required database environment variables are configured before running the application.

On Linux and macOS:

```bash
export DB_HOST="localhost:5432"
export DB_USERNAME="postgres"
export DB_PASSWORD="your_password"

./mvnw spring-boot:run
```

On Windows:

```cmd
set DB_HOST=localhost:5432
set DB_USERNAME=postgres
set DB_PASSWORD=your_password

mvnw.cmd spring-boot:run
```

The application runs locally at:

`http://localhost:8080`

### API Documentation

After starting the application locally, access Swagger UI at:

`http://localhost:8080/swagger-ui/index.html`

## Deployment

- **Backend Hosting:** Render
- **Database Hosting:** Neon PostgreSQL
- **Containerization:** Docker

The application is configured to use environment variables for database credentials and the deployment platform's assigned port.

## Error Handling

The application provides consistent error responses for validation failures, invalid request data, and resources that cannot be found.

## Project Status

The core backend functionality has been implemented and tested.

The application has been successfully deployed to Render and connected to a Neon PostgreSQL database. Live API testing confirmed successful task creation and retrieval.

## Author

Yusuf Harby