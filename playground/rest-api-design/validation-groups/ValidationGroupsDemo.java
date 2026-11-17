import jakarta.validation.*;
import jakarta.validation.groups.Default;
import jakarta.validation.constraints.*;
import java.util.*;

public class ValidationGroupsDemo {
    public interface OnCreate {}
    public interface OnUpdate {}

    record UserRequest(
        @Null(groups = OnCreate.class)    // create: server yang bikin id
        @NotNull(groups = OnUpdate.class) // update: id wajib
        Long id,

        @NotBlank(groups = {OnCreate.class, OnUpdate.class}) String name,

        @NotBlank(groups = OnCreate.class) // update: password opsional
        @Size(min = 8, groups = OnCreate.class) String password,

        @Size(min = 3) String nickname) {}   // tanpa groups = group Default saja

    static final Validator V = Validation.buildDefaultValidatorFactory().getValidator();

    static void check(String label, UserRequest r, Class<?>... groups) {
        Set<ConstraintViolation<UserRequest>> vs = V.validate(r, groups);
        System.out.println("== " + label + " (" + groups[0].getSimpleName() + ")");
        if (vs.isEmpty()) System.out.println("  lolos");
        vs.stream().map(v -> "  [" + v.getPropertyPath() + "] " + v.getMessage()).sorted().forEach(System.out::println);
    }

    public static void main(String[] a) {
        check("create valid", new UserRequest(null, "Rani", "rahasia123", null), OnCreate.class);
        check("create salah: id dikirim, password kosong", new UserRequest(7L, "Rani", null, null), OnCreate.class);
        check("update valid: password tidak dikirim", new UserRequest(7L, "Rani", null, null), OnUpdate.class);
        check("update salah: id hilang", new UserRequest(null, "Rani", null, null), OnUpdate.class);
        check("jebakan: nickname 'ab' (@Size tanpa groups) lolos di OnCreate", new UserRequest(null, "Rani", "rahasia123", "ab"), OnCreate.class);
        check("pembanding: group Default menangkap nickname 'ab'", new UserRequest(null, "Rani", "rahasia123", "ab"), Default.class);
    }
}
