# Library Management Microservices - Test Instructions

## Overview
This project contains a set of microservices for a Library Management System. We have implemented a complete automated test suite covering the **Repository**, **Service**, and **Controller** layers.

## Prerequisites
Before running the tests, ensure the following are installed and running:
1.  **Java** (JDK 21 or compatible)
2.  **Maven**
3.  **MySQL Database**
    -   Host: `localhost`
    -   Port: `3306`
    -   Database Name: `library`
    -   Username: `root`
    -   Password: *(empty)*

> **Important**: The repository tests run against the **local MySQL database**. Ensure the `library` database exists. Use your preferred tool (e.g., phpMyAdmin) to create it if it doesn't exist.

## Project Structure
-   **bookService**: Manages book inventory.
-   **studentService**: Manages student records.
-   **borrowIssueService**: Manages book issuing and returning.

## Running the Tests

You can run the tests for each microservice using Maven. Open a terminal in the project root directory.

### 1. Run Book Service Tests
```bash
cd bookService
mvn test
```
**Expected Output:**
-   `BookRepositoryTest`: Verifies CRUD operations against the local `library` DB.
-   `BookServiceTest`: Unit tests using Mockito.
-   `BookControllerTest`: Integration tests for REST endpoints.

### 2. Run Student Service Tests
Return to the root directory and run:
```bash
cd ../studentService
mvn test
```
**Expected Output:**
-   `StudentRepositoryTest`: Verifies persistence in the local `library` DB.
-   `StudentServiceTest`: Unit tests using Mockito.
-   `StudentControllerTest`: Integration tests for REST endpoints.

### 3. Run Borrow Issue Service Tests
Return to the root directory and run:
```bash
cd ../borrowIssueService
mvn test
```
**Expected Output:**
-   `BorrowRepositoryTest`: Verifies issue/return records in the local `library` DB.
-   `BorrowServiceTest`: Unit tests using Mockito.
-   `BorrowControllerTest`: Integration tests for REST endpoints.

## Automation Scripts
We have provided two scripts in the root directory to simplify running services and tests.

### 1. Run All Services
To start all microservices (Eureka, API Gateway, Book, Student, BorrowIssue) in the background:
```bash
./run_all_services.sh
```
*   Logs for each service will be written to `<service_name>.log` files.
*   The script waits for Eureka to initialize before starting other services.

### 2. Stop All Services
To stop all running microservices:
```bash
./stop_all_services.sh
```
*   This script finds processes running on the service ports (8761, 8080, 8081, 8082, 8083) and kills them.

### 3. Run All Tests
To execute the entire test suite for all services:
```bash
./run_all_tests.sh
```
*   This will run `mvn test` for each service sequentially.
*   It provides a summary of pass/fail status.

## Test Reports
After running `mvn test` or `./run_all_tests.sh`, you can find detailed test reports (including standard output and potential errors) in the `target/surefire-reports` directory inside each service folder.
