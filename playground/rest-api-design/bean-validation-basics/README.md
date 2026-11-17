# Bean Validation Basics Demo (Lesson 5.1)

Run (bash, Java 17+; jar diambil dari `~/.m2`, ganti versi kalau beda):

```bash
M=$(cygpath -m ~/.m2/repository)   # Git Bash di Windows; di Linux/Mac: M=~/.m2/repository
CP="$M/jakarta/validation/jakarta.validation-api/3.0.2/jakarta.validation-api-3.0.2.jar;$M/org/hibernate/validator/hibernate-validator/8.0.2.Final/hibernate-validator-8.0.2.Final.jar;$M/org/apache/tomcat/embed/tomcat-embed-el/10.1.20/tomcat-embed-el-10.1.20.jar;$M/org/jboss/logging/jboss-logging/3.5.3.Final/jboss-logging-3.5.3.Final.jar;$M/com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar"
java -cp "$CP" BeanValidationDemo.java
```

(Separator `;` untuk Windows; di Linux/Mac pakai `:`.)

Yang diliat:

* Request valid lolos; request salah semua mengeluarkan satu pesan per field.
* `null` dianggap valid oleh `@Email`, `@Pattern`, `@Size`: field opsional lolos.
* `""` (string kosong) lolos `@Email` (Hibernate Validator menganggap kosong valid) tapi ditolak `@Pattern`. Jadi `null` dan `""` bisa diperlakukan beda tergantung constraint.
* `"   "` (spasi doang) ditolak `@NotBlank` di field alamat; `@NotNull` saja akan meloloskannya.
