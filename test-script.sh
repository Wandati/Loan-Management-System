#!/bin/bash

# Script to test the API endpoints in the Docker container

# NOTE: The customerNumber 318411216 is pre-configured for mocked transaction data.
#       Use this customer number to ensure all endpoints (including transaction-data) work properly.
#       You can try a different customer number to test the subscription,loan request and loan status but it wont work for transaction data 

CUSTOMER_NUMBER=318411216
LOAN_AMOUNT=1500

echo "==========================================="
echo "Subscribing customer $CUSTOMER_NUMBER..."
echo "==========================================="
curl -X POST "http://localhost:8080/api/subscribe?customerNumber=$CUSTOMER_NUMBER"
echo -e "\n"

echo "==========================================="
echo "Testing loan status for $CUSTOMER_NUMBER..."
echo "==========================================="
curl "http://localhost:8080/api/loanStatus?customerNumber=$CUSTOMER_NUMBER"
echo -e "\n"

echo "==========================================="
echo "Requesting a loan of $LOAN_AMOUNT for $CUSTOMER_NUMBER..."
echo "==========================================="
curl -X POST "http://localhost:8080/api/loanRequest?customerNumber=$CUSTOMER_NUMBER&amount=$LOAN_AMOUNT"
echo -e "\n"

echo "==========================================="
echo "Checking loan status after loan request..."
echo "==========================================="
curl "http://localhost:8080/api/loanStatus?customerNumber=$CUSTOMER_NUMBER"
echo -e "\n"

echo "==========================================="
echo "Testing duplicate loan request (should fail)..."
echo "==========================================="
curl -X POST "http://localhost:8080/api/loanRequest?customerNumber=$CUSTOMER_NUMBER&amount=$LOAN_AMOUNT"
echo -e "\n"

echo "==========================================="
echo "Testing transaction data retrieval (mocked)..."
echo "==========================================="
curl "http://localhost:8080/api/transaction-data?customerNumber=$CUSTOMER_NUMBER"
echo -e "\n"

