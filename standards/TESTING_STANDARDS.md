# Testing Standards

This document defines the testing standards for all software projects.

Testing is not optional.

Every feature should be designed with testability in mind.

The objective is to build reliable, maintainable, and production-ready software through a comprehensive testing strategy.

---

# Core Principles

Testing should:

Increase confidence.

Prevent regressions.

Document expected behavior.

Support safe refactoring.

Improve software quality.

Reduce production defects.

---

# Testing Philosophy

Prefer preventing bugs over fixing bugs.

Write tests that verify behavior rather than implementation details.

Tests should be:

Reliable

Deterministic

Readable

Maintainable

Independent

Fast whenever possible

---

# Test Pyramid

Follow the Test Pyramid.

Prioritize:

1. Unit Tests

2. Integration Tests

3. End-to-End Tests

Avoid relying primarily on UI or E2E tests.

---

# Types of Testing

Consider:

Unit Testing

Integration Testing

Component Testing

Contract Testing

API Testing

Database Testing

Performance Testing

Load Testing

Stress Testing

Security Testing

Smoke Testing

Regression Testing

Acceptance Testing

Choose the appropriate type based on the feature.

---

# Unit Testing

Unit tests should:

Test one unit of behavior.

Be isolated.

Run quickly.

Avoid external dependencies.

Use mocks only when necessary.

Prefer constructor injection to improve testability.

---

# Integration Testing

Integration tests verify collaboration between components.

Examples:

Spring Boot

Database

Redis

Kafka

RabbitMQ

REST Clients

External APIs

Whenever practical, use real infrastructure through Testcontainers instead of heavy mocking.

---

# End-to-End Testing

Use E2E tests sparingly.

Cover critical user journeys.

Avoid testing every edge case with E2E tests.

---

# Test Naming

Test names should clearly describe behavior.

Prefer:

shouldReturnUserWhenIdExists

shouldThrowExceptionWhenEmailIsDuplicated

Avoid vague names.

---

# Test Structure

Prefer:

Arrange

Act

Assert

Keep tests focused on one behavior.

Avoid multiple unrelated assertions.

---

# Assertions

Assert observable behavior.

Avoid asserting implementation details.

Write meaningful assertion messages when helpful.

---

# Mocking

Mock only external dependencies.

Avoid mocking value objects or simple models.

Avoid excessive mocking.

Prefer real implementations when practical.

---

# Test Data

Use realistic test data.

Avoid magic values.

Prefer builders, factories, or fixtures.

Generate unique data when necessary.

---

# Database Testing

Test:

CRUD operations

Transactions

Constraints

Indexes (when relevant)

Migration compatibility

Query correctness

Prefer isolated databases using Testcontainers.

---

# API Testing

Verify:

Status Codes

Headers

Response Body

Validation

Authentication

Authorization

Error Responses

Pagination

Filtering

Sorting

Versioning

---

# Performance Testing

Measure:

Latency

Throughput

Resource Usage

Response Time

Concurrency

Benchmark before optimizing.

---

# Security Testing

Verify:

Authentication

Authorization

Input Validation

Injection Protection

Sensitive Data Exposure

Rate Limiting

Error Handling

---

# Testcontainers

Prefer Testcontainers for integration testing when infrastructure dependencies exist.

Examples:

PostgreSQL

MySQL

Redis

Kafka

RabbitMQ

MongoDB

Containers should be isolated and reproducible.

---

# Spring Boot Testing

Use:

@SpringBootTest

@WebMvcTest

@DataJpaTest

@JsonTest

@TestConfiguration

Select the narrowest test slice that satisfies the objective.

---

# Code Coverage

Code coverage is a metric, not a goal.

Do not chase 100% coverage blindly.

Prioritize meaningful coverage of business logic and critical paths.

Focus on quality over quantity.

---

# Edge Cases

Always consider:

Null values

Empty values

Boundary values

Large datasets

Duplicate data

Concurrent access

Invalid input

Unexpected failures

Timeouts

Retries

---

# Failure Testing

Verify application behavior during failures.

Examples:

Database unavailable

Redis unavailable

Kafka unavailable

Timeouts

External API failures

Network interruptions

Ensure graceful degradation where appropriate.

---

# Test Maintainability

Tests should be:

Easy to read

Easy to modify

Independent

Self-explanatory

Avoid duplication.

Extract reusable test utilities when appropriate.

---

# Continuous Integration

Tests should run automatically.

Failures must block deployment when appropriate.

Tests should produce deterministic results across environments.

---

# Documentation

Document:

Test strategy

Coverage goals

Testing tools

How to execute tests

Known limitations

---

# Test Review Checklist

When reviewing tests, evaluate:

Readability

Coverage

Correctness

Maintainability

Isolation

Performance

Determinism

Naming

Edge Case Coverage

Failure Scenarios

Production Relevance

---

# Learning Expectations

Whenever introducing a testing concept, explain:

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

Always recommend an appropriate testing strategy.

Generate tests that verify behavior, not implementation details.

Prefer realistic examples over trivial ones.

If multiple testing approaches exist, explain their trade-offs.

Recommend the smallest effective test scope while ensuring confidence.

Encourage writing tests as part of feature development rather than as an afterthought.

Your objective is to help produce software that is reliable, maintainable, and confidently deployable to production.
