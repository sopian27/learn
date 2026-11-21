# WebMvcSliceTestDemo (Lesson 11.2)

Run: `java WebMvcSliceTestDemo.java`

Lihat:
- Versi BUG: "qty 0" lolos 201 (data sampah masuk), "sku kosong" balik 500 (bocor `NullPointerException`). Dua test FAIL, dan test service-layer nggak akan nangkep ini.
- Versi FIXED: semua PASS, 400 untuk input salah.
- Mapping ke Spring: `post()` = controller + `@Valid` + `@RestControllerAdvice`; `FAKE` = `@MockitoBean OrderService`; `expect` = `mockMvc.perform(...).andExpect(status()...)`.
