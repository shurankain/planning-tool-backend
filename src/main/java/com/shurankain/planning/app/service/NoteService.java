package com.shurankain.planning.app.service;

import com.shurankain.planning.app.dto.NoteDto;
import com.shurankain.planning.app.dto.NoteWithTasksDto;
import com.shurankain.planning.app.dto.TaskDto;
import com.shurankain.planning.app.persistence.model.Note;
import com.shurankain.planning.app.persistence.model.Task;
import com.shurankain.planning.app.persistence.repsoitory.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .orElse(null);
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
                .tasks(noteDto.getTasks().stream().map(dtoTask -> Task.builder().taskInfo(dtoTask).build()).collect(Collectors.toList()))
                .build());
    }

    public Note editNote(String id, NoteWithTasksDto noteWithTasksDto) {
        var noteToChange = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No note with such id found :: " + id));
        noteToChange.setNoteText(noteWithTasksDto.getNoteText());
        List<Task> tasks = noteWithTasksDto.getTasks().stream().map(taskDto -> Task.builder()
                .taskInfo(taskDto.getTaskInfo())
                .creationDate(taskDto.getCreationDate())
                .completionStatus(taskDto.getCompletionStatus())
                .build()).collect(Collectors.toList());
        noteToChange.setTasks(tasks);
        return noteRepository.save(noteToChange);
    }

    public Note addTaskToNote(String id, TaskDto taskDto) {
        var noteToChange = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No note with such id found :: " + id));
        var task = taskService.addTask(taskDto.getTaskInfo());
        var taskList = noteToChange.getTasks();
        taskList.add(task);
        noteToChange.setTasks(taskList);
        return noteRepository.save(noteToChange);
    }

    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }
}
