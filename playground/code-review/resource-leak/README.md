# Lesson 4.4: Resource leak

```bash
java ResourceLeakDemo.java
```

What to look for:

- Two failed exports leave 0 of 2 connections free. With try-with-resources both come back.
- A new executor per request leaves 5 threads alive. With `shutdown()`, 0.
- A map without a limit keeps all 1000 entries. A bounded LRU keeps 100.

The pool is a `Semaphore`, not HikariCP. The map here is not thread-safe; in a real service use a cache library with size and TTL limits.
