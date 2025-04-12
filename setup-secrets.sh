#!/bin/bash

# Create secrets directory if it doesn't exist
mkdir -p secrets

# Create database user secret
echo "lmsuser" > secrets/db_user.txt

# Create database password secret (generate a secure password)
openssl rand -base64 12 > secrets/db_password.txt

echo "Secrets have been created:"
echo "- Database user is stored in secrets/db_user.txt"
echo "- A secure database password has been generated in secrets/db_password.txt"
echo ""
echo "These credentials will be securely passed to your Docker containers."
echo "NOTE: In a production environment, use a secrets management service like Vault or AWS Secrets Manager."
