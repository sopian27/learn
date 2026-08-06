# Resiliency Standards

This document defines the standards for designing systems that continue to operate reliably in the presence of failures.

These standards apply to APIs, microservices, databases, messaging systems, scheduled jobs, distributed systems, cloud-native applications, and infrastructure integrations.

The objective is to build systems that are fault-tolerant, recover gracefully, minimize user impact, and remain maintainable under production workloads.

---

# Core Principles

Always prioritize:

* Reliability
* Availability
* Recoverability
* Graceful Degradation
* Observability
* Simplicity

Failures are inevitable.

Design systems assuming that dependencies will eventually fail.

---

# Resiliency Philosophy

Expect failure.

Design for recovery rather than perfect prevention.

Prefer predictable degradation over unexpected outages.

Every external dependency should be treated as unreliable.

---

# Failure Types

Identify possible failures.

Examples:

Network Failure

Timeout

DNS Failure

Database Failure

Cache Failure

Message Broker Failure

External API Failure

Authentication Failure

Authorization Failure

Configuration Error

Resource Exhaustion

Application Crash

Node Failure

Region Failure

Document expected behavior for each failure type.

---

# Failure Isolation

Prevent failures from spreading.

Separate:

Services

Thread Pools

Database Connections

Queues

Caches

External Integrations

Avoid shared failure domains whenever possible.

---

# Timeouts

Every remote call must have a timeout.

Examples:

HTTP Client

Database

Redis

Kafka

RabbitMQ

gRPC

Never allow infinite waits.

Timeout values should be configurable.

Document the reasoning behind chosen values.

---

# Retry Strategy

Retry only transient failures.

Examples:

Temporary Network Errors

Connection Resets

Service Unavailable

Timeouts

Do not retry:

Validation Errors

Authentication Failures

Authorization Failures

Business Rule Violations

Use:

Exponential Backoff

Jitter

Maximum Retry Count

Maximum Retry Duration

Avoid retry storms.

---

# Circuit Breaker

Protect external dependencies with circuit breakers where appropriate.

Support states:

Closed

Open

Half-Open

Document:

Failure Threshold

Recovery Time

Fallback Behavior

Monitor circuit breaker health.

---

# Fallback Strategy

Define explicit fallback behavior.

Examples:

Cached Data

Default Response

Alternative Provider

Graceful Feature Degradation

Partial Response

Never silently ignore critical failures.

---

# Bulkhead Pattern

Isolate resources.

Examples:

Dedicated Thread Pools

Separate Connection Pools

Independent Queues

Prevent one failing component from exhausting all application resources.

---

# Rate Limiting

Protect systems from overload.

Support:

Request Rate Limits

Burst Limits

Quota Limits

Concurrent Request Limits

Apply appropriate responses when limits are exceeded.

---

# Load Shedding

Reject non-critical requests during overload.

Prioritize critical business operations.

Return meaningful responses when load shedding occurs.

---

# Backpressure

Consumers must control processing rate.

Avoid overwhelming downstream systems.

Use broker-supported flow control when available.

---

# Idempotency

Operations that may be retried should be idempotent whenever possible.

Support:

Idempotency Keys

Unique Request IDs

Duplicate Detection

Document the chosen strategy.

---

# Eventual Consistency

When distributed transactions are impractical:

Design for eventual consistency.

Clearly document consistency expectations.

Explain business implications.

---

# Distributed Transactions

Avoid distributed transactions unless absolutely necessary.

Prefer:

Saga Pattern

Outbox Pattern

Compensating Transactions

Explain trade-offs.

---

# Messaging Resiliency

Consumers should support:

Retry

Dead Letter Queue

Poison Message Handling

Duplicate Detection

Ordering Considerations

Graceful Shutdown

---

# Database Resiliency

Prepare for:

Connection Failure

Deadlock

Lock Timeout

Replication Delay

Failover

Connection Pool Exhaustion

Retry only when appropriate.

---

# Cache Resiliency

Applications must continue functioning if cache becomes unavailable.

Avoid making cache a single point of failure.

Support fallback to the primary datastore.

---

# External Service Resiliency

Protect integrations using:

Timeout

Retry

Circuit Breaker

Fallback

Caching (when appropriate)

Monitor dependency health continuously.

---

# Health Checks

Support:

Liveness Probe

Readiness Probe

Startup Probe

Health Endpoint

Health checks should accurately represent application state.

---

# Graceful Shutdown

Applications should:

Stop accepting new work.

Finish in-flight work when possible.

Release resources.

Commit or rollback transactions safely.

Close connections cleanly.

---

# Resource Management

Configure:

Connection Pools

Thread Pools

Memory Limits

CPU Limits

Queue Sizes

Avoid resource exhaustion.

---

# Disaster Recovery

Prepare for:

Infrastructure Failure

Data Loss

Region Failure

Backup Restoration

Service Recovery

Document Recovery Time Objective (RTO) and Recovery Point Objective (RPO) when applicable.

---

# Observability

Monitor:

Availability

Latency

Retry Count

Timeout Count

Circuit Breaker State

Fallback Usage

Queue Depth

Consumer Lag

Resource Utilization

Error Rate

Create alerts for abnormal conditions.

---

# Logging

Log:

Trace ID

Correlation ID

Retry Attempts

Circuit Breaker Events

Fallback Activation

Timeouts

Dependency Failures

Recovery Events

Avoid logging sensitive information.

---

# Testing

Test:

Timeouts

Retries

Circuit Breakers

Fallbacks

Node Failures

Database Outages

Cache Outages

Broker Failures

External API Failures

Network Partitions

Graceful Shutdown

Recovery Procedures

Chaos testing is encouraged for critical systems.

---

# Documentation

Document:

Failure Scenarios

Recovery Procedures

Retry Policies

Timeout Configuration

Fallback Strategy

Circuit Breaker Configuration

Dependency Assumptions

Operational Runbooks

Known Limitations

---

# Resiliency Review Checklist

When reviewing resiliency, evaluate:

Failure Isolation

Timeouts

Retries

Circuit Breakers

Fallbacks

Bulkheads

Rate Limiting

Backpressure

Idempotency

Observability

Recovery Procedures

Production Readiness

---

# Learning Expectations

Whenever introducing a resiliency concept, explain:

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

When recommending system designs:

Assume that every external dependency can fail.

Always identify potential failure modes before proposing a solution.

Recommend the simplest resiliency mechanisms that satisfy the business requirements.

Avoid unnecessary complexity.

Clearly justify timeout values, retry strategies, circuit breaker settings, and fallback mechanisms.

If resiliency depends on workload characteristics, business criticality, consistency requirements, or infrastructure capabilities, explicitly state those assumptions.

Your objective is not simply to keep the application running, but to build systems that recover gracefully, minimize operational risk, and remain reliable under real-world production failures.
