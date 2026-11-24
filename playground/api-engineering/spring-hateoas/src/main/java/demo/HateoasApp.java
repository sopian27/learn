package demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Lesson 1.2: link cancel/refund dibangun dari status order. Jalankan: mvn spring-boot:run
@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class HateoasApp {

    enum Status { PENDING, PAID, SHIPPED, CANCELLED }

    record Order(long id, Status status) {}

    public static void main(String[] args) { SpringApplication.run(HateoasApp.class, args); }

    // Data dummy: status ditentukan dari id, biar bisa dicoba tanpa DB.
    static Order find(long id) { return new Order(id, Status.values()[(int) ((id - 1) % 4)]); }

    @GetMapping("/{id}")
    EntityModel<Order> get(@PathVariable long id) {
        Order o = find(id);
        var model = EntityModel.of(o, linkTo(methodOn(HateoasApp.class).get(id)).withSelfRel());
        if (o.status() == Status.PENDING) model.add(linkTo(methodOn(HateoasApp.class).cancel(id)).withRel("cancel"));
        if (o.status() == Status.PAID) model.add(linkTo(methodOn(HateoasApp.class).refund(id)).withRel("refund"));
        return model;
    }

    // Return type bukan String: methodOn() bikin proxy dari return type, dan String itu final.
    @PostMapping("/{id}/cancel") ResponseEntity<Void> cancel(@PathVariable long id) { return ResponseEntity.noContent().build(); }
    @PostMapping("/{id}/refund") ResponseEntity<Void> refund(@PathVariable long id) { return ResponseEntity.noContent().build(); }
}
