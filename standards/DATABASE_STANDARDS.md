# Database Standards

This document defines the database engineering standards for all projects.

Whenever designing, reviewing, or implementing a database, follow these standards unless project-specific requirements explicitly override them.

The goal is to build databases that are maintainable, scalable, secure, and production-ready.

---

# Core Principles

Always optimize for:

* Correctness
* Data Integrity
* Simplicity
* Maintainability
* Performance
* Scalability
* Security
* Reliability

Prefer simple schemas over overly complex designs.

Avoid premature optimization.

Always explain trade-offs when recommending a design.

---

# Database Design

Before creating tables:

Understand the business domain.

Identify entities.

Identify relationships.

Identify ownership of data.

Normalize appropriately.

Denormalize only when justified by measurable performance or business requirements.

---

# Naming Conventions

Use snake_case.

Examples:

user_account

payment_transaction

order_item

Columns:

created_at

updated_at

deleted_at

Foreign Keys:

user_id

order_id

payment_id

Primary Keys:

id

Avoid abbreviations unless they are industry standard.

Use meaningful names.

---

# Primary Keys

Prefer:

UUID when globally unique identifiers are beneficial.

Auto-increment integer when simplicity and sequential IDs are acceptable.

Explain the trade-offs before recommending one.

---

# Relationships

Always identify:

One-to-One

One-to-Many

Many-to-Many

Use foreign keys whenever appropriate.

Avoid unnecessary cascading deletes.

Document cascade behavior explicitly.

---

# Constraints

Always consider:

NOT NULL

UNIQUE

CHECK

FOREIGN KEY

DEFAULT

Do not rely solely on application validation.

Protect data integrity at the database level whenever practical.

---

# Indexing

Create indexes only when justified.

Explain why an index is needed.

Evaluate:

Primary Key

Unique Index

Composite Index

Partial Index (when supported)

Covering Index (when applicable)

Avoid unnecessary indexes.

Remember:

Every index improves reads but increases write cost.

---

# Query Standards

Prefer:

Explicit column selection.

Meaningful aliases.

Parameterized queries.

Readable formatting.

Avoid:

SELECT *

Nested queries when JOINs are clearer.

Repeated queries inside loops (N+1).

---

# Performance

Always evaluate:

Execution Plan

Index Usage

Sorting

Filtering

Join Strategy

Pagination

Batch Operations

Measure before optimizing.

Never assume.

---

# Pagination

Prefer cursor-based pagination for very large datasets.

Use offset pagination only when appropriate.

Always explain the trade-offs.

---

# Transactions

Keep transactions:

Small

Fast

Consistent

Avoid long-running transactions.

Minimize lock duration.

Use appropriate isolation levels.

Explain why a specific isolation level is chosen.

---

# Concurrency

Consider:

Deadlocks

Lock Contention

Optimistic Locking

Pessimistic Locking

Version Columns

Explain trade-offs.

---

# Soft Delete

When soft delete is required:

Use:

deleted_at

or

is_deleted

Document why soft delete is used.

Ensure queries correctly exclude deleted records.

---

# Audit Columns

Unless unnecessary, include:

created_at

created_by

updated_at

updated_by

Optionally:

deleted_at

deleted_by

---

# Data Types

Choose the smallest suitable type.

Examples:

BOOLEAN

INTEGER

BIGINT

NUMERIC

TIMESTAMP

UUID

TEXT

VARCHAR

Avoid oversized VARCHAR lengths without justification.

---

# SQL Style

Keywords:

UPPERCASE

Identifiers:

snake_case

Indent nested queries.

Align JOIN clauses.

Write SQL that is easy to review.

---

# Migration

Use Flyway (or project standard).

Never modify an executed migration.

Create a new migration for every schema change.

Migration names should clearly describe the change.

Example:

Create user table

Add index on email

Rename payment_status column

---

# Security

Never concatenate SQL strings.

Always use parameterized queries.

Validate user input.

Protect sensitive data.

Encrypt data when appropriate.

Never store secrets in plaintext.

---

# Backup & Recovery

Design with recovery in mind.

Consider:

Backup strategy

Restore testing

Point-in-time recovery

Disaster recovery requirements

---

# Documentation

Document:

ER Diagram

Relationships

Constraints

Indexes

Migration history

Known limitations

---

# Code Review Checklist

When reviewing database changes, evaluate:

Schema design

Naming

Normalization

Indexes

Constraints

Query performance

Migration quality

Transaction safety

Security

Backward compatibility

---

# Learning Expectations

Whenever introducing a new database concept:

Explain:

What it is.

Why it exists.

When to use it.

When not to use it.

Advantages.

Disadvantages.

Trade-offs.

Production examples.

Common mistakes.

Best practices.

---

# AI Behavior

When generating SQL:

Always explain design decisions.

Never generate schema blindly.

If requirements are ambiguous, ask clarifying questions before designing.

Prefer maintainable solutions over clever solutions.

Whenever possible, compare PostgreSQL and MySQL behaviors if they differ significantly.

If a recommendation depends on expected data volume, write patterns, read patterns, or business constraints, explicitly state those assumptions.

Your objective is not merely to create tables, but to design databases that are robust, scalable, and maintainable throughout the lifecycle of the application.
