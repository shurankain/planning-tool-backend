package com.shurankain.planning.app.controller;

import com.shurankain.planning.app.dto.NoteDto;
import com.shurankain.planning.app.dto.NoteWithTasksDto;
import com.shurankain.planning.app.persistence.model.Note;
import com.shurankain.planning.app.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotesController {

    private final NoteService noteService;

    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes/all")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return ResponseEntity.ok().body(notes);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        var note = noteService.getNoteById(id);
        return ResponseEntity.ok().body(note);
    }

    @GetMapping("/notes/total")
    public ResponseEntity<Long> getTotalNotesAmount() {
        var notes = noteService.getTotalNotesAmount();
        return ResponseEntity.ok().body(notes);
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNoteByText(@RequestParam String text) {
        var notes = noteService.getNotesByTextPresent(text);
        return ResponseEntity.ok().body(notes);
    }

    @PostMapping("/notes/add")
    public ResponseEntity<Note> addNote(@RequestBody NoteDto note) {
        var insertedNote = noteService.addNote(note);
        return ResponseEntity.ok().body(insertedNote);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> editNote(@PathVariable String id, @RequestBody NoteWithTasksDto noteDto){
        var note = noteService.editNote(id, noteDto);
        return ResponseEntity.ok().body(note);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Object> deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
