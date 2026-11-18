# Problem Details Demo (Lesson 6.2)

Run (Java 17+, tanpa dependency):

```bash
java ProblemDetailsDemo.java
```

Yang diliat:

* Bagian atas: tiga endpoint, tiga bentuk error berbeda. Klien harus bikin parser per endpoint.
* Bagian bawah: satu bentuk `type`/`title`/`status`/`detail`/`instance`, plus field tambahan (`errorId`).
* Di Spring 6+, `ProblemDetail` punya field yang sama; Content-Type-nya `application/problem+json`.
