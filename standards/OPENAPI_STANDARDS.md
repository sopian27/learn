# OpenAPI Standards

This document defines the standards for designing, generating, maintaining, and reviewing OpenAPI specifications.

These standards apply to all REST APIs regardless of programming language or framework.

The objective is to produce API documentation that serves as the single source of truth for API consumers, developers, testers, and operations teams.

---

# Core Principles

Always prioritize:

* Accuracy
* Consistency
* Clarity
* Completeness
* Maintainability
* Consumer Experience

API documentation is part of the product.

Documentation must always match the implementation.

---

# Documentation Philosophy

Every API should be understandable without reading the source code.

Consumers should know:

What the API does.

How to call it.

Authentication requirements.

Possible responses.

Validation rules.

Error responses.

Limitations.

---

# OpenAPI Version

Use the project-approved OpenAPI version.

Prefer OpenAPI 3.x or newer unless compatibility requirements dictate otherwise.

Document the OpenAPI version used by the project.

---

# API Information

Every specification should define:

Title

Description

Version

Contact

License (when applicable)

Terms of Service (when applicable)

Keep descriptions concise and meaningful.

---

# Server Definitions

Document all supported environments.

Examples:

Local

Development

Testing

Staging

Production

Clearly indicate environment-specific differences.

---

# Tags

Group endpoints by business capability.

Examples:

Users

Authentication

Payments

Orders

Reports

Avoid generic tags such as:

Misc

General

API

---

# Endpoint Documentation

Every endpoint should document:

Purpose

Business Description

HTTP Method

Path

Authentication Requirements

Authorization Requirements

Request Parameters

Request Body

Responses

Validation Rules

Error Responses

Examples

Known Limitations

---

# Path Parameters

Document:

Name

Type

Required

Description

Constraints

Example Value

---

# Query Parameters

Document:

Purpose

Type

Required

Default Value

Allowed Values

Validation Rules

Examples

---

# Request Body

Describe every field.

Include:

Type

Required

Nullable

Description

Validation Rules

Example

Default Value (when applicable)

Do not expose internal entities directly.

Use request DTOs.

---

# Response Body

Document every response field.

Include:

Type

Description

Nullable

Example

Business Meaning

Explain nested objects when necessary.

---

# Response Codes

Document all expected responses.

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

Do not document only successful responses.

---

# Error Responses

Provide a standardized error schema.

Include:

Error Code

Message

Timestamp

Trace ID

Correlation ID (when available)

Validation Errors (when applicable)

Examples

Document common error scenarios.

---

# Authentication

Document authentication requirements.

Examples:

JWT

OAuth2

API Key

Basic Authentication

Mutual TLS

Clearly specify which endpoints require authentication.

---

# Authorization

Document authorization requirements.

Examples:

Roles

Permissions

Scopes

Access Rules

---

# Pagination

Document:

Pagination Method

Page Parameters

Sorting

Filtering

Total Count (when applicable)

Cursor Format (if used)

Provide example requests and responses.

---

# Filtering

Document:

Supported Filters

Operators

Case Sensitivity

Allowed Values

Examples

Reject unsupported filters.

---

# Sorting

Document:

Sortable Fields

Default Sort

Direction

Examples

---

# Versioning

Document API versioning strategy.

Examples:

URI Versioning

Header Versioning

Media Type Versioning

Explain deprecation policy.

---

# Examples

Every endpoint should include:

Request Example

Successful Response Example

Validation Failure Example

Authorization Failure Example

Business Error Example

Examples should represent realistic production scenarios.

---

# Schema Reuse

Reuse schemas whenever practical.

Avoid duplicated model definitions.

Prefer reusable components.

---

# Enums

Document every enum value.

Explain business meaning.

Provide examples.

---

# File Upload

Document:

Content Type

Maximum Size

Allowed Extensions

Validation Rules

Example Request

---

# Rate Limiting

Document:

Limits

Burst Capacity

Headers

Retry Behavior

Responses when limits are exceeded.

---

# Webhooks

When supported, document:

Trigger

Payload

Retry Behavior

Authentication

Delivery Guarantees

Failure Handling

---

# Security

Never expose:

Internal IDs (unless intended)

Sensitive Fields

Secrets

Implementation Details

Internal Error Messages

---

# OpenAPI Quality

Ensure the specification is:

Valid

Complete

Consistent

Human-readable

Machine-readable

Suitable for client code generation.

---

# Synchronization

Documentation must remain synchronized with implementation.

Documentation updates are required whenever API behavior changes.

---

# Testing

Validate:

OpenAPI Schema

Examples

Generated Documentation

Request Validation

Response Validation

Generated Clients (when applicable)

---

# Documentation Review Checklist

When reviewing OpenAPI documentation, evaluate:

Completeness

Accuracy

Consistency

Examples

Validation Rules

Error Responses

Authentication

Authorization

Schema Reuse

Versioning

Consumer Experience

Production Readiness

---

# Learning Expectations

Whenever introducing an OpenAPI concept, explain:

What it is.

Why it exists.

When to use it.

When not to use it.

Advantages.

Disadvantages.

Trade-offs.

Real-world examples.

Common mistakes.

Best practices.

---

# AI Behavior

When generating OpenAPI documentation:

Do not simply mirror source code.

Understand the business purpose of the API first.

Generate documentation that is useful for API consumers, QA engineers, frontend developers, integration teams, and operations.

Always include meaningful descriptions, realistic examples, validation rules, authentication requirements, and error scenarios.

If implementation details are incomplete or ambiguous, ask clarifying questions rather than making assumptions.

Your objective is not merely to generate an OpenAPI specification, but to create documentation that can serve as the authoritative contract between API providers and API consumers throughout the lifecycle of the application.
