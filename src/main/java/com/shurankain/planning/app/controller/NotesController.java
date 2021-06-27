package com.shurankain.planning.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.shurankain.planning.app.dto.NoteDto;
import com.shurankain.planning.app.persistence.model.Note;
import com.shurankain.planning.app.service.NoteService;

@Controller
public class NotesController {

    private final NoteService noteService;

    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return ResponseEntity.ok().body(notes);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        var note = noteService.getNoteById(id);
        return ResponseEntity.ok().body(note);
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNoteByText(@RequestParam String text) {
        var notes = noteService.getNotesByTextPresent(text);
        return ResponseEntity.ok().body(notes);
    }

    @GetMapping("/notes/total")
    public ResponseEntity<Long> getTotalNotesAmount() {
        var notes = noteService.getTotalNotesAmount();
        return ResponseEntity.ok().body(notes);
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> addNote(@RequestBody NoteDto note) {
        var insertedNote = noteService.addNote(note);
        return ResponseEntity.ok().body(insertedNote);
    }
}
