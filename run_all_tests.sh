#!/bin/bash

echo "Running All Test Cases..."

# Function to run tests
run_tests() {
    service_name=$1
    echo "--------------------------------------------------"
    echo "Testing $service_name..."
    echo "--------------------------------------------------"
    cd $service_name
    mvn test
    if [ $? -eq 0 ]; then
        echo "✅ $service_name Tests Passed"
    else
        echo "❌ $service_name Tests Failed"
        exit 1
    fi
    cd ..
    echo ""
}

# Run tests for each service
run_tests "bookService"
run_tests "studentService"
run_tests "borrowIssueService"

echo "--------------------------------------------------"
echo "🎉 All Test Suites executed successfully!"
echo "--------------------------------------------------"
