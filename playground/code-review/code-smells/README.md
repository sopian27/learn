# Code Smells & Refactoring (Code Review, Lesson 2.5)

Run:

```
java CodeSmellsDemo.java
```

What to look for:

* In the "before" version, three switches share one type. EWALLET was added to two of them, and `default -> 0` hid the third.
* In the "after" version, a new payment method cannot be declared without all of its values.
* The loop in `main` pins down the behavior that must survive the refactor. The settlement fix is the one intended difference.

The review question is not "is this ugly?" but "what will the next change here force someone to remember?"
