# Secrets Handling (Code Review, Lesson 3.3)

Run:

```
java SecretsDemo.java
```

What to look for:

* Leak: a record's default `toString()` writes the key into the log.
* Mask: the overridden `toString()` hides it while the value stays usable.
* Fail-fast: a missing or blank key stops startup instead of failing on the first payment.

All keys here are fake. The review question: where can this value end up besides the place that needs it?
