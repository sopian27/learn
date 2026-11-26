# Readability, Naming & Cognitive Load (Code Review, Lesson 2.3)

Run:

```
java ReadabilityReviewDemo.java
```

What to look for:

* Read `check(...)` first and time how long it takes to answer "can a customer refund a shipped order?"
* `check(order, true)` lets anyone refund a 90-day-old shipped order, and the call site does not say why.
* The "after" version behaves the same for every combination checked in `main`; only the reading cost changed.

The review question is not "do I like this name?" but "what would a reader wrongly assume?"
