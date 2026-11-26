# Clean Code Principles in Review (Code Review, Lesson 2.4)

Run:

```
java CleanPrinciplesDemo.java
```

What to look for:

* In the "before" version, raising the promo to 20% also doubles the merchant commission.
* In the "after" version, the arithmetic is still written once, but each rule has its own numbers.

The review question is not "do these lines look alike?" but "do they change for the same reason?"
