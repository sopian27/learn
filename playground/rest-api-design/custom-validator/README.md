# Custom Validator Demo (Lesson 5.2)

Run (bash, Java 17+; jar dari `~/.m2`, ganti versi kalau beda):

```bash
M=$(cygpath -m ~/.m2/repository)   # Git Bash di Windows; di Linux/Mac: M=~/.m2/repository
CP="$M/jakarta/validation/jakarta.validation-api/3.0.2/jakarta.validation-api-3.0.2.jar;$M/org/hibernate/validator/hibernate-validator/8.0.2.Final/hibernate-validator-8.0.2.Final.jar;$M/org/apache/tomcat/embed/tomcat-embed-el/10.1.20/tomcat-embed-el-10.1.20.jar;$M/org/jboss/logging/jboss-logging/3.5.3.Final/jboss-logging-3.5.3.Final.jar;$M/com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar"
java -cp "$CP" CustomValidatorDemo.java
```

(Separator `;` untuk Windows; di Linux/Mac pakai `:`.)

Yang diliat:

* `@ValidSku` (field-level): format benar tapi check digit salah tetap ditolak. `null` sengaja dianggap valid, urusan `@NotNull`.
* `@ValidDeliveryWindow` (class-level): aturan lintas field. Error ditempel ke `deliveryDate` lewat `addPropertyNode`, jadi consumer tahu field mana yang salah.
* Validator harus `public` dengan constructor tanpa argumen (kalau tidak: `HV000064`).
* Di record, `@Target` constraint harus mencakup `FIELD`; kalau cuma `RECORD_COMPONENT`, constraint diam-diam nggak jalan.
* "Stok cukup?" butuh DB, jadi sengaja nggak ada di sini.
