### README.md

# Human Resources Management System (HRMS)

## Overview

This Human Resources Management System (HRMS) is designed to automate the accounting and management of human resources within an enterprise. The system aims to provide efficient personnel management, increase productivity, and reduce administrative costs. It includes functionalities for user authorization, data access restrictions and personnel data management. This project is a bachelor's degree qualification work.

## Technologies Used

- Spring Boot
- Spring Security
- PostgreSQL
- Maven
- ModelMapper
- Angular
- Docker
- Swagger OpenAPI

## Prerequisites

- JDK
- Node.js and npm
- Angular CLI
- Docker

## Setup

### 1. Clone the Repository

```sh
git clone https://github.com/HarkushaVlad/HR-forge.git
cd HR-forge
```

### 2. Configure Secrets

Create the necessary secret files in the `secrets/` directory:

```sh
cd secrets
echo "your_database_name" > db_name.txt
echo "your_database_user" > db_user.txt
echo "your_database_password" > db_password.txt
cd ..
```

### 3. Update Environment Properties

Create and edit the `env.properties` file in `HR-forge-api/src/main/resources/`:

```sh
cd HR-forge-api/src/main/resources/
touch env.properties
echo "DB_URL=jdbc:postgresql://localhost:5430/your_database_name" >> env.properties
echo "DB_USERNAME=your_database_user" >> env.properties
echo "DB_PASSWORD=your_database_password" >> env.properties
echo "JWT_SECRET_KEY=your_secret_key" >> env.properties
cd ../../../..
```

### 4. Setup and Run the Database

Ensure you have Docker installed and running. Use Docker Compose to start the PostgreSQL database:

```sh
docker-compose up -d
```

### 5. Restore the Database

To restore the database from the backup file, run the following command:

```sh
docker exec -i postgres-db-hr-forge psql -U your_database_user -d your_database_name < database/backup.sql
```
**Note:** The backup database contains a system administrator account with the login `myk.pav@example.com` and the password `qwerty09`. All accounts in the demo database use the password `qwerty09`.

### 6. Run the Backend

Navigate to the backend directory and start the Spring Boot application:

```sh
cd HR-forge-api
./mvnw spring-boot:run
cd ..
```

### 7. Run the Frontend

Navigate to the frontend directory and start the Angular application:

```sh
cd HR-forge-ui
npm install
ng serve
cd ..
```