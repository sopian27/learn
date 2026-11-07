# Bean Scope Demo (Lesson 4.3)

Singleton yang menyimpan state per-user, dan prototype yang "beku" di dalam singleton. Ditulis tanpa Spring supaya mekanismenya kelihatan.

## Jalankan

```
java BeanScopeDemo.java
```

## Yang diperhatikan

- Singleton dengan field `currentUser`: Rani dan Akbar sama-sama dapat struk "Akbar" (data bocor antar request).
- Versi stateless: tiap orang dapat struknya sendiri.
- Prototype yang di-inject lewat constructor ke singleton hanya dibuat sekali, jadi cart #-nya sama terus.
- Lewat provider (di Spring: `ObjectProvider`), cart baru tiap dipanggil.
- Ini simulasi, bukan Spring sebenarnya.
