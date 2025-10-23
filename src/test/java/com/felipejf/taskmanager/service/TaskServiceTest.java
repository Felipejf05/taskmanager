package com.felipejf.taskmanager.service;

import com.felipejf.taskmanager.entity.Task;
import com.felipejf.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Estudar Java");
        task.setDescription("Aprimorar conhecimento em Java");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void shouldAddTaskSuccessfully() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task saved = taskService.addTask(task);

        assertNotNull(saved, "A tarefa salva não deveria ser nula");
        assertEquals("Estudar Java", saved.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void shouldReturnAllTasks() {
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> tasks = taskService.getTasks();

        assertEquals(1, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void shouldFindTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task found = taskService.findById(1L);

        assertEquals("Estudar Java", found.getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void shouldDeleteTaskById() {
        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldUpdateTaskSuccessfully() {
        Task updatedTask = new Task();
        updatedTask.setTitle("Estudar Spring Boot");
        updatedTask.setDescription("Estudar o framework Spring Boot em detalhes");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.updateTask(1L, updatedTask);

        assertNotNull(result);
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFoundForUpdate() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> taskService.updateTask(1L, task));

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, never()).save(any(Task.class));
    }
}