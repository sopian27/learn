# Method and Class Design (Code Review, Lesson 2.6)

Run:

```
java MethodClassDesignDemo.java
```

What to look for:

* `beforeCancel(paid, false, true)` compiles and does the opposite of the ticket.
* In `CancelOrder`, a paid order is always refunded. The caller only chooses about the email, by name.
* The two dependencies are one-method interfaces, so the checks replace them with lambdas and no mocking library.

The review question: can you tell what the call site does without opening the method?
