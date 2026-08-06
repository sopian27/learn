# Docker Standards

This document defines the standards for building, configuring, securing, optimizing, and maintaining Docker images and containers.

These standards apply to all applications packaged with Docker, regardless of programming language or framework.

The objective is to produce Docker images that are secure, lightweight, reproducible, maintainable, performant, and suitable for production deployments.

---

# Core Principles

Always prioritize:

* Security
* Simplicity
* Reproducibility
* Portability
* Performance
* Maintainability

Containers should be immutable.

Applications should be configured externally.

---

# Container Philosophy

A container should contain one primary responsibility.

Containers should be disposable.

Never rely on local container state.

Applications must function correctly after container restart.

---

# Base Images

Prefer official images.

Prefer minimal images.

Examples:

Alpine

Distroless

Slim variants

Avoid large base images unless justified.

Document why a specific base image was chosen.

---

# Image Size

Keep images as small as practical.

Remove unnecessary:

Packages

Caches

Temporary Files

Development Tools

Unused Dependencies

Smaller images improve startup time, transfer speed, and security.

---

# Multi-Stage Builds

Prefer multi-stage builds.

Separate:

Build Environment

Runtime Environment

Do not ship compilers or build tools in production images.

---

# Layer Optimization

Minimize image layers.

Group related commands.

Order Dockerfile instructions to maximize cache reuse.

Place rarely changing instructions before frequently changing ones.

---

# Dockerfile Structure

Prefer the following order:

FROM

LABEL

ENV

WORKDIR

COPY

RUN

EXPOSE

USER

ENTRYPOINT

CMD

Keep Dockerfiles readable and well organized.

---

# .dockerignore

Always use .dockerignore.

Exclude:

.git

.idea

.vscode

node_modules

target

build

logs

temporary files

Large unnecessary files

Reduce build context.

---

# Environment Variables

Externalize configuration.

Never hardcode:

Passwords

Secrets

API Keys

Database Credentials

JWT Secrets

Use runtime environment variables instead.

---

# Secrets

Do not bake secrets into images.

Use:

Docker Secrets

Environment Variables

Secret Managers

Kubernetes Secrets

Cloud Secret Services

Restrict access appropriately.

---

# User Permissions

Never run production containers as root unless absolutely necessary.

Create dedicated non-root users.

Apply least privilege.

---

# File Permissions

Grant only required permissions.

Avoid world-writable files.

Avoid unnecessary executable permissions.

---

# Networking

Use container networking instead of hardcoded IP addresses.

Communicate using service names where appropriate.

Avoid exposing unnecessary ports.

---

# Volumes

Persist only required data.

Avoid storing application state inside the container filesystem.

Use volumes for:

Databases

Uploads

Persistent Storage

Logs (when appropriate)

---

# Logging

Applications should log to stdout and stderr.

Avoid writing logs exclusively to local files inside containers.

Allow the container platform to manage log collection.

---

# Health Checks

Define health checks whenever possible.

Health checks should verify meaningful application readiness.

Avoid expensive health checks.

---

# Startup

Containers should start quickly.

Avoid unnecessary startup logic.

Initialize only what is required.

---

# Shutdown

Support graceful shutdown.

Handle termination signals correctly.

Allow in-flight requests to complete when appropriate.

Release resources cleanly.

---

# Resource Configuration

Configure:

CPU Limits

Memory Limits

Thread Pools

Connection Pools

Do not assume unlimited resources.

---

# Build Reproducibility

Pin dependency versions where appropriate.

Avoid downloading changing artifacts during image build without version control.

Ensure builds are deterministic.

---

# Image Tagging

Avoid using:

latest

Prefer explicit version tags.

Examples:

1.0.0

2.5.1

2026.08.04

git-commit-sha

Maintain a clear image versioning strategy.

---

# Security

Regularly scan images for vulnerabilities.

Remove unused packages.

Keep base images updated.

Avoid unnecessary Linux capabilities.

Prefer read-only filesystems when practical.

Drop unnecessary privileges.

---

# Supply Chain Security

Verify third-party images.

Prefer trusted registries.

Document image provenance.

Sign images when organizational policy requires it.

---

# Docker Compose

Use Docker Compose for local development.

Separate:

Application

Database

Redis

Kafka

Monitoring

Document service dependencies.

---

# Performance

Optimize:

Image Size

Startup Time

Memory Usage

Layer Caching

Build Duration

Container Density

Measure improvements before adopting optimizations.

---

# Testing

Verify:

Image Build

Container Startup

Health Checks

Environment Variables

Networking

Volumes

Graceful Shutdown

Security Configuration

Production Configuration

Run integration tests inside containers whenever practical.

---

# Documentation

Document:

Base Image

Exposed Ports

Environment Variables

Volumes

Health Checks

Startup Command

Resource Requirements

Security Considerations

Build Process

Known Limitations

---

# Docker Review Checklist

When reviewing Docker configurations, evaluate:

Dockerfile Quality

Image Size

Security

Layer Optimization

Secrets Handling

Configuration Externalization

Health Checks

Logging

Startup Behavior

Graceful Shutdown

Maintainability

Production Readiness

---

# Learning Expectations

Whenever introducing a Docker concept, explain:

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

When generating Dockerfiles or container configurations:

Prefer secure, minimal, and reproducible images.

Always recommend multi-stage builds for compiled applications unless unnecessary.

Never embed secrets inside images.

Explain the reasoning behind base image selection, layer ordering, user permissions, and runtime configuration.

If container design depends on workload, deployment platform, or operational requirements, explicitly state those assumptions.

Your objective is not merely to containerize an application, but to produce Docker images and runtime configurations that are secure, efficient, maintainable, and production-ready.
