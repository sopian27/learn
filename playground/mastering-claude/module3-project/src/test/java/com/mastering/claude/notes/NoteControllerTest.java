package com.mastering.claude.notes;

import com.mastering.claude.notes.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteControllerTest {

    @Autowired
    private TestRestTemplate rest;

    @LocalServerPort
    private int port;

    @Test
    void createThenGetReturnsNote() {
        var payload = new NoteRequest("Judul", "Isi catatan");

        ResponseEntity<Note> created = rest.postForEntity(url("/notes"), payload, Note.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(created.getBody()).isNotNull();
        Long id = created.getBody().id();

        ResponseEntity<Note> fetched = rest.getForEntity(url("/notes/" + id), Note.class);
        assertThat(fetched.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(fetched.getBody().title()).isEqualTo("Judul");
    }

    @Test
    void listThenDeleteRemovesNote() {
        var payload = new NoteRequest("A", "B");
        Long id = rest.postForEntity(url("/notes"), payload, Note.class).getBody().id();

        ResponseEntity<Note[]> listed = rest.getForEntity(url("/notes"), Note[].class);
        assertThat(listed.getBody()).isNotEmpty();

        rest.delete(url("/notes/" + id));
        ResponseEntity<Note> afterDelete = rest.getForEntity(url("/notes/" + id), Note.class);
        assertThat(afterDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    record NoteRequest(String title, String content) {}
}
