# Validation Groups Demo (Lesson 5.3)

Run (bash, Java 17+; jar dari `~/.m2`, ganti versi kalau beda):

```bash
M=$(cygpath -m ~/.m2/repository)   # Git Bash di Windows; di Linux/Mac: M=~/.m2/repository
CP="$M/jakarta/validation/jakarta.validation-api/3.0.2/jakarta.validation-api-3.0.2.jar;$M/org/hibernate/validator/hibernate-validator/8.0.2.Final/hibernate-validator-8.0.2.Final.jar;$M/org/apache/tomcat/embed/tomcat-embed-el/10.1.20/tomcat-embed-el-10.1.20.jar;$M/org/jboss/logging/jboss-logging/3.5.3.Final/jboss-logging-3.5.3.Final.jar;$M/com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar"
java -cp "$CP" ValidationGroupsDemo.java
```

(Separator `;` untuk Windows; di Linux/Mac pakai `:`.)

Yang diliat:

* Satu record `UserRequest`, dua group (`OnCreate`, `OnUpdate`): `id` harus null saat create, wajib saat update; `password` wajib saat create, opsional saat update.
* Jebakan: `@Size` di `nickname` tanpa `groups` hanya masuk group `Default`. Divalidasi dengan `OnCreate`, nickname `"ab"` lolos diam-diam. Divalidasi dengan `Default`, baru ketangkep.
