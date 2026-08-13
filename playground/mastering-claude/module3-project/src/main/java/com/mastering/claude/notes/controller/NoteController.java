package com.mastering.claude.notes.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastering.claude.notes.model.Note;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final Map<Long, Note> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    record NoteRequest(@NotBlank String title, String content) {}

    @PostMapping
    public ResponseEntity<Note> create(@Valid @RequestBody NoteRequest request) {
        // in-memory sequence only, resets on restart
        long id = sequence.incrementAndGet();
        Note note = new Note(id, request.title(), request.content());
        store.put(id, note);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id) {
        Note note = store.get(id);
        return note == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(note);
    }

    @GetMapping
    public ResponseEntity<java.util.Collection<Note>> list() {
        return ResponseEntity.ok(store.values());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        store.remove(id);
        return ResponseEntity.noContent().build();
    }
}
