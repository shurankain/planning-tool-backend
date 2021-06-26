package com.shurankain.planning.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shurankain.planning.app.dto.TaskDto;
import com.shurankain.planning.app.persistence.model.Task;
import com.shurankain.planning.app.persistence.repsoitory.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    public static final String TEST_TASK_ID = "testTaskId";
    public static final String TEST_TASK_INFO = "testTaskInfo";
    public static final LocalDateTime TEST_TASK_DATE = LocalDateTime.of(2021, 6, 26, 11, 20);

    @Captor
    private ArgumentCaptor<Task> taskCaptor;
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    void whenAddTaskCalledThenSavedTaskInfoShouldBeReturned() {
        when(taskRepository.insert(any(Task.class))).thenReturn(createMockTask());

        var addedTask = taskService.addTask(TEST_TASK_INFO);

        verify(taskRepository, times(1)).insert(taskCaptor.capture());
        assertEquals(TEST_TASK_ID, addedTask.getId());
        assertEquals(TEST_TASK_INFO, addedTask.getTaskInfo());
        assertEquals(TEST_TASK_DATE, addedTask.getCreationDate());
        assertFalse(addedTask.isCompletionStatus());

        Task capturedTask = taskCaptor.getValue();
        assertNull(capturedTask.getId());
        assertEquals(TEST_TASK_INFO, capturedTask.getTaskInfo());
        assertNotNull(capturedTask.getCreationDate());
        assertFalse(capturedTask.isCompletionStatus());
    }

    @Test
    void whenEditTaskMethodInvokedWithProperDataThenTaskIdUpdated() {
        when(taskRepository.findById(TEST_TASK_ID)).thenReturn(Optional.of(createMockTask()));
        when(taskRepository.save(any(Task.class))).thenReturn(createMockTask());

        var addedTask = taskService.editTask(createMockTaskDto());

        verify(taskRepository, times(1)).findById(TEST_TASK_ID);
        verify(taskRepository, times(1)).save(taskCaptor.capture());
        assertEquals(TEST_TASK_ID, addedTask.getId());
        assertEquals(TEST_TASK_INFO, addedTask.getTaskInfo());
        assertEquals(TEST_TASK_DATE, addedTask.getCreationDate());
        assertFalse(addedTask.isCompletionStatus());

        Task capturedTask = taskCaptor.getValue();
        assertEquals(TEST_TASK_ID, capturedTask.getId());
        assertEquals(TEST_TASK_INFO, capturedTask.getTaskInfo());
        assertEquals(TEST_TASK_DATE, capturedTask.getCreationDate());
        assertFalse(capturedTask.isCompletionStatus());
    }

    private Task createMockTask() {
        return Task.builder()
                .id(TEST_TASK_ID)
                .taskInfo(TEST_TASK_INFO)
                .creationDate(TEST_TASK_DATE)
                .completionStatus(false)
                .build();
    }

    private TaskDto createMockTaskDto() {
        return TaskDto.builder()
                .id(TEST_TASK_ID)
                .taskInfo(TEST_TASK_INFO)
                .creationDate(TEST_TASK_DATE)
                .completionStatus(false)
                .build();
    }
}