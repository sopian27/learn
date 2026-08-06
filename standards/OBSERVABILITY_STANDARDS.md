# Observability Standards

This document defines the standards for designing, implementing, monitoring, and operating observable systems.

These standards apply to all backend services, APIs, messaging systems, databases, scheduled jobs, batch processes, and distributed systems.

The objective is to make every production system easy to understand, monitor, troubleshoot, and improve throughout its lifecycle.

---

# Core Principles

Always prioritize:

* Visibility
* Reliability
* Debuggability
* Simplicity
* Consistency
* Actionable Insights

Observability is a design requirement, not an operational afterthought.

---

# Observability Philosophy

Every production issue should be diagnosable using telemetry.

Applications should explain what they are doing without requiring developers to reproduce issues locally.

Every important business operation should be observable.

---

# The Three Pillars

Design observability around:

Metrics

Logs

Distributed Tracing

Each pillar complements the others.

Do not rely on only one.

---

# Structured Logging

Always use structured logs.

Prefer JSON logging.

Include consistent field names across services.

Avoid free-form log messages when structured fields are appropriate.

---

# Log Levels

Use log levels consistently.

TRACE

DEBUG

INFO

WARN

ERROR

FATAL (if supported)

Do not log everything as ERROR.

Avoid excessive DEBUG logging in production.

---

# Required Log Fields

Whenever possible include:

Timestamp

Service Name

Application Version

Environment

Log Level

Thread

Trace ID

Span ID

Correlation ID

Request ID

User ID (when appropriate)

Operation

Execution Time

Error Code

Exception Type

Host

Pod Name

Container ID

---

# Sensitive Data

Never log:

Passwords

API Keys

JWT Tokens

Refresh Tokens

Secrets

Private Keys

Personal Sensitive Information

Payment Information

Mask confidential fields.

---

# Business Logging

Log important business events.

Examples:

Order Created

Payment Completed

User Registered

Report Generated

Batch Completed

Business logs should support operational analysis.

---

# Error Logging

Log:

Root Cause

Exception Type

Error Code

Affected Resource

Retry Count

Dependency

Avoid duplicate logging of the same exception.

---

# Metrics

Expose metrics for:

Availability

Latency

Throughput

Error Rate

CPU

Memory

Disk

Network

Queue Size

Database

Cache

Business KPIs

Metrics should be machine-readable.

---

# Golden Signals

Monitor:

Latency

Traffic

Errors

Saturation

These should exist for every production service.

---

# RED Method

For APIs measure:

Rate

Errors

Duration

---

# USE Method

For infrastructure measure:

Utilization

Saturation

Errors

---

# Business Metrics

Monitor business health.

Examples:

Orders Per Minute

Login Success Rate

Payment Success Rate

Transactions Per Second

Messages Processed

Batch Success Rate

Observability should include business outcomes, not only technical metrics.

---

# Distributed Tracing

Support distributed tracing.

Every request should propagate:

Trace ID

Span ID

Correlation ID

Across services.

Avoid breaking trace propagation.

---

# Context Propagation

Propagate tracing context through:

REST APIs

Messaging

Kafka

RabbitMQ

Scheduled Jobs

Async Tasks

Background Workers

---

# Health Checks

Expose:

Liveness

Readiness

Startup

Health endpoints should represent real application health.

---

# Dependency Monitoring

Monitor:

Database

Redis

Kafka

RabbitMQ

External APIs

Authentication Providers

Storage

DNS

Network

Detect dependency degradation early.

---

# Alerting

Alerts should be:

Actionable

Meaningful

Low-noise

Prioritized

Avoid alert fatigue.

Every alert should have an operational response.

---

# Dashboards

Create dashboards for:

Application Health

Infrastructure

Database

Messaging

Cache

Business Metrics

Error Trends

Deployment Health

Capacity Planning

Dashboards should answer operational questions quickly.

---

# Performance Monitoring

Monitor:

Response Time

Slow Requests

Slow Queries

GC Activity

Memory Usage

Thread Pools

Connection Pools

Queue Lag

Resource Utilization

---

# Deployment Observability

Track:

Deployment Version

Deployment Time

Deployment Duration

Rollback Events

Release Health

Correlate incidents with deployments.

---

# Batch Processing

Record:

Batch Start

Batch End

Duration

Success Count

Failure Count

Retry Count

Skipped Records

---

# Messaging

Monitor:

Producer Rate

Consumer Rate

Consumer Lag

Retry Count

DLQ Size

Processing Latency

Duplicate Processing

---

# Database Observability

Monitor:

Slow Queries

Connection Pool

Deadlocks

Lock Wait

Replication Delay

Query Duration

Index Usage

---

# Cache Observability

Monitor:

Cache Hit Ratio

Miss Ratio

Memory Usage

Evictions

TTL Expiration

Latency

Connection Errors

---

# Security Observability

Monitor:

Authentication Failures

Authorization Failures

Suspicious Requests

Rate Limit Violations

Unexpected Access

Configuration Changes

Do not expose confidential information.

---

# Incident Investigation

Support root cause analysis using:

Logs

Metrics

Traces

Deployment History

Infrastructure Events

Business Events

Every production issue should be traceable.

---

# Documentation

Document:

Available Metrics

Log Format

Tracing Strategy

Alert Rules

Dashboards

Operational Runbooks

Monitoring Tools

Known Limitations

---

# Testing

Verify:

Logs

Metrics

Tracing

Health Endpoints

Alert Rules

Dashboards

Telemetry Accuracy

Trace Propagation

Monitoring Coverage

---

# Observability Review Checklist

When reviewing observability, evaluate:

Logging

Metrics

Tracing

Business Metrics

Alert Quality

Dashboard Coverage

Performance Visibility

Security

Maintainability

Production Readiness

---

# Learning Expectations

Whenever introducing an observability concept, explain:

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

When generating applications or reviewing existing systems:

Treat observability as a first-class architectural concern.

Recommend structured logging, meaningful metrics, distributed tracing, health checks, and actionable alerts by default.

Always explain what telemetry should be collected, why it matters, and how it supports debugging, performance analysis, and operational excellence.

If observability requirements depend on traffic volume, infrastructure, compliance, or business criticality, explicitly state those assumptions.

Your objective is not simply to collect logs or metrics, but to build systems that allow engineers to understand, diagnose, optimize, and confidently operate production workloads.
