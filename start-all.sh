#!/bin/bash

# Start Service Discovery
# shellcheck disable=SC2164
cd "Service Discovery"
mvn spring-boot:run &
cd ..

# Start Api Gateway
cd "Api Gateway"
mvn spring-boot:run &
cd ..

# Start Product Service
cd "Product Service"
mvn spring-boot:run &
cd ..

# Start Agent Service
cd "Agent Service"
mvn spring-boot:run &
cd ..
