# Mapping Demo (Lesson 4.3)

Run: `java MappingDemo.java`

Tanpa MapStruct/Spring. Mapper by-name berbasis reflection meniru konvensi MapStruct (nama sama = dicopy); MapStruct asli generate kode saat compile.

Yang diliat:

* Mapper manual: field baru (`phone`) terlupa, compile OK, hasil null senyap.
* Mapper otomatis longgar: nama beda (`displayName` vs `name`) jadi null senyap.
* Mode strict (setara `unmappedTargetPolicy = ERROR`): kegagalan muncul di build.
* DTO yang menamai field sama dengan field rahasia entity: ikut ter-copy otomatis.
