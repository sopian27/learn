import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;

public class NestedValidationDemo {
    record OrderItem(@NotBlank String sku, @Min(1) int quantity) {}

    // Versi salah: item tidak diberi @Valid, jadi isinya tidak pernah dicek
    record BadOrder(@NotBlank String customerId, @NotEmpty List<OrderItem> items) {}

    // Versi benar: @Valid menurunkan validasi ke tiap item; element-level untuk tag
    record Order(@NotBlank String customerId,
                 @NotEmpty @Size(max = 50) List<@Valid OrderItem> items,
                 List<@NotBlank String> tags) {}

    static final Validator V = Validation.buildDefaultValidatorFactory().getValidator();

    static <T> void check(String label, T r) {
        Set<ConstraintViolation<T>> vs = V.validate(r);
        System.out.println("== " + label);
        if (vs.isEmpty()) System.out.println("  lolos");
        vs.stream().map(v -> "  [" + v.getPropertyPath() + "] " + v.getMessage()).sorted().forEach(System.out::println);
    }

    public static void main(String[] args) {
        var good = new OrderItem("SKU-1", 2);
        var bad  = new OrderItem("SKU-2", 0);
        var blank = new OrderItem(" ", -3);

        check("jebakan: tanpa @Valid, quantity 0 lolos", new BadOrder("C-1", List.of(good, bad)));
        check("dengan @Valid, quantity 0 ketangkap", new Order("C-1", List.of(good, bad), List.of()));
        check("dua item rusak, path menunjuk index", new Order("C-1", List.of(blank, good, bad), List.of()));
        check("items kosong", new Order("C-1", List.of(), List.of()));
        check("tag kosong di dalam list", new Order("C-1", List.of(good), List.of("promo", " ")));
    }
}
