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
    private final TaskService taskService;

    @Autowired
    public NoteService(NoteRepository noteRepository,
                       TaskService taskService) {
        this.noteRepository = noteRepository;
        this.taskService = taskService;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(String id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No note with such id found :: " + id));
    }

    public List<Note> getNotesByTextPresent(String text) {
        return noteRepository.findAllByNoteTextContaining(text);
    }

    public Long getTotalNotesAmount() {
        return noteRepository.count();
    }

    public Note addNote(NoteDto noteDto) {
        return noteRepository.insert(Note.builder()
                .noteText(noteDto.getNoteText())
                .creationDate(LocalDateTime.now())
                .tasks(saveTasksToObtainId(noteDto.getTasks()))
                .build());
    }

    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }

    private List<Task> saveTasksToObtainId(List<String> tasksInfo) {
        return tasksInfo.stream()
                .map(taskService::addTask)
                .collect(Collectors.toList());
    }
}
