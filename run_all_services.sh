#!/bin/bash

echo "Starting Library Management Microservices..."

# Function to run a service
run_service() {
    service_name=$1
    port=$2
    echo "Starting $service_name on port $port..."
    cd $service_name
    mvn spring-boot:run > ../$service_name.log 2>&1 &
    cd ..
}

# 1. Start Discovery Service (Eureka)
run_service "discoveryService" 8761
echo "Waiting 20 seconds for Discovery Service to Initialize..."
sleep 20

# 2. Start API Gateway
run_service "apiGateWay" 8080
sleep 5

# 3. Start Core Services
run_service "bookService" 8081
run_service "studentService" 8082
run_service "borrowIssueService" 8083

echo "All services are starting in the background."
echo "Logs are being written to <service_name>.log files in this directory."
echo "Access Eureka Dashboard at http://localhost:8761"
echo "Access API Gateway at http://localhost:8080"
