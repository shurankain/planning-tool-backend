package com.shurankain.planning.app.service;

import com.shurankain.planning.app.dto.NoteDto;
import com.shurankain.planning.app.persistence.model.Note;
import com.shurankain.planning.app.persistence.model.Task;
import com.shurankain.planning.app.persistence.repsoitory.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    public static final String NOTE_ID = "testNoteId";
    public static final String NOTE_TEXT = "testNoteText";

    public static final String TASK_ID = "testTaskId";
    public static final String TASK_INFO = "testTaskInfo";
    public static final LocalDateTime CREATION_DATE = LocalDateTime.of(2021, 6, 26, 11, 20);

    @Captor
    private ArgumentCaptor<Note> noteCaptor;
    @Captor
    private ArgumentCaptor<String> taskInfoCaptor;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private TaskService taskService;
    @InjectMocks
    private NoteService noteService;

    @Test
    void whenAddNoteCalledThenSavedNoteInfoShouldBeReturned() {
        when(noteRepository.insert(any(Note.class))).thenReturn(constructNote());

        var addedNote = noteService.addNote(constructNoteDto());

        verify(noteRepository, times(1)).insert(noteCaptor.capture());

        assertEquals(NOTE_ID, addedNote.getId());
        assertEquals(NOTE_TEXT, addedNote.getNoteText());
        assertEquals(CREATION_DATE, addedNote.getCreationDate());
        List<Task> tasks = addedNote.getTasks();
        assertEquals(3, tasks.size());
        verifyTasksInfo(tasks);

        List<String> capturedTasksInfoList = taskInfoCaptor.getAllValues();
        capturedTasksInfoList.forEach(taskInfo -> assertEquals(TASK_INFO, taskInfo));

        Note capturedNote = noteCaptor.getValue();
        assertNull(capturedNote.getId());
        assertEquals(NOTE_TEXT, capturedNote.getNoteText());
        assertNotNull(capturedNote.getCreationDate());
        assertEquals(3, capturedNote.getTasks().size());
    }

    private void verifyTasksInfo(List<Task> tasks) {
        tasks.forEach(task -> {
            assertEquals(TASK_ID, task.getId());
            assertEquals(TASK_INFO, task.getTaskInfo());
            assertEquals(CREATION_DATE, task.getCreationDate());
            assertFalse(task.isCompletionStatus());
        });
    }

    private Note constructNote() {
        return Note.builder()
                .id(NOTE_ID)
                .noteText(NOTE_TEXT)
                .creationDate(CREATION_DATE)
                .tasks(constructTasksList())
                .build();
    }

    private NoteDto constructNoteDto() {
        return NoteDto.builder()
                .noteText(NOTE_TEXT)
                .tasks(constructTasksInfosList())
                .build();
    }

    private List<String> constructTasksInfosList() {
        return Arrays.asList(TASK_INFO, TASK_INFO, TASK_INFO);
    }

    private List<Task> constructTasksList() {
        return Arrays.asList(createMockTask(), createMockTask(), createMockTask());
    }

    private Task createMockTask() {
        return Task.builder()
                .id(TASK_ID)
                .taskInfo(TASK_INFO)
                .creationDate(CREATION_DATE)
                .completionStatus(false)
                .build();
    }
}