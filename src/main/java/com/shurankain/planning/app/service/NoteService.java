package com.shurankain.planning.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shurankain.planning.app.dto.NoteDto;
import com.shurankain.planning.app.persistence.model.Note;
import com.shurankain.planning.app.persistence.model.Task;
import com.shurankain.planning.app.persistence.repsoitory.NoteRepository;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(String id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No note with such id found :: " + id));
    }

    public Note getNoteByTextPresent(String text) {
        return noteRepository.findNoteByNoteTextContaining(text)
                .orElseThrow(() -> new IllegalStateException("No note with such text found :: " + text));
    }

    public Long getTotalNotesAmount() {
        return noteRepository.count();
    }

    public Note addNote(NoteDto noteDto) {

        return noteRepository.insert(Note.builder()
                .noteText(noteDto.getNoteText())
                .creationDate(LocalDateTime.now())
                .tasks(convertToTasksList(noteDto.getTasks()))
            .build());
}

    private List<Task> convertToTasksList(List<String> tasksInfo) {
        return tasksInfo.stream().map(taskInfo ->
                    Task.builder()
                            .taskInfo(taskInfo)
                            .creationDate(LocalDateTime.now())
                            .completionStatus(false)
                            .build()
        ).collect(Collectors.toList());
    }
}