import java.lang.reflect.RecordComponent;
import java.util.*;

// Lesson 4.3: mapper manual vs mapper otomatis berbasis nama (cara kerja MapStruct, versi mini).
// Tanpa MapStruct asli. MapStruct generate kode saat compile; di sini pakai reflection supaya satu file.
public class MappingDemo {
    record User(long id, String displayName, String email, String phone, String passwordHash) {}

    // Sprint lalu DTO punya 3 field. Sprint ini ditambah `phone`.
    record UserResponse(long id, String name, String email, String phone) {}

    // 1. Mapper manual: kontrol penuh, tapi field baru gampang terlupa (compiler tidak protes).
    static UserResponse manual(User u) {
        return new UserResponse(u.id(), u.displayName(), u.email(), null); // phone lupa diisi
    }

    // 2. Mapper otomatis by-name: field dengan nama sama dicopy. Nama beda (displayName -> name) harus dipetakan eksplisit.
    static <T extends Record> T autoMap(Record src, Class<T> target, Map<String, String> renames, boolean strict) throws Exception {
        Map<String, Object> srcVals = new HashMap<>();
        for (RecordComponent c : src.getClass().getRecordComponents()) srcVals.put(c.getName(), c.getAccessor().invoke(src));
        RecordComponent[] tc = target.getRecordComponents();
        Object[] args = new Object[tc.length];
        Class<?>[] types = new Class<?>[tc.length];
        for (int i = 0; i < tc.length; i++) {
            String from = renames.getOrDefault(tc[i].getName(), tc[i].getName());
            if (strict && !srcVals.containsKey(from))
                throw new IllegalStateException("Unmapped target property: " + tc[i].getName()); // mirip unmappedTargetPolicy=ERROR
            args[i] = srcVals.get(from);
            types[i] = tc[i].getType();
        }
        return target.getDeclaredConstructor(types).newInstance(args);
    }

    // DTO yang ceroboh: ada field bernama sama dengan field rahasia di entity.
    record BadUserResponse(long id, String email, String passwordHash) {}

    public static void main(String[] args) throws Exception {
        User u = new User(1, "Arif Pratama", "arif@mail.com", "0812-0000", "$2a$10$abc...");

        System.out.println("== 1. Mapper manual, field baru `phone` terlupa ==");
        System.out.println(manual(u));
        System.out.println("   -> compile OK, test hijau, phone diam-diam null\n");

        System.out.println("== 2. Mapper otomatis, mode longgar: `name` tidak ketemu ==");
        System.out.println(autoMap(u, UserResponse.class, Map.of(), false));
        System.out.println("   -> name=null tanpa error. Longgar = bug senyap\n");

        System.out.println("== 3. Mode strict (unmappedTargetPolicy=ERROR) ==");
        try { autoMap(u, UserResponse.class, Map.of(), true); }
        catch (IllegalStateException e) { System.out.println("GAGAL: " + e.getMessage() + "  <- ketahuan di build, bukan di production"); }
        System.out.println("dengan rename name<-displayName: " + autoMap(u, UserResponse.class, Map.of("name", "displayName"), true) + "\n");

        System.out.println("== 4. Jebakan: by-name mapping membawa field rahasia kalau DTO menamainya sama ==");
        System.out.println(autoMap(u, BadUserResponse.class, Map.of(), true));
        System.out.println("   -> passwordHash bocor tanpa satu baris kode mapping pun. Allow-list tetap ada di DTO, bukan di mapper");
    }
}
