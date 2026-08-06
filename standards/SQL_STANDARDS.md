# SQL Standards

This document defines the standards for writing, reviewing, optimizing, and maintaining SQL.

These standards apply to PostgreSQL, MySQL, Oracle, SQL Server, and other relational database systems unless platform-specific guidance is required.

The objective is to produce SQL that is correct, readable, maintainable, performant, secure, and suitable for production environments.

---

# Core Principles

Always prioritize:

* Correctness
* Readability
* Simplicity
* Maintainability
* Performance
* Security

SQL should clearly communicate intent.

Prefer understandable queries over clever or overly compact solutions.

---

# SQL Philosophy

Write SQL for humans first.

Optimize only after measuring.

Never sacrifice correctness for micro-optimizations.

Document assumptions when query behavior depends on business rules.

---

# SQL Formatting

Use consistent formatting.

Examples:

* SQL keywords in UPPERCASE.
* Tables and columns in snake_case.
* One column per line in SELECT statements.
* One JOIN per line.
* Indent nested queries consistently.
* Align JOIN and WHERE clauses for readability.

Readable SQL is easier to review and maintain.

---

# SELECT Statements

Prefer explicit column selection.

Good:

SELECT
id,
username,
created_at
FROM user_account;

Avoid:

SELECT *

Select only the columns required by the application.

---

# Table Aliases

Use short, meaningful aliases.

Example:

u  -> user_account

o  -> orders

p  -> payment_transaction

Avoid single-letter aliases in complex queries unless universally understood.

---

# WHERE Clauses

Filter as early as possible.

Avoid unnecessary conditions.

Write predicates that can benefit from indexes.

Prefer positive conditions over unnecessary negations.

---

# JOIN Standards

Choose the correct JOIN type.

Examples:

INNER JOIN

LEFT JOIN

RIGHT JOIN (only when justified)

FULL OUTER JOIN (when supported and required)

Document complex joins.

Avoid accidental Cartesian products.

Always specify JOIN conditions explicitly.

---

# EXISTS vs IN

Choose based on readability and execution characteristics.

Explain trade-offs when recommending one over the other.

Validate using execution plans for large datasets.

---

# Subqueries

Use subqueries only when they improve readability or correctness.

Avoid deeply nested subqueries.

Prefer Common Table Expressions (CTEs) when they improve maintainability.

---

# Common Table Expressions (CTE)

Use CTEs to improve readability.

Avoid excessive chaining.

Measure performance when working with very large datasets.

---

# Aggregation

Use:

COUNT

SUM

AVG

MIN

MAX

GROUP BY

HAVING

only when required.

Document assumptions for aggregated data.

---

# Window Functions

Prefer window functions for analytical queries when appropriate.

Examples:

ROW_NUMBER

RANK

DENSE_RANK

LAG

LEAD

Explain why a window function is preferable to alternative approaches.

---

# Pagination

Prefer cursor-based pagination for large datasets.

Offset pagination is acceptable for small or moderate result sets.

Always order results explicitly.

Never rely on implicit ordering.

---

# Sorting

Use ORDER BY whenever result ordering matters.

Avoid sorting unnecessarily.

Ensure sorting columns are appropriate for expected data volumes.

---

# Index Awareness

Write SQL with indexes in mind.

Consider:

Equality predicates

Range predicates

Sorting

Joining

Grouping

Do not assume indexes exist.

Review execution plans before recommending new indexes.

---

# Query Optimization

Evaluate:

Execution Plan

Join Order

Index Usage

Cardinality

Sorting

Temporary Tables

Filtering

Batch Processing

Optimize based on evidence.

---

# Transactions

Keep transactions:

Small

Fast

Consistent

Avoid long-running transactions.

Reduce lock duration.

Choose appropriate isolation levels.

---

# Concurrency

Understand:

Row Locks

Table Locks

Deadlocks

Optimistic Locking

Pessimistic Locking

Document concurrency assumptions.

---

# NULL Handling

Understand NULL semantics.

Use:

IS NULL

IS NOT NULL

Avoid incorrect comparisons using '=' with NULL.

---

# Data Types

Use appropriate data types.

Avoid unnecessary implicit conversions.

Ensure comparisons use compatible types.

---

# Security

Always use parameterized queries.

Never concatenate user input into SQL.

Validate all external input.

Protect against SQL Injection.

Apply least privilege to database accounts.

---

# Batch Operations

Prefer batch operations over row-by-row processing when appropriate.

Measure batch size.

Avoid excessively large transactions.

---

# Database-Specific Features

Use platform-specific features only when they provide significant value.

Document portability implications.

Examples:

PostgreSQL JSONB

PostgreSQL ARRAY

MySQL Generated Columns

Oracle MERGE

---

# Performance Testing

Measure:

Execution Time

Rows Examined

Rows Returned

Index Usage

Memory Usage

CPU Usage

Lock Contention

Benchmark before and after optimization.

---

# Explain Plans

Review execution plans for:

Slow Queries

Large Tables

Complex Joins

Aggregation

Sorting

Document findings before optimizing.

---

# Error Handling

Handle database errors appropriately.

Differentiate:

Constraint Violations

Deadlocks

Timeouts

Connection Failures

Syntax Errors

Do not suppress database errors without justification.

---

# Logging

Log slow queries when appropriate.

Capture:

Execution Time

Parameters (excluding sensitive data)

Affected Rows

Trace ID

Correlation ID

Avoid logging confidential information.

---

# Documentation

Document:

Purpose

Business Rules

Expected Input

Expected Output

Performance Considerations

Index Dependencies

Known Limitations

Platform-Specific Behavior

---

# SQL Review Checklist

When reviewing SQL, evaluate:

Correctness

Readability

Formatting

Security

Index Awareness

Performance

Scalability

Concurrency

Maintainability

Portability

Production Readiness

---

# Learning Expectations

Whenever introducing an SQL concept, explain:

What it is.

Why it exists.

When to use it.

When not to use it.

Advantages.

Disadvantages.

Trade-offs.

Execution characteristics.

Real-world examples.

Common mistakes.

Best practices.

---

# AI Behavior

When generating SQL:

Understand the business requirements before writing queries.

Prefer clarity over cleverness.

Never recommend optimizations without evidence such as execution plans or workload characteristics.

When multiple SQL approaches are possible, compare them and explain their trade-offs.

Highlight any assumptions related to data volume, indexing, concurrency, or database platform.

Your objective is not merely to generate SQL statements, but to design queries that remain readable, performant, maintainable, and reliable in production systems.
