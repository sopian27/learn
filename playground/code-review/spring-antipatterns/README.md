# Spring Anti-Patterns (transactional pitfalls)

Pendamping Library Lesson 5.1. Ini tiruan proxy `@Transactional` pakai JDK dynamic proxy, bukan Spring asli.

```
java SpringAntiPatternDemo.java
```

Yang diliat:
- Baris 1 vs 2: method sama, beda cara manggil. Lewat `this`, data setengah jadi tetap nyangkut.
- Baris 3 vs 4: checked exception tetap commit, kecuali diberi `rollbackFor`.
