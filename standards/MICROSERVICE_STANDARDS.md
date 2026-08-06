# Microservice Standards

This document defines the standards for designing, implementing, deploying, operating, and evolving microservices.

These standards apply to all microservice-based systems regardless of programming language, framework, cloud provider, or messaging platform.

The objective is to build services that are loosely coupled, highly cohesive, independently deployable, scalable, resilient, observable, maintainable, and aligned with business domains.

---

# Core Principles

Always prioritize:

* Business Alignment
* High Cohesion
* Loose Coupling
* Simplicity
* Reliability
* Scalability
* Observability
* Maintainability

A microservice is not defined by its size.

A microservice is defined by its business responsibility.

---

# Microservice Philosophy

Split services by business capability, not by technical layers.

Each service should own one business domain.

Avoid creating services simply because microservices are fashionable.

A monolith is often the correct solution until clear business boundaries emerge.

---

# Service Boundaries

Use business domains as service boundaries.

Examples:

Authentication

Payment

Order

Inventory

Notification

Reporting

Customer

Recommendation

Avoid splitting services by:

Controllers

Repositories

Database Tables

Programming Languages

---

# Single Responsibility

Each service should have one primary business responsibility.

If a service has multiple unrelated responsibilities, reconsider its boundaries.

---

# Bounded Context

Respect bounded contexts.

Each service owns:

Business Rules

Data Model

Database

APIs

Events

Avoid leaking internal concepts across service boundaries.

---

# Database Ownership

Every microservice owns its database.

Never allow multiple services to write directly to the same database.

Database sharing creates tight coupling.

Communicate through APIs or events.

---

# Communication Patterns

Choose communication based on business requirements.

Examples:

REST

gRPC

Messaging

Event Streaming

Request-Reply

Publish-Subscribe

Avoid synchronous communication for every interaction.

Prefer asynchronous communication when appropriate.

---

# Synchronous Communication

Use synchronous communication when:

Immediate responses are required.

User interaction depends on the result.

Consistency requirements justify it.

Avoid long dependency chains.

---

# Asynchronous Communication

Use messaging when:

Loose coupling is desired.

High scalability is required.

Eventual consistency is acceptable.

Workflows span multiple services.

Document consistency expectations.

---

# API Design

APIs should expose business capabilities.

Avoid exposing internal implementation details.

Version APIs carefully.

Document all public APIs.

---

# Event Design

Events describe completed business actions.

Examples:

OrderCreated

PaymentCompleted

ShipmentDelivered

Events should be immutable.

Version event schemas when necessary.

---

# Service Contracts

Treat APIs and events as contracts.

Changes should preserve backward compatibility whenever practical.

Document breaking changes.

---

# Data Consistency

Accept that distributed systems often require eventual consistency.

Avoid distributed transactions.

Prefer:

Saga Pattern

Outbox Pattern

Compensating Transactions

Explain consistency trade-offs.

---

# Idempotency

Operations that may be retried should be idempotent.

Support:

Idempotency Keys

Unique Event IDs

Duplicate Detection

Document the strategy.

---

# Service Discovery

Use service discovery mechanisms rather than hardcoded addresses.

Examples:

Kubernetes Services

Consul

Eureka (legacy)

DNS

---

# Configuration

Externalize configuration.

Support environment-specific configuration.

Do not hardcode endpoints or credentials.

---

# Security

Protect service-to-service communication.

Support:

TLS

mTLS (when appropriate)

OAuth2

JWT

API Gateway

Least Privilege

Never expose internal services directly unless required.

---

# API Gateway

Use an API Gateway when appropriate.

Responsibilities may include:

Authentication

Authorization

Rate Limiting

Routing

Observability

Avoid placing business logic inside the gateway.

---

# Resiliency

Every service should prepare for dependency failures.

Support:

Timeouts

Retries

Circuit Breakers

Fallbacks

Bulkheads

Graceful Degradation

Avoid cascading failures.

---

# Scalability

Design services to scale independently.

Prefer stateless services.

Avoid unnecessary shared state.

Support horizontal scaling.

---

# Observability

Every service should expose:

Structured Logs

Metrics

Distributed Traces

Health Checks

Business Metrics

Propagate Trace IDs and Correlation IDs across service boundaries.

---

# Deployment

Services should be independently deployable.

Avoid coordinated deployments whenever possible.

Support rolling updates and rollback.

---

# Versioning

Version:

APIs

Events

Schemas

Configuration

Deprecate old versions gradually.

Communicate breaking changes.

---

# Database Migrations

Each service manages its own migrations.

Coordinate schema evolution with application deployment.

Prefer backward-compatible migrations.

---

# Performance

Measure:

Latency

Throughput

Error Rate

Dependency Calls

Database Queries

Cache Performance

Message Processing

Optimize based on evidence.

---

# Testing

Test:

Unit

Integration

Contract

End-to-End

Consumer-Driven Contracts (when applicable)

Resiliency

Failure Scenarios

Performance

Security

Test services independently whenever possible.

---

# Monitoring

Monitor:

Availability

Latency

Error Rate

Resource Usage

Queue Lag

Retries

Timeouts

Circuit Breakers

Dependency Health

Business KPIs

Create actionable alerts.

---

# Documentation

Document:

Business Capability

Architecture

Dependencies

Public APIs

Events

Database Ownership

Deployment Process

Operational Runbooks

Failure Modes

Recovery Procedures

Known Limitations

---

# Anti-Patterns

Avoid:

Shared Databases

God Services

Chatty APIs

Distributed Monoliths

Hardcoded Endpoints

Circular Dependencies

Synchronous Chains

Business Logic in API Gateway

Premature Service Decomposition

---

# Review Checklist

When reviewing a microservice, evaluate:

Business Boundary

Service Responsibility

API Design

Event Design

Database Ownership

Resiliency

Security

Observability

Scalability

Performance

Maintainability

Deployment Independence

Production Readiness

---

# Learning Expectations

Whenever introducing a microservice concept, explain:

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

Compare it with a modular monolith when appropriate.

---

# AI Behavior

When recommending a microservice architecture:

Do not recommend microservices by default.

First understand:

Business domains.

Team structure.

Deployment frequency.

Scalability requirements.

Operational maturity.

Data consistency requirements.

Prefer the simplest architecture that satisfies the current business needs.

Recommend modular monoliths when they are a better fit.

When microservices are appropriate, justify service boundaries, communication patterns, data ownership, resiliency mechanisms, and operational considerations.

If recommendations depend on organizational size, traffic patterns, compliance requirements, or infrastructure capabilities, explicitly state those assumptions.

Your objective is not merely to split an application into services, but to design distributed systems that are understandable, resilient, scalable, maintainable, and aligned with business capabilities throughout their lifecycle.
