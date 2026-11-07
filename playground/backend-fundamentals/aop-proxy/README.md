# AOP Proxy Demo (Lesson 4.4)

Proxy mencegat panggilan dari luar, tapi self-invocation lolos. Ditulis tanpa Spring pakai `java.lang.reflect.Proxy` (JDK dynamic proxy).

## Jalankan

```
java AopProxyDemo.java
```

## Yang diperhatikan

- `proxy.pay()` dapat `[TX BEGIN]`/`[TX COMMIT]`, tapi `saveAudit()` yang dipanggil lewat `this` di dalamnya tidak dapat.
- Memanggil `saveAudit()` lewat referensi proxy (seperti dari bean lain) dapat TX.
- Tipe objek adalah `$Proxy...`, bukan class aslinya.
- CGLIB tidak didemokan (butuh library); mekanisme pencegatannya sama, bedanya membuat subclass.
- Ini simulasi, bukan Spring sebenarnya.
