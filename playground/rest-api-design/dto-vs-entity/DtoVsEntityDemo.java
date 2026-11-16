import java.lang.reflect.RecordComponent;
import java.util.*;

// Lesson 4.1: Entity langsung ke response (bocor + kontrak ikut berubah) vs lewat DTO.
// Serializer mini berbasis reflection meniru Jackson: semua field record ikut keluar.
public class DtoVsEntityDemo {
    // Entity versi 1: representasi tabel users.
    record UserV1(long id, String fullName, String email, String passwordHash, String internalNote) {}
    // Entity versi 2: DB di-refactor, fullName jadi displayName.
    record UserV2(long id, String displayName, String email, String passwordHash, String internalNote) {}

    // DTO: kontrak API, hanya field yang sengaja dibuka.
    record UserResponse(long id, String name, String email) {}

    static String toJson(Record r) throws Exception {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        for (RecordComponent c : r.getClass().getRecordComponents())
            sj.add("\"" + c.getName() + "\": \"" + c.getAccessor().invoke(r) + "\"");
        return sj.toString();
    }

    // Controller "malas": return entity apa adanya.
    static String getUserLeaky(Record entity) throws Exception { return toJson(entity); }

    // Controller yang benar: mapping eksplisit entity -> DTO. Hanya mapper yang tahu soal kolom DB.
    static UserResponse toResponse(UserV1 u) { return new UserResponse(u.id(), u.fullName(), u.email()); }
    static UserResponse toResponse(UserV2 u) { return new UserResponse(u.id(), u.displayName(), u.email()); }

    public static void main(String[] args) throws Exception {
        UserV1 v1 = new UserV1(1, "Arif Pratama", "arif@mail.com", "$2a$10$abc...", "sering telat bayar");
        UserV2 v2 = new UserV2(1, "Arif Pratama", "arif@mail.com", "$2a$10$abc...", "sering telat bayar");

        System.out.println("== 1. Entity langsung ke response (over-exposure) ==");
        System.out.println(getUserLeaky(v1));
        System.out.println("   -> passwordHash dan internalNote ikut bocor ke client\n");

        System.out.println("== 2. Entity berubah, kontrak API ikut pecah ==");
        System.out.println("sebelum: " + getUserLeaky(v1));
        System.out.println("sesudah: " + getUserLeaky(v2));
        System.out.println("   -> field 'fullName' hilang, client mobile yang baca fullName dapat null\n");

        System.out.println("== 3. Lewat DTO ==");
        System.out.println("sebelum refactor: " + toJson(toResponse(v1)));
        System.out.println("sesudah refactor: " + toJson(toResponse(v2)));
        System.out.println("   -> JSON identik, yang diubah cuma satu mapper");
    }
}
