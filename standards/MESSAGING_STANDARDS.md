# Messaging Standards

This document defines the standards for designing, implementing, testing, and operating asynchronous messaging systems.

These standards apply to Kafka, RabbitMQ, ActiveMQ, Amazon SQS, Google Pub/Sub, and similar messaging platforms.

The objective is to build messaging systems that are reliable, scalable, observable, maintainable, and resilient.

---

# Core Principles

Always prioritize:

* Reliability
* Durability
* Scalability
* Simplicity
* Observability
* Fault Tolerance
* Data Integrity

Messaging should improve system decoupling, not introduce unnecessary complexity.

---

# Messaging Philosophy

Use asynchronous messaging only when it provides clear business or technical value.

Avoid messaging when synchronous communication is simpler and sufficient.

Every message should represent a meaningful business event or command.

---

# Communication Patterns

Understand and choose the appropriate pattern.

Examples:

Event Notification

Event-Carried State Transfer

Command Messaging

Request-Reply

Publish-Subscribe

Point-to-Point Queue

Streaming

Always explain why a pattern is selected.

---

# Event vs Command

Events describe something that has already happened.

Examples:

UserRegistered

OrderPaid

InvoiceGenerated

Commands request that something should happen.

Examples:

CreateInvoice

SendEmail

GenerateReport

Do not confuse events with commands.

---

# Message Design

Messages should be:

Immutable

Self-contained when practical

Versionable

Serializable

Business-oriented

Avoid exposing internal implementation details.

---

# Event Naming

Use business language.

Prefer past-tense for events.

Examples:

OrderCreated

PaymentCompleted

UserDeleted

InventoryReserved

Avoid technical names.

Example:

ProcessEvent

ExecuteJob

---

# Message Schema

Every message should include:

Event Name

Event Version

Timestamp

Unique Event ID

Correlation ID

Payload

Metadata (when applicable)

---

# Message Versioning

Expect schemas to evolve.

Prefer backward-compatible changes.

Avoid breaking consumers.

Document version changes.

---

# Message Size

Keep messages reasonably small.

Avoid sending unnecessary fields.

Store large files externally and reference them.

---

# Delivery Guarantees

Understand the delivery model.

Examples:

At Most Once

At Least Once

Exactly Once (when supported)

Always explain trade-offs.

Design consumers accordingly.

---

# Idempotency

Consumers must be idempotent whenever duplicate delivery is possible.

Repeated processing should not produce incorrect business results.

Document the idempotency strategy.

---

# Ordering

Do not assume global message ordering.

When ordering matters:

Use appropriate partitioning or queue strategies.

Explain ordering guarantees provided by the chosen platform.

---

# Partitioning

Design partition keys carefully.

Consider:

Load Distribution

Ordering Requirements

Scalability

Avoid hot partitions.

---

# Retry Strategy

Transient failures should be retried.

Define:

Retry Count

Retry Interval

Backoff Strategy

Maximum Retry Duration

Avoid infinite retry loops.

---

# Dead Letter Queue (DLQ)

Messages that cannot be processed should be moved to a Dead Letter Queue when appropriate.

Document:

Reason

Original Message

Timestamp

Retry Count

Provide operational guidance for replay.

---

# Poison Messages

Detect messages that consistently fail.

Prevent them from blocking processing.

Route them to DLQ or quarantine.

Investigate root causes.

---

# Error Handling

Consumers should:

Handle failures gracefully.

Log meaningful context.

Avoid crashing the entire consumer for a single message.

---

# Transaction Boundaries

Clearly define transactional behavior.

Avoid distributed transactions unless absolutely necessary.

Prefer eventual consistency when appropriate.

Consider patterns such as the Outbox Pattern for reliable event publication.

---

# Message Durability

Choose durability based on business requirements.

Critical business events should be durable.

Document retention requirements.

---

# Kafka Standards

When using Kafka:

Use meaningful topic names.

Design partitions intentionally.

Use consumer groups appropriately.

Manage offsets carefully.

Configure retention according to business needs.

Avoid using Kafka as a database.

---

# RabbitMQ Standards

When using RabbitMQ:

Choose the correct exchange type.

Define durable queues when required.

Use acknowledgements.

Configure dead-letter exchanges.

Avoid unbounded queue growth.

---

# Spring Boot Standards

Prefer Spring for Apache Kafka or Spring AMQP when appropriate.

Separate producers and consumers by responsibility.

Externalize broker configuration.

Avoid embedding business logic inside listener methods.

---

# Security

Protect messaging infrastructure.

Use authentication and authorization mechanisms supported by the broker.

Encrypt communication when required.

Never place secrets in message payloads.

Avoid sending sensitive personal data unless necessary and protected.

---

# Monitoring

Monitor:

Queue Depth

Consumer Lag

Publish Rate

Consume Rate

Retry Count

DLQ Size

Message Throughput

Processing Latency

Error Rate

Alert on abnormal behavior.

---

# Logging

Log:

Message ID

Correlation ID

Consumer Group

Topic or Queue

Partition (when applicable)

Offset (when applicable)

Processing Time

Errors

Avoid logging sensitive payload data.

---

# Testing

Verify:

Producer behavior

Consumer behavior

Serialization

Deserialization

Retry logic

DLQ handling

Idempotency

Ordering (when required)

Failure recovery

Performance

Use embedded brokers or Testcontainers when practical.

---

# Performance

Evaluate:

Throughput

Latency

Partition distribution

Consumer concurrency

Message size

Batch processing

Resource usage

Measure before optimizing.

---

# Documentation

Document:

Broker

Topics or Queues

Exchange Types (if applicable)

Message Schemas

Retry Strategy

DLQ Strategy

Retention Policy

Ordering Guarantees

Versioning Strategy

Operational Procedures

Known Limitations

---

# Messaging Review Checklist

When reviewing messaging implementations, evaluate:

Appropriate Messaging Pattern

Message Design

Naming

Versioning

Reliability

Idempotency

Retry Strategy

DLQ Handling

Ordering

Security

Observability

Scalability

Maintainability

Production Readiness

---

# Learning Expectations

Whenever introducing a messaging concept, explain:

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

When recommending messaging:

Do not recommend asynchronous communication by default.

First understand:

Business workflow.

Latency requirements.

Consistency requirements.

Failure tolerance.

Expected throughput.

Choose the simplest solution that satisfies the requirements.

Always explain why messaging is appropriate—or why a synchronous approach may be a better fit.

Recommend production-ready patterns such as idempotent consumers, retry with backoff, dead-letter queues, and observability.

Your objective is not simply to exchange messages, but to design messaging systems that are reliable, understandable, scalable, and maintainable throughout their lifecycle.
