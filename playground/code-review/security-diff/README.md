# Security in a Diff (Code Review, Lesson 3.1)

Run:

```
java SecurityDiffDemo.java
```

What to look for:

* Injection: the same input changes the SQL text in one version and stays a parameter in the other. The database is simulated.
* Access control: a logged-in user reads someone else's order until ownership is checked.
* Data exposure: a record's default `toString()` puts the token in the log.

The review question: who controls this value, and who is allowed to see the result?
