# Error Handling, Logging, Observability (Code Review, Lesson 2.7)

Run:

```
java ErrorHandlingDemo.java
```

What to look for:

* "before" returns 200 while the order is still unpaid, so the provider never retries, and the log line names no order.
* "after" returns 500, writes one log line with the order id and the cause, and counts the failure.
* A repeated callback pays the order only once, which is what makes retrying safe.

The review question: when this fails at 2 a.m., who finds out, and can they tell which order it was?
