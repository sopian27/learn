# GraphQL Resolver (Lesson 3.2)

Jalankan: `java ResolverDemo.java` (Java 17+, tanpa library).

Yang diliat:
- Query `{ order { id status } }` cuma manggil `findById`; `findItems` nggak disentuh karena `items` nggak diminta.
- Query `{ order { id items } }` manggil `findItems` juga. Satu resolver per field = bibit N+1 di lesson 3.3.
- Resolver cuma manggil `OrderService`; logika bisnis tetap di service.
