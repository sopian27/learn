# Architecture & SOLID in Review (Code Review, Lesson 2.2)

Run:

```
java SolidReviewDemo.java
```

What to look for:

* The "before" version returns `refundDays=0` for `PAYLATER` and charges nothing for a misspelled method, without any error.
* In the "after" version, remove `refundDays()` from `PayLater` and run again: it no longer compiles.

The review question is not "which SOLID letter is violated?" but "what breaks on the next change?"
