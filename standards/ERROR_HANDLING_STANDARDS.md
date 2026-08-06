# Error Handling Standards

This document defines the standards for detecting, handling, reporting, logging, and recovering from errors.

These standards apply to all backend services, APIs, batch jobs, event consumers, scheduled tasks, and integrations.

The objective is to build systems that fail predictably, recover gracefully, and provide actionable information for both users and engineers.

---

# Core Principles

Always prioritize:

* Correctness
* Clarity
* Observability
* Maintainability
* Reliability
* User Experience

Errors are expected.

Design systems to handle failures gracefully.

Never assume everything will succeed.

---

# Error Handling Philosophy

An error handling strategy should:

Prevent data corruption.

Provide meaningful feedback.

Preserve system stability.

Enable troubleshooting.

Support monitoring and alerting.

Avoid hiding failures.

---

# Types of Errors

Classify errors into categories.

Examples:

Validation Errors

Business Rule Errors

Authentication Errors

Authorization Errors

External Service Errors

Database Errors

Network Errors

Timeout Errors

Concurrency Errors

Configuration Errors

Infrastructure Errors

Unexpected System Errors

Choose the appropriate handling strategy for each category.

---

# Exceptions

Throw exceptions only when appropriate.

Exceptions should represent exceptional situations.

Do not use exceptions for normal control flow.

Prefer meaningful exception names.

Examples:

UserNotFoundException

DuplicateEmailException

PaymentFailedException

InvalidConfigurationException

Avoid generic exceptions whenever possible.

---

# Exception Hierarchy

Organize exceptions logically.

Examples:

ApplicationException

BusinessException

ValidationException

InfrastructureException

ExternalServiceException

DatabaseException

Avoid deep inheritance hierarchies.

---

# Checked vs Unchecked Exceptions

Understand the trade-offs.

Use checked exceptions only when callers are expected to recover.

Prefer unchecked exceptions for programming errors and unrecoverable application failures.

Document the chosen approach.

---

# Validation Errors

Validate input at system boundaries.

Return meaningful validation messages.

Report all relevant validation issues when appropriate.

Avoid exposing implementation details.

---

# Business Errors

Business rule violations are expected outcomes.

Return clear messages.

Avoid treating business rule failures as system failures.

---

# API Error Responses

Return consistent error responses.

Include:

Timestamp

HTTP Status

Error Code

Message

Path

Trace ID

Correlation ID (when available)

Field Errors (for validation)

Do not expose stack traces or internal implementation details.

---

# Error Codes

Define stable application-specific error codes.

Examples:

USER_NOT_FOUND

INVALID_REQUEST

PAYMENT_DECLINED

RESOURCE_ALREADY_EXISTS

DATABASE_UNAVAILABLE

Error codes should remain stable across versions when possible.

---

# Logging

Log enough information to diagnose problems.

Include:

Trace ID

Correlation ID

Request ID

User ID (when appropriate)

Operation

Execution Time

Exception Type

Message

Root Cause

Never log:

Passwords

Secrets

Access Tokens

Refresh Tokens

Sensitive Personal Information

---

# Exception Logging

Log exceptions once at the appropriate layer.

Avoid duplicate logging of the same exception.

Always preserve the original cause.

---

# Root Cause Preservation

Never discard the original exception.

Wrap exceptions only when additional context is required.

Preserve stack traces.

---

# Retry Strategy

Retry only transient failures.

Examples:

Temporary Network Failure

Timeout

Service Unavailable

Avoid retrying:

Validation Errors

Business Rule Violations

Authentication Failures

Authorization Failures

Use exponential backoff with limits.

---

# Circuit Breakers

Protect integrations with external services.

Consider:

Timeout

Retry

Circuit Breaker

Fallback

Bulkhead

Explain trade-offs.

---

# Timeouts

Every remote call should define an appropriate timeout.

Avoid infinite waiting.

Document timeout values.

---

# Fallbacks

Fallback behavior should be explicit.

Examples:

Cached Data

Default Value

Graceful Degradation

Alternative Service

Never hide critical failures with silent fallbacks.

---

# Database Errors

Handle:

Constraint Violations

Deadlocks

Connection Failures

Lock Timeouts

Duplicate Keys

Differentiate transient failures from permanent failures.

---

# Messaging Errors

Consumers should:

Handle malformed messages.

Retry transient failures.

Route poison messages to a Dead Letter Queue.

Log sufficient context for investigation.

---

# Scheduled Jobs

Scheduled tasks should:

Continue processing independent work when possible.

Log failures.

Record execution status.

Support retries when appropriate.

Avoid silent failures.

---

# Batch Processing

Handle individual record failures separately when possible.

Continue processing remaining records if business rules allow.

Generate failure reports.

---

# User-Facing Messages

Messages shown to users should be:

Clear

Actionable

Non-technical

Never expose internal implementation details.

Provide technical details only in logs.

---

# Security

Do not reveal:

SQL Statements

Internal File Paths

Server Information

Framework Versions

Stack Traces

Sensitive Configuration

Use generic messages when necessary.

---

# Monitoring

Monitor:

Error Rate

Exception Types

HTTP 5xx

Retry Counts

Timeouts

Circuit Breaker Status

DLQ Size

Alert on abnormal trends.

---

# Testing

Verify:

Validation Errors

Business Errors

Infrastructure Failures

Database Failures

Timeouts

Retries

Fallbacks

Circuit Breakers

Unexpected Exceptions

Recovery Behavior

---

# Documentation

Document:

Exception Hierarchy

Error Codes

HTTP Error Responses

Retry Policies

Fallback Strategies

Operational Runbooks

Known Failure Modes

---

# Error Review Checklist

When reviewing error handling, evaluate:

Correct Exception Type

Meaningful Messages

Appropriate Logging

Security

Retry Logic

Timeouts

Fallback Strategy

Observability

Maintainability

Consistency

Production Readiness

---

# Learning Expectations

Whenever introducing an error handling concept, explain:

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

When generating code:

Design error handling before writing business logic.

Use domain-specific exceptions.

Recommend centralized exception handling where appropriate (for example, a global exception handler in web applications).

Always justify retry, timeout, fallback, and circuit breaker decisions.

If an error handling strategy depends on business criticality or consistency requirements, explicitly state those assumptions.

Your objective is not merely to catch exceptions, but to build systems that fail safely, are easy to debug, and provide a consistent experience for users and operators.
