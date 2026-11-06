# DI Injection Styles Demo (Lesson 4.2)

Tiga gaya injection (field, setter, constructor) ditulis tanpa Spring, supaya terlihat bedanya saat dependency hilang.

## Jalankan

```
java DiInjectionStylesDemo.java
```

## Yang diperhatikan

- Field injection: `new` biasa menghasilkan objek setengah jadi. Compiler diam, NPE baru muncul saat method dipanggil. Satu-satunya jalan masuk dependency adalah reflection (yang dilakukan `@Autowired`).
- Setter injection: lupa memanggil setter = NPE di runtime.
- Constructor injection: dependency `final` dan wajib. Salah rakit gagal saat objek dibuat, bukan saat request pertama.
- Unit test constructor injection cukup `new` dengan test double, tanpa container.
- Ini demo, bukan Spring sebenarnya.
