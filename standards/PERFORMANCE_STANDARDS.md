# Performance Standards

This document defines the standards for designing, implementing, measuring, reviewing, and optimizing application performance.

These standards apply to backend services, APIs, databases, messaging systems, caching layers, scheduled jobs, and distributed systems.

Performance optimization should improve user experience and system efficiency without sacrificing correctness, maintainability, reliability, or security.

---

# Core Principles

Always prioritize:

* Correctness
* Maintainability
* Reliability
* Simplicity
* Observability
* Scalability

Performance is important, but never at the expense of correctness.

---

# Performance Philosophy

Measure before optimizing.

Never optimize based on assumptions.

Profile before changing code.

Validate improvements with measurable data.

Document optimization decisions.

---

# Performance Goals

Define measurable objectives whenever possible.

Examples:

Response Time

Latency

Throughput

Memory Usage

CPU Usage

Database Query Time

Cache Hit Ratio

Message Processing Rate

Resource Utilization

---

# Performance Budget

Establish acceptable limits for:

API Response Time

Database Query Duration

Application Startup Time

Memory Consumption

CPU Usage

Request Throughput

Queue Processing Delay

Document these budgets and review them regularly.

---

# Benchmarking

Create reproducible benchmarks.

Benchmark before and after optimization.

Document:

Environment

Input Size

Concurrency

Hardware

Configuration

Results

Avoid comparing unrelated benchmarks.

---

# Profiling

Profile the application before optimizing.

Use appropriate tools for:

CPU Profiling

Memory Profiling

Thread Analysis

Database Profiling

Garbage Collection Analysis

Network Analysis

Always identify bottlenecks before proposing optimizations.

---

# Database Performance

Evaluate:

Execution Plans

Indexes

Join Strategy

Sorting

Filtering

Pagination

Locking

Connection Pool

Batch Operations

Avoid unnecessary queries.

Avoid N+1 query problems.

Optimize only after measurement.

---

# API Performance

Measure:

Latency

Payload Size

Serialization

Deserialization

Validation Cost

Compression

Connection Reuse

Avoid over-fetching.

Minimize unnecessary network calls.

---

# Caching

Introduce caching only when justified.

Evaluate:

Read Frequency

Write Frequency

Consistency Requirements

Memory Cost

Cache Hit Ratio

Document cache strategy and invalidation rules.

---

# Concurrency

Use concurrency only when it provides measurable benefits.

Evaluate:

Thread Count

Thread Pool Configuration

Synchronization

Blocking Operations

Contention

Context Switching

Avoid unnecessary thread creation.

---

# Java Performance

Prefer readable code over micro-optimizations.

Evaluate:

Object Allocation

Boxing and Unboxing

Streams vs Loops

Collections

Immutable Objects

Garbage Collection Impact

Avoid premature optimization.

---

# Spring Boot Performance

Optimize:

Bean Initialization

Lazy Loading (when appropriate)

Connection Pools

HTTP Clients

Serialization

Database Access

Caching

External Integrations

Avoid unnecessary auto-configuration.

---

# Memory Management

Monitor:

Heap Usage

Non-Heap Usage

Garbage Collection

Object Lifetime

Memory Leaks

Large Object Allocation

Avoid retaining unnecessary references.

---

# CPU Optimization

Evaluate:

Algorithm Complexity

Busy Waiting

Repeated Computation

Serialization Cost

Compression Cost

Use efficient algorithms before optimizing implementation details.

---

# Network Performance

Reduce:

Round Trips

Payload Size

Connection Establishment

Redundant Requests

Support:

Compression

Persistent Connections

HTTP/2 or newer protocols when appropriate.

---

# Messaging Performance

Measure:

Producer Throughput

Consumer Throughput

Consumer Lag

Batch Size

Partition Distribution

Retry Impact

Avoid oversized messages.

---

# Batch Processing

Optimize:

Batch Size

Transaction Scope

Parallel Processing

Retry Strategy

Resource Usage

Benchmark different batch sizes.

---

# File Processing

Use streaming for large files.

Avoid loading large files entirely into memory unless justified.

Monitor processing time and memory usage.

---

# Container Performance

Configure:

CPU Limits

Memory Limits

Requests

Thread Pools

Connection Pools

Avoid resource starvation.

---

# Kubernetes Performance

Monitor:

Pod Resource Usage

Autoscaling

Scheduling

Startup Time

Readiness

Liveness

Horizontal Scaling

Avoid unnecessary replicas.

---

# External Services

Measure:

Latency

Timeouts

Retries

Circuit Breakers

Fallbacks

Connection Reuse

Avoid synchronous dependencies when unnecessary.

---

# Scalability

Consider:

Vertical Scaling

Horizontal Scaling

Stateless Design

Partitioning

Sharding

Load Balancing

Choose the simplest solution that meets expected growth.

---

# Load Testing

Perform:

Baseline Tests

Load Tests

Stress Tests

Spike Tests

Soak Tests

Document:

Traffic Pattern

Duration

Results

Bottlenecks

Recommendations

---

# Performance Monitoring

Continuously monitor:

Latency

Throughput

Error Rate

CPU

Memory

Disk

Network

Queue Depth

Cache Metrics

Database Metrics

Set alerts for abnormal behavior.

---

# Logging

Log:

Execution Time

Slow Queries

Slow Requests

Timeouts

Retries

Resource Exhaustion

Avoid excessive logging in performance-critical paths.

---

# Documentation

Document:

Performance Goals

Benchmarks

Known Bottlenecks

Optimization Decisions

Trade-offs

Scalability Assumptions

Monitoring Strategy

---

# Performance Review Checklist

When reviewing performance, evaluate:

Algorithm Complexity

Database Efficiency

Caching

Concurrency

Memory Usage

CPU Usage

Network Efficiency

Scalability

Maintainability

Observability

Production Readiness

---

# Learning Expectations

Whenever introducing a performance concept, explain:

What it is.

Why it matters.

When to optimize.

When not to optimize.

Advantages.

Disadvantages.

Trade-offs.

Measurement techniques.

Real-world examples.

Common mistakes.

Best practices.

---

# AI Behavior

When recommending performance improvements:

Never optimize blindly.

Always request or analyze evidence such as:

Performance metrics

Profiling results

Execution plans

Load test results

Monitoring data

Prioritize architectural improvements before low-level micro-optimizations.

Explain the expected impact, trade-offs, and verification strategy for every optimization.

If optimization depends on workload, traffic patterns, hardware, or infrastructure, explicitly state those assumptions.

Your objective is not simply to make software faster, but to build systems that remain efficient, scalable, observable, maintainable, and reliable under real production workloads.
