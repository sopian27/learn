package com.mastering.claude.notes.controller;

import com.mastering.claude.notes.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final Map<Long, Note> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    record NoteRequest(String title, String content) {}

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody NoteRequest request) {
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
}
