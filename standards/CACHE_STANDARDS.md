# Cache Standards

This document defines the standards for designing, implementing, reviewing, and maintaining caching strategies.

Caching is a performance optimization, not a source of truth.

Always design the system to function correctly without cache.

The objective is to improve performance, scalability, and user experience while preserving data consistency and maintainability.

---

# Core Principles

Always prioritize:

* Correctness
* Consistency
* Simplicity
* Performance
* Observability
* Reliability

Never cache data simply because caching is available.

Every cache should have a clear purpose.

---

# Cache Philosophy

Treat the database (or primary datastore) as the source of truth.

Cache exists to reduce latency and system load.

Never store business-critical state exclusively in cache.

---

# When to Use Cache

Consider caching when:

* Data is read frequently.
* Data changes infrequently.
* Database queries are expensive.
* External API calls are slow.
* Computation is expensive.
* Low latency is important.

---

# When NOT to Use Cache

Avoid caching when:

* Data changes continuously.
* Strong consistency is mandatory.
* The computation is inexpensive.
* Cache invalidation becomes more complex than the performance benefit.

---

# Cache Levels

Understand the different cache layers:

Application Memory

Distributed Cache

CDN

HTTP Cache

Browser Cache

Database Cache

Choose the appropriate level based on requirements.

---

# Cache Strategies

Prefer well-known patterns.

Supported strategies include:

Cache Aside

Read Through

Write Through

Write Behind (Write Back)

Refresh Ahead

Always explain the advantages, disadvantages, and trade-offs of each strategy.

---

# Cache Aside

Default recommendation for most backend services.

Flow:

Application

↓

Cache

↓

Database

Application is responsible for loading and invalidating cache.

---

# Read Through

Cache is responsible for loading missing data.

Suitable when supported by the caching solution.

---

# Write Through

Every write updates:

Database

Cache

Improves consistency.

May increase write latency.

---

# Write Behind

Writes are buffered before reaching the database.

Use only when eventual consistency is acceptable.

Explain associated risks.

---

# Refresh Ahead

Refresh cache before expiration.

Useful for frequently accessed data.

Monitor refresh overhead.

---

# Cache Keys

Keys should be:

Unique

Predictable

Readable

Stable

Examples:

user:123

product:999

config:payment

order:1001:summary

Avoid random or ambiguous key names.

---

# Key Naming Convention

Use colon-separated namespaces.

Examples:

user:{id}

user:{id}:profile

user:{id}:permissions

order:{id}

config:{name}

session:{id}

---

# TTL (Time To Live)

Every cache entry should have a justified TTL unless explicitly intended to be permanent.

TTL should reflect business requirements.

Avoid extremely long TTL values without justification.

Explain why the chosen TTL is appropriate.

---

# Cache Invalidation

One of the hardest problems in software engineering.

Always define an invalidation strategy.

Examples:

Time-based expiration

Manual invalidation

Event-driven invalidation

Version-based invalidation

Write-through updates

Document the chosen approach.

---

# Cache Consistency

Clearly define consistency expectations.

Examples:

Strong Consistency

Eventual Consistency

Read-After-Write Consistency

Explain trade-offs.

---

# Cache Stampede

Prevent multiple requests from rebuilding the same cache entry simultaneously.

Consider:

Locking

Request Coalescing

Early Refresh

Randomized TTL

Document mitigation strategies.

---

# Cache Penetration

Prevent repeated lookups for non-existent data.

Consider:

Null caching

Bloom Filters

Input validation

---

# Cache Avalanche

Avoid many keys expiring simultaneously.

Mitigate using:

Random TTL

Warm-up strategies

Distributed expiration

---

# Cache Warm-up

Preload frequently accessed data when beneficial.

Avoid unnecessary warm-up.

Measure effectiveness.

---

# Serialization

Choose serialization format carefully.

Examples:

JSON

Binary

Protocol Buffers

Kryo

Java Serialization (avoid unless required)

Explain trade-offs.

---

# Redis Standards

When using Redis:

Prefer logical key namespaces.

Avoid extremely large values.

Use expiration whenever appropriate.

Choose suitable data structures:

String

Hash

Set

Sorted Set

List

Stream

Do not use Redis as a relational database.

---

# Spring Boot Standards

Prefer Spring Cache abstraction when appropriate.

Use RedisTemplate for advanced use cases.

Avoid mixing multiple caching approaches without clear justification.

Externalize cache configuration.

---

# Monitoring

Monitor:

Hit Rate

Miss Rate

Evictions

Memory Usage

Latency

Connection Pool

Expiration Rate

Cache Size

Alert on abnormal behavior.

---

# Logging

Log:

Cache Miss (when useful)

Cache Rebuild

Cache Eviction

Unexpected Failures

Avoid excessive logging in high-volume operations.

---

# Security

Do not cache:

Passwords

Secrets

Access Tokens

Refresh Tokens

Sensitive Personal Information

unless explicitly encrypted and justified.

Restrict cache access.

---

# Testing

Verify:

Cache Hit

Cache Miss

TTL Expiration

Invalidation

Concurrent Access

Fallback to Database

Failure Recovery

Performance Improvement

---

# Failure Handling

Applications must continue functioning if cache becomes unavailable.

Fallback gracefully.

Do not allow cache failures to corrupt business logic.

---

# Performance Review

Evaluate:

Memory Usage

Serialization Cost

Network Overhead

Latency Improvement

Database Load Reduction

Scalability

Measure before and after introducing cache.

---

# Documentation

Document:

Purpose

Strategy

TTL

Invalidation

Key Naming

Fallback Behavior

Consistency Model

Known Limitations

---

# Cache Review Checklist

When reviewing a caching implementation, evaluate:

Appropriate Use

Key Design

TTL

Invalidation

Consistency

Performance

Security

Observability

Failure Recovery

Maintainability

Scalability

Production Readiness

---

# Learning Expectations

Whenever introducing a caching concept, explain:

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

When recommending caching:

Never recommend cache by default.

First understand:

Data access patterns.

Read/write ratio.

Consistency requirements.

Expected traffic.

Data size.

Always justify the chosen caching strategy.

Explain why alternative strategies were not selected.

Your objective is not simply to improve performance, but to design caching solutions that remain reliable, observable, maintainable, and production-ready.
