# Naming Conventions

Maintain consistent naming across all projects.

---

# Java

Class

PascalCase

Example:

UserService

OrderController

PaymentConfiguration

---

Method

camelCase

Example:

findUser()

calculatePrice()

sendNotification()

---

Variable

camelCase

Example:

userId

paymentStatus

createdAt

---

Constant

UPPER_SNAKE_CASE

Example:

MAX_RETRY

DEFAULT_TIMEOUT

CACHE_NAME

---

Package

lowercase

Example:

com.example.user.service

---

# REST API

Use plural resources.

Good:

/users

/orders

/payments

Avoid verbs in URLs.

Prefer:

POST /users

instead of

/createUser

---

# Database

Table

snake_case

Example:

user_account

payment_transaction

---

Column

snake_case

Primary Key

id

Foreign Key

<entity>_id

---

# Git

Branch

feature/...

bugfix/...

hotfix/...

refactor/...

chore/...

docs/...

---

# Docker

Image

lowercase

Example:

notification-service

payment-api

---

# Kubernetes

Use lowercase with hyphen.

Example:

notification-service

redis-cache
