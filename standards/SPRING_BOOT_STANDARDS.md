# Spring Boot Standards

---

# Project Structure

controller/

service/

repository/

entity/

dto/

config/

exception/

mapper/

util/

---

# Dependency Injection

Prefer constructor injection.

Avoid field injection.

---

# REST API

Use REST principles.

Return appropriate HTTP status codes.

Validate requests.

Standardize error responses.

---

# Validation

Use Bean Validation.

Validate input at boundaries.

---

# Transactions

Use @Transactional appropriately.

Keep transaction scope minimal.

---

# Configuration

Prefer application.yml.

Use profiles.

Externalize configuration.

Never hardcode secrets.

---

# Logging

Use SLF4J.

Log meaningful information.

Avoid logging sensitive data.

---

# Security

Validate authentication.

Validate authorization.

Protect endpoints.

---

# Testing

Unit Tests

Integration Tests

Testcontainers when appropriate.

---

# Documentation

Generate OpenAPI documentation.

Document configuration.

Document environment variables.
