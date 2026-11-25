# Review Purpose (Code Review, Lesson 1.1)

Run:

```
java RefundReviewDemo.java
```

What to look for:

* `buggy #1` is accepted. A happy-path test stops here and turns green.
* `buggy #2` is also accepted: refunded 140000 on a 100000 payment.
* `fixed #2` is rejected.

The question a reviewer asks: "what happens if this is called twice?"

Not covered: concurrent requests. The fixed version is still not thread-safe.
