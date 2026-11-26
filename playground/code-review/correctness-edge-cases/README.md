# Correctness & Edge Cases (Code Review, Lesson 2.1)

Run:

```
java InstallmentReviewDemo.java
```

What to look for:

* The happy path (1_200_000 over 12 months) is identical in both versions.
* `1_000_000` over 3 months: the buggy version silently loses 1 rupiah.
* `months=0` crashes with a vague error; negative totals and `total=5` pass silently in the buggy version.

The review question is not "does this run?" but "which input makes this lie?"
