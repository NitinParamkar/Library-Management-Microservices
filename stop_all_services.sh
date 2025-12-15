#!/bin/bash

echo "Stopping Library Management Microservices..."

# List of ports used by the services
# 8761: Discovery Service
# 8080: API Gateway
# 8081: Book Service
# 8082: Student Service
# 8083: Borrow Issue Service
PORTS=(8761 8080 8081 8082 8083)

for port in "${PORTS[@]}"; do
    pid=$(lsof -t -i:$port)
    if [ -n "$pid" ]; then
        echo "Killing process on port $port (PID: $pid)..."
        kill -9 $pid
    else
        echo "No process found on port $port."
    fi
done

echo "🛑 All services stopped."
