# Nested & Collection Validation Demo (Lesson 5.4)

Run (bash, Java 17+; jar dari `~/.m2`, ganti versi kalau beda):

```bash
M=$(cygpath -m ~/.m2/repository)   # Git Bash di Windows; di Linux/Mac: M=~/.m2/repository
CP="$M/jakarta/validation/jakarta.validation-api/3.0.2/jakarta.validation-api-3.0.2.jar;$M/org/hibernate/validator/hibernate-validator/8.0.2.Final/hibernate-validator-8.0.2.Final.jar;$M/org/apache/tomcat/embed/tomcat-embed-el/10.1.20/tomcat-embed-el-10.1.20.jar;$M/org/jboss/logging/jboss-logging/3.5.3.Final/jboss-logging-3.5.3.Final.jar;$M/com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar"
java -cp "$CP" NestedValidationDemo.java
```

(Separator `;` untuk Windows; di Linux/Mac pakai `:`.)

Yang diliat:

* `BadOrder` tanpa `@Valid` di list item: item dengan `quantity = 0` lolos diam-diam.
* `Order` dengan `List<@Valid OrderItem>`: error muncul dengan path `items[1].quantity`, jadi konsumen tahu item ke berapa yang salah.
* `@NotEmpty` pada list menolak order tanpa item; `List<@NotBlank String>` mengecek tiap elemen string.
