# Bad vs Great Review (Code Review, Lesson 1.2)

Run:

```
java VoucherReviewDemo.java
```

What to look for:

* `total=30000`: buggy and fixed agree. A test with only this input stays green.
* `total=40000`: the boundary, still equal.
* `total=500000`: buggy gives 250000 off, fixed gives 20000.

A great review comment names the input that breaks, the effect, and a way to fix it.
