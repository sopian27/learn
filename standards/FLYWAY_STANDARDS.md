# Flyway Standards

This document defines the standards for managing database schema migrations using Flyway.

These standards apply to all projects that use Flyway for database versioning.

The objective is to ensure every schema change is safe, repeatable, auditable, backward-compatible whenever possible, and suitable for production deployments.

---

# Core Principles

Always prioritize:

* Data Integrity
* Backward Compatibility
* Repeatability
* Safety
* Maintainability
* Auditability

Database migrations are permanent history.

Treat every migration as production code.

---

# Migration Philosophy

Every migration must be:

Deterministic

Idempotent where appropriate

Reviewable

Traceable

Easy to understand

Never modify an executed migration.

Always create a new migration.

---

# Migration Naming

Use Flyway versioned migrations.

Examples:

V1__Create_user_table.sql

V2__Add_email_index.sql

V3__Create_payment_transaction.sql

V4__Rename_status_column.sql

V5__Drop_unused_table.sql

Names should clearly describe the schema change.

Avoid vague names such as:

update.sql

fix.sql

change.sql

---

# Migration Scope

One migration should perform one logical change.

Good:

Create a table.

Add an index.

Rename a column.

Add a constraint.

Bad:

Create ten unrelated tables.

Modify unrelated schemas.

Bundle multiple independent features.

---

# Schema Changes

Document why the schema change is required.

Consider:

Backward compatibility.

Application impact.

Rollback strategy.

Deployment order.

---

# Backward Compatibility

Prefer additive changes.

Examples:

Add nullable columns.

Add new tables.

Add indexes.

Avoid destructive changes during the first deployment.

Examples:

Drop columns.

Rename columns used by older application versions.

Change incompatible data types.

Plan multi-step migrations when required.

---

# Data Migration

Separate schema migration from data migration whenever practical.

Large data migrations should be:

Measured.

Monitored.

Restartable.

Document expected duration.

---

# DDL Standards

Use explicit SQL.

Prefer readable formatting.

Document non-obvious decisions.

Avoid vendor-specific syntax unless justified.

---

# DML Standards

Use DML carefully.

Avoid updating millions of rows inside one transaction unless proven safe.

Batch updates when appropriate.

Verify affected row counts.

---

# Index Management

Create indexes only when justified.

Consider:

Query patterns.

Write overhead.

Storage cost.

Concurrent index creation when supported.

Document why the index exists.

---

# Constraints

Add constraints deliberately.

Examples:

PRIMARY KEY

FOREIGN KEY

UNIQUE

CHECK

NOT NULL

Validate impact before deployment.

---

# Transactions

Use transactional migrations whenever supported.

Understand database-specific limitations.

Keep migrations small.

Avoid long-running transactions.

---

# Locking

Understand migration locking behavior.

Avoid operations that unnecessarily block production traffic.

Document expected lock duration.

---

# Large Tables

Be cautious when modifying large tables.

Examples:

ALTER TABLE

Column Type Changes

NOT NULL Changes

Index Creation

Estimate impact before deployment.

---

# Rollback Strategy

Flyway Community Edition does not provide automatic rollback migrations.

Every migration should include a documented rollback or recovery strategy.

Examples:

Restore from backup.

Compensating migration.

Manual recovery procedure.

Never assume rollback is trivial.

---

# Repeatability

Use repeatable migrations only for objects that naturally evolve.

Examples:

Views

Stored Procedures

Functions

Reference Data (when appropriate)

Do not misuse repeatable migrations for schema evolution.

---

# Environment Compatibility

Migrations must behave consistently across:

Local

Development

Testing

Staging

Production

Avoid environment-specific SQL whenever possible.

---

# Data Integrity

Never risk data loss without explicit justification.

Validate:

Foreign Keys

Unique Constraints

Existing Data

Nullability

Before applying restrictive changes.

---

# Performance

Evaluate:

Execution Time

Lock Duration

Table Size

Index Build Time

Resource Consumption

Benchmark when necessary.

---

# Testing

Every migration should be tested.

Verify:

Fresh Database

Incremental Upgrade

Existing Data

Large Dataset

Rollback Procedure

Application Compatibility

Failure Recovery

Use Testcontainers or disposable databases when practical.

---

# Documentation

Document:

Purpose

Business Reason

Affected Tables

Affected Columns

Expected Downtime

Locking Behavior

Rollback Strategy

Deployment Notes

Known Risks

---

# Code Review Checklist

When reviewing migrations, evaluate:

Naming

Scope

Safety

Backward Compatibility

Data Integrity

Performance

Locking

Rollback Strategy

Documentation

Production Readiness

---

# Common Anti-Patterns

Avoid:

Editing executed migrations.

Combining unrelated changes.

Dropping columns immediately after replacing them.

Long-running blocking migrations.

Large unbatched updates.

Hardcoded environment-specific values.

Unnecessary vendor-specific SQL.

Skipping migration reviews.

---

# Learning Expectations

Whenever introducing a Flyway concept, explain:

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

When generating Flyway migrations:

Never generate migrations blindly.

Understand the business requirement and current schema first.

Prefer additive and backward-compatible migrations.

Recommend multi-step migration strategies for high-risk changes.

Always explain:

Expected impact.

Deployment considerations.

Locking behavior.

Rollback or recovery approach.

If the migration depends on database size, traffic patterns, uptime requirements, or database engine capabilities, explicitly state those assumptions.

Your objective is not merely to modify the database schema, but to produce migrations that are safe, predictable, auditable, and suitable for zero-downtime or low-risk production deployments whenever possible.
