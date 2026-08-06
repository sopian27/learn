# API Standards

This document defines the standards for designing, implementing, documenting, securing, testing, and maintaining APIs.

These standards apply to all backend services unless project-specific requirements explicitly override them.

The objective is to build APIs that are consistent, secure, scalable, maintainable, and developer-friendly.

---

# Core Principles

Every API should be:

* Consistent
* Predictable
* Secure
* Performant
* Versionable
* Easy to understand
* Easy to maintain
* Well documented

Always optimize for long-term maintainability.

---

# API Design Philosophy

Design APIs from the consumer's perspective.

Prioritize simplicity.

Avoid unnecessary complexity.

Prefer consistency over creativity.

Whenever multiple valid approaches exist, explain the trade-offs.

---

# REST Principles

Whenever applicable:

Use RESTful design.

Resources should represent business entities.

Avoid RPC-style endpoints unless justified.

Good:

GET /users

POST /users

GET /users/{id}

PUT /users/{id}

PATCH /users/{id}

DELETE /users/{id}

Avoid:

POST /createUser

GET /getUsers

POST /deleteUser

---

# URI Naming

Use:

lowercase

hyphen-separated when needed

plural resources

Examples:

/users

/user-profiles

/payment-transactions

Avoid:

camelCase

PascalCase

verbs in URLs

---

# HTTP Methods

GET

Retrieve data.

Must not modify data.

POST

Create resources.

May trigger business processes.

PUT

Replace an entire resource.

PATCH

Partially update a resource.

DELETE

Delete resources.

Explain why a specific HTTP method is chosen.

---

# HTTP Status Codes

Use appropriate status codes.

Examples:

200 OK

201 Created

202 Accepted

204 No Content

400 Bad Request

401 Unauthorized

403 Forbidden

404 Not Found

409 Conflict

422 Unprocessable Entity

429 Too Many Requests

500 Internal Server Error

503 Service Unavailable

Avoid always returning HTTP 200.

---

# Request Design

Validate all incoming requests.

Prefer JSON.

Use DTOs.

Avoid exposing internal entities.

Validate:

Required fields

Length

Range

Format

Business rules

---

# Response Design

Responses should be consistent.

Prefer a standard response format.

Example:

success

message

data

metadata

timestamp

traceId

Do not leak internal implementation details.

---

# Error Handling

Use structured error responses.

Include:

Error Code

Message

Timestamp

Trace ID

Path

Details (when appropriate)

Never expose stack traces.

---

# Validation

Validate at API boundaries.

Use Bean Validation when applicable.

Return meaningful validation errors.

---

# Pagination

Support pagination for list endpoints.

Prefer:

page

size

sort

or

cursor-based pagination for large datasets.

Always document pagination behavior.

---

# Filtering

Support filtering where appropriate.

Examples:

status

createdFrom

createdTo

keyword

Avoid creating unnecessary endpoints for simple filters.

---

# Sorting

Support sorting using query parameters.

Document allowed fields.

Reject unsupported sort fields.

---

# Searching

Provide flexible search capabilities when appropriate.

Explain search behavior clearly.

Document case sensitivity if applicable.

---

# Versioning

Support API versioning.

Examples:

/v1/users

/v2/users

Document breaking changes.

Avoid unnecessary version increments.

---

# Idempotency

Explain whether endpoints are idempotent.

For operations such as payments or external integrations, consider idempotency keys.

---

# Authentication

Never expose protected resources without authentication.

Support project-specific authentication mechanisms.

Examples:

JWT

OAuth2

API Keys

Session-based authentication

Document authentication requirements.

---

# Authorization

Validate permissions.

Do not rely only on frontend authorization.

Always enforce authorization on the server.

---

# Security

Validate all input.

Protect against:

SQL Injection

XSS

CSRF (when applicable)

Mass Assignment

Broken Authentication

Broken Authorization

Rate limit abuse

Sensitive Data Exposure

Follow OWASP API Security best practices.

---

# Performance

Avoid over-fetching.

Avoid under-fetching.

Minimize payload size.

Compress responses when appropriate.

Cache responses where appropriate.

Document caching behavior.

---

# Caching

Clearly define:

Cacheable endpoints

Cache duration

Invalidation strategy

ETag

Last-Modified

Cache-Control

when applicable.

---

# File Upload

Validate:

File size

File type

Virus scanning (when required)

Storage strategy

Never trust client-provided metadata.

---

# Asynchronous APIs

When long-running operations exist:

Prefer asynchronous processing.

Return:

202 Accepted

Provide status endpoints when appropriate.

---

# Logging

Log:

Request ID

Trace ID

User ID (when appropriate)

Execution time

Errors

Never log:

Passwords

Tokens

Secrets

Sensitive personal data

---

# Observability

Support:

Trace IDs

Correlation IDs

Metrics

Health Checks

Readiness Checks

Structured Logging

---

# Documentation

Every API must include:

Purpose

Authentication

Request

Response

Status Codes

Validation Rules

Examples

Error Responses

Rate Limits

Version

Known Limitations

---

# OpenAPI

Maintain accurate OpenAPI documentation.

Keep documentation synchronized with implementation.

Include examples whenever possible.

---

# Testing

Test:

Success scenarios

Validation failures

Authorization failures

Authentication failures

Edge cases

Performance

Integration

Regression

---

# Deprecation

When deprecating endpoints:

Document deprecation.

Provide migration guidance.

Communicate removal timelines.

Avoid breaking consumers unexpectedly.

---

# API Review Checklist

When reviewing an API, evaluate:

RESTfulness

Naming

Consistency

Validation

Security

Performance

Documentation

Error Handling

Versioning

Pagination

Filtering

Maintainability

Backward Compatibility

Production Readiness

---

# Learning Expectations

Whenever introducing an API concept, explain:

What it is.

Why it exists.

When to use it.

When not to use it.

Advantages.

Disadvantages.

Trade-offs.

Production examples.

Common mistakes.

Best practices.

---

# AI Behavior

When generating APIs:

Do not generate endpoints blindly.

Understand the business domain first.

Ask clarifying questions if requirements are incomplete.

Always justify design decisions.

Recommend improvements based on scalability, maintainability, and developer experience.

Your goal is not simply to generate endpoints, but to design APIs that are intuitive, robust, secure, and production-ready.
