#!/bin/bash

# Script to test the API endpoints in the Docker container

# Subscribe the customer first
echo "Subscribing customer 366585630..."
curl -X POST "http://localhost:8080/api/subscribe?customerNumber=366585630"
echo -e "\n"

# Test customer subscription status
echo "Testing customer status for 366585630..."
curl "http://localhost:8080/api/loanStatus?customerNumber=366585630"
echo -e "\n"

# Test loan request
echo "Testing loan request for 366585630..."
curl -X POST "http://localhost:8080/api/loanRequest?customerNumber=366585630&amount=1500"
echo -e "\n"

# Test loan status after request
echo "Testing loan status for 366585630 after loan request..."
curl "http://localhost:8080/api/loanStatus?customerNumber=366585630"
echo -e "\n"

# Test duplicate loan request (should fail with "pending loan exists")
echo "Testing duplicate loan request (should fail)..."
curl -X POST "http://localhost:8080/api/loanRequest?customerNumber=366585630&amount=1500"
echo -e "\n"

