#!/bin/bash
# Script to test the API endpoints in the Docker container
# Updated to use the optimized endpoints that support shell scripts
# NOTE: The customerNumber 318411216 is pre-configured for mocked transaction data.
#       Use this customer number to ensure all endpoints (including transaction-data) work properly.
#       You can try a different customer number to test subscription, loan request, and loan status,
#       but it won't work for transaction data.

BASE_URL="http://localhost:8080/api/v1"
CUSTOMER_NUMBER=1184112162
LOAN_AMOUNT=15000
CUSTOMER_NAME=TestUser2

echo "==========================================="
echo "Subscribing customer $CUSTOMER_NUMBER..."
echo "==========================================="
# Using the subscribe-params endpoint specifically designed for shell scripts
curl -X POST "$BASE_URL/subscribe-params?customerNumber=$CUSTOMER_NUMBER&name=$CUSTOMER_NAME"
echo -e "\n"

echo "==========================================="
echo "Testing loan status for $CUSTOMER_NUMBER..."
echo "==========================================="
# Using the loanStatus endpoint with query parameters
curl "$BASE_URL/loanStatus?customerNumber=$CUSTOMER_NUMBER"
echo -e "\n"

echo "==========================================="
echo "Getting KYC info for $CUSTOMER_NUMBER..."
echo "==========================================="
# Adding KYC endpoint test
curl "$BASE_URL/kyc?customerNumber=$CUSTOMER_NUMBER"
echo -e "\n"

echo "==========================================="
echo "Getting transaction data for $CUSTOMER_NUMBER..."
echo "==========================================="
# Testing transaction data endpoint
curl "$BASE_URL/transaction-data?customerNumber=$CUSTOMER_NUMBER"
echo -e "\n"

echo "==========================================="
echo "Requesting a loan of $LOAN_AMOUNT for $CUSTOMER_NUMBER..."
echo "==========================================="
# Using the loanRequest endpoint with query parameters
curl -X POST "$BASE_URL/loanRequest?customerNumber=$CUSTOMER_NUMBER&amount=$LOAN_AMOUNT"
echo -e "\n"

echo "==========================================="
echo "Checking updated loan status for $CUSTOMER_NUMBER..."
echo "==========================================="
# Checking loan status again after requesting a loan
curl "$BASE_URL/loanStatus?customerNumber=$CUSTOMER_NUMBER"
echo -e "\n"

echo "==========================================="
echo "Testing loan duplication - Requesting another loan for $CUSTOMER_NUMBER..."
echo "This should fail with 'A pending loan already exists' message"
echo "==========================================="
# Testing loan duplication scenario - should get rejection message
curl -X POST "$BASE_URL/loanRequest?customerNumber=$CUSTOMER_NUMBER&amount=$LOAN_AMOUNT"
echo -e "\n"
