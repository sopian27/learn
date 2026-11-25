# Blameless Review (Code Review, Lesson 1.3)

Run:

```
java TimeoutReviewDemo.java
```

What to look for:

* `buggy "30"` becomes 30 milliseconds, so an 800 ms bank call times out.
* `fixed "PT30S"` is 30 seconds and the call succeeds.
* `fixed "30"` is rejected: a value without a unit fails loudly instead of being guessed.

The blameless question is not "who wrote 30?" but "why could a number without a unit get through?"
