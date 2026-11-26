# Test Adequacy (Code Review, Lesson 2.8)

Run:

```
java TestAdequacyDemo.java
```

What to look for:

* The weak suite reaches every line and both branches, yet passes on the buggy code.
* The boundary suite fails on the buggy code and passes on the fix.
* The only difference between the two suites is which inputs they chose.

The review question: if this line were wrong, which test would turn red?
