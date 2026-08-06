# Security Standards

This document defines the security standards for designing, developing, deploying, and maintaining software.

These standards apply to every project unless explicitly overridden by documented business or compliance requirements.

Security must be considered throughout the software lifecycle, not added as an afterthought.

---

# Core Principles

Always prioritize:

* Confidentiality
* Integrity
* Availability
* Least Privilege
* Defense in Depth
* Secure by Default
* Fail Securely
* Zero Trust mindset

Every security decision should balance risk, usability, maintainability, and performance.

---

# Secure Development Mindset

Assume:

* Every input is malicious until validated.
* Every client can be compromised.
* Every network is untrusted.
* Every dependency may contain vulnerabilities.

Never trust the frontend.

Always validate on the server.

---

# Authentication

Authentication must verify identity.

Support project-appropriate mechanisms such as:

* JWT
* OAuth2
* OpenID Connect
* API Keys
* Session Authentication
* Mutual TLS (when required)

Never invent custom authentication unless absolutely necessary.

---

# Authorization

Authorization must verify permissions.

Apply authorization checks on every protected operation.

Prefer:

* Role-Based Access Control (RBAC)
* Attribute-Based Access Control (ABAC) when needed

Never rely on hidden UI elements for security.

---

# Password Security

Never store plaintext passwords.

Always use strong password hashing algorithms.

Examples:

* Argon2
* bcrypt
* scrypt

Never use:

* MD5
* SHA1
* Plain SHA256 for password hashing

Enforce password policies according to business requirements.

---

# Secrets Management

Never hardcode:

Passwords

API Keys

JWT Secrets

Database Credentials

Private Keys

Store secrets using:

Environment Variables

Secret Managers

Vault solutions

Kubernetes Secrets (with proper controls)

Never commit secrets into version control.

---

# Input Validation

Validate every external input.

Validate:

Type

Length

Range

Format

Business Rules

Whitelist whenever possible.

Reject invalid input early.

---

# Output Encoding

Encode output appropriate to the destination.

Prevent:

Cross-Site Scripting (XSS)

HTML Injection

Header Injection

CSV Injection

Log Injection

---

# SQL Injection

Always use:

Prepared Statements

Parameterized Queries

ORM parameter binding

Never concatenate SQL using user input.

Review dynamic SQL carefully.

---

# Cross-Site Scripting (XSS)

Escape output when rendering HTML.

Apply Content Security Policy (CSP) where appropriate.

Avoid rendering untrusted HTML.

---

# Cross-Site Request Forgery (CSRF)

Protect state-changing operations when using cookie/session authentication.

Understand when CSRF protection is required and when token-based authentication changes the threat model.

---

# File Upload Security

Validate:

File Type

File Size

Extension

Content Type

Virus Scan (when applicable)

Never trust client-provided metadata.

Store uploaded files outside the application root when appropriate.

---

# API Security

Apply OWASP API Security best practices.

Protect against:

Broken Object Level Authorization

Broken Authentication

Excessive Data Exposure

Mass Assignment

Rate Limit Abuse

Security Misconfiguration

Injection

Improper Asset Management

---

# Session Security

Sessions should:

Expire appropriately.

Support logout.

Prevent fixation.

Invalidate after password changes when appropriate.

---

# JWT Security

Use short-lived access tokens.

Use refresh tokens appropriately.

Validate:

Signature

Expiration

Issuer

Audience

Algorithm

Never trust unsigned tokens.

Never expose JWT secrets.

Consider token revocation strategies when required.

---

# Encryption

Encrypt sensitive data:

In Transit

At Rest (when required)

Prefer modern TLS versions.

Avoid obsolete cryptographic algorithms.

Never create custom cryptographic algorithms.

---

# Logging

Log security-relevant events.

Examples:

Authentication

Authorization Failure

Password Changes

Privilege Changes

Configuration Changes

Security Exceptions

Never log:

Passwords

Access Tokens

Refresh Tokens

Secrets

Private Keys

Sensitive Personal Information

---

# Error Handling

Return meaningful but safe error messages.

Do not expose:

Stack Traces

SQL Queries

Framework Internals

Server Paths

Internal IP Addresses

---

# Dependency Security

Regularly:

Update dependencies.

Review CVEs.

Remove unused libraries.

Prefer actively maintained libraries.

---

# Docker Security

Use minimal base images.

Run containers as non-root.

Avoid privileged containers.

Scan container images.

Keep images updated.

---

# Kubernetes Security

Use:

RBAC

Network Policies

Resource Limits

Pod Security Standards

Secrets Management

Readiness/Liveness Probes

Avoid running privileged workloads unless necessary.

---

# Database Security

Use least-privilege database accounts.

Encrypt sensitive columns when appropriate.

Enable auditing where required.

Restrict administrative access.

---

# Caching Security

Do not cache sensitive information unless explicitly required.

Apply TTL appropriately.

Invalidate cache after sensitive updates.

Protect cache endpoints from unauthorized access.

---

# Messaging Security

Validate incoming messages.

Authenticate producers and consumers when supported.

Avoid trusting message contents.

Design consumers to be idempotent.

---

# Rate Limiting

Protect public endpoints against abuse.

Apply:

Rate Limiting

Request Throttling

Account Lockout (when appropriate)

Progressive Delays (when appropriate)

---

# Audit Logging

Record important business events.

Examples:

Login

Logout

Permission Changes

Sensitive Data Access

Administrative Actions

Audit logs should be immutable where practical.

---

# Security Headers

When applicable:

Content-Security-Policy

Strict-Transport-Security

X-Content-Type-Options

X-Frame-Options

Referrer-Policy

Permissions-Policy

---

# Backup Security

Protect backups.

Encrypt backups.

Control backup access.

Test restoration procedures.

---

# Incident Response

Prepare for:

Detection

Containment

Recovery

Post-Incident Review

Root Cause Analysis

Lessons Learned

---

# Security Review Checklist

Evaluate:

Authentication

Authorization

Input Validation

Output Encoding

SQL Injection

XSS

CSRF

Secrets Management

Logging

Encryption

Dependency Risks

Infrastructure Security

Configuration Security

API Security

Data Protection

Production Readiness

---

# Learning Expectations

Whenever introducing a security concept, explain:

What it is.

Why it exists.

What attack it prevents.

When to apply it.

Common implementation mistakes.

Trade-offs.

Real-world examples.

OWASP relevance.

Best practices.

---

# AI Behavior

When generating code, infrastructure, APIs, or configurations:

Always choose the most secure practical implementation.

Never recommend insecure shortcuts without explicitly warning about the risks.

If security depends on business context, clearly state the assumptions.

When reviewing code, proactively identify vulnerabilities and recommend concrete mitigations.

Your objective is not only to make software functional, but to ensure it is resilient against common threats while remaining maintainable and production-ready.
