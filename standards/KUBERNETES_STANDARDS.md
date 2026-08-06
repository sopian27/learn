# Kubernetes Standards

This document defines the standards for designing, deploying, securing, operating, and maintaining applications on Kubernetes.

These standards apply to Kubernetes, OpenShift, and Kubernetes-compatible platforms.

The objective is to build workloads that are secure, resilient, scalable, observable, maintainable, and production-ready.

---

# Core Principles

Always prioritize:

* Reliability
* Security
* Scalability
* Maintainability
* Observability
* Portability

Applications should be cloud-native and stateless whenever practical.

---

# Kubernetes Philosophy

Treat Pods as ephemeral.

Never rely on local Pod storage.

Applications must tolerate Pod restarts and rescheduling.

Configuration belongs outside the application.

Infrastructure should be declarative.

---

# Resource Organization

Organize Kubernetes manifests clearly.

Examples:

Namespace

Deployment

StatefulSet

DaemonSet

Service

Ingress

ConfigMap

Secret

PersistentVolume

PersistentVolumeClaim

HorizontalPodAutoscaler

NetworkPolicy

Separate resources by responsibility.

---

# Namespaces

Use namespaces to isolate environments and applications.

Examples:

development

testing

staging

production

Avoid deploying unrelated workloads into the same namespace.

---

# Labels and Annotations

Apply meaningful labels.

Examples:

app

component

version

environment

team

Labels should support:

Selection

Monitoring

Automation

Annotations should store metadata only.

---

# Deployments

Use Deployments for stateless applications.

Define:

Replicas

Rolling Update Strategy

Resource Requests

Resource Limits

Health Probes

Graceful Shutdown

Avoid single-replica production deployments unless justified.

---

# Stateful Applications

Use StatefulSets for stateful workloads.

Examples:

Databases

Kafka

ZooKeeper

Document storage and recovery strategies.

---

# Services

Choose the appropriate Service type.

Examples:

ClusterIP

NodePort

LoadBalancer

ExternalName

Avoid exposing internal services unnecessarily.

---

# Ingress

Use Ingress for HTTP and HTTPS traffic.

Configure:

TLS

Hostnames

Routing Rules

Path Rules

Authentication (when required)

Avoid exposing services directly when Ingress is appropriate.

---

# Configuration Management

Store non-sensitive configuration in ConfigMaps.

Store sensitive configuration in Secrets.

Never hardcode environment-specific values in manifests.

---

# Secrets

Use Kubernetes Secrets only for confidential information.

Encrypt Secrets at rest when supported.

Restrict access using RBAC.

Avoid storing plaintext secrets in Git.

---

# Resource Requests and Limits

Define CPU and Memory requests and limits.

Do not rely on defaults.

Tune values using production metrics.

Avoid overcommitting resources without understanding the impact.

---

# Health Probes

Configure:

Startup Probe

Liveness Probe

Readiness Probe

Health checks should accurately represent application state.

Avoid expensive probe implementations.

---

# Autoscaling

Use Horizontal Pod Autoscaler (HPA) when appropriate.

Scale based on:

CPU

Memory

Custom Metrics

External Metrics

Document scaling assumptions.

---

# Storage

Use PersistentVolumeClaims for persistent data.

Avoid local storage for production workloads.

Document backup and recovery procedures.

---

# Networking

Use Kubernetes networking.

Avoid hardcoded IP addresses.

Use Services for service discovery.

Apply Network Policies to restrict communication.

Adopt a default-deny model where practical.

---

# Security

Apply the principle of least privilege.

Use:

RBAC

Network Policies

Pod Security Standards

Non-root containers

Read-only root filesystems (when practical)

Drop unnecessary Linux capabilities.

Avoid privileged containers.

---

# Service Accounts

Create dedicated Service Accounts.

Avoid using the default Service Account for production workloads.

Grant only required permissions.

---

# Scheduling

Use:

Node Selectors

Affinity

Anti-Affinity

Taints

Tolerations

Topology Spread Constraints

Document scheduling requirements.

---

# Rolling Updates

Prefer rolling updates.

Configure:

maxUnavailable

maxSurge

Monitor rollout progress.

Support rollback procedures.

---

# Rollbacks

Every deployment should support rollback.

Document rollback procedures.

Verify application compatibility before rollback.

---

# Logging

Applications should log to stdout and stderr.

Avoid writing logs only to local files.

Use centralized logging platforms.

Include:

Trace ID

Correlation ID

Request ID

---

# Monitoring

Monitor:

Pod Health

CPU

Memory

Disk Usage

Restart Count

OOMKilled Events

Network

Latency

Error Rate

Queue Depth

Create alerts for abnormal conditions.

---

# Observability

Support:

Metrics

Distributed Tracing

Structured Logging

Health Endpoints

Readiness Endpoints

Liveness Endpoints

Integrate with monitoring systems such as Prometheus and Grafana when appropriate.

---

# Resiliency

Prepare for:

Pod Restart

Node Failure

Rolling Updates

Autoscaling

Dependency Failure

Network Partition

Graceful Shutdown

Avoid single points of failure.

---

# Image Management

Use immutable image tags.

Avoid:

latest

Pull images from trusted registries.

Scan images for vulnerabilities.

---

# CI/CD

Deploy using automated pipelines.

Avoid manual manifest editing in production.

Prefer GitOps workflows when applicable.

Track deployment history.

---

# Disaster Recovery

Document:

Backup Strategy

Restore Procedure

Recovery Time Objective (RTO)

Recovery Point Objective (RPO)

Cluster Recovery

Application Recovery

---

# Testing

Verify:

Deployment

Scaling

Rolling Updates

Rollback

Health Probes

Resource Limits

Network Policies

Configuration

Secrets

Persistent Storage

Graceful Shutdown

Failure Recovery

---

# Documentation

Document:

Architecture

Namespaces

Services

Ingress

Configuration

Secrets

Storage

Networking

Scaling

Deployment Process

Rollback Procedure

Operational Runbooks

Known Limitations

---

# Kubernetes Review Checklist

When reviewing Kubernetes manifests, evaluate:

Resource Organization

Security

Configuration

Secrets

Resource Limits

Health Probes

Autoscaling

Networking

Observability

Scalability

Maintainability

Production Readiness

---

# Learning Expectations

Whenever introducing a Kubernetes concept, explain:

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

When generating Kubernetes manifests or architecture:

Prefer declarative, secure, and production-ready configurations.

Always recommend resource requests and limits, health probes, graceful shutdown, RBAC, and observability.

Do not generate manifests blindly.

Understand the workload characteristics, deployment strategy, availability requirements, and operational constraints before proposing a solution.

If recommendations depend on cluster size, cloud provider, traffic patterns, or organizational policies, explicitly state those assumptions.

Your objective is not merely to deploy applications on Kubernetes, but to design workloads that are secure, resilient, scalable, observable, maintainable, and operationally excellent throughout their lifecycle.
