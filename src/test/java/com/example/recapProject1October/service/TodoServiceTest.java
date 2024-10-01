package com.example.recapProject1October.service;

import com.example.recapProject1October.model.KanbanTask;
import com.example.recapProject1October.model.TaskStatus;
import com.example.recapProject1October.repository.TaskRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private final TaskRepository taskRepository = mock(TaskRepository.class);
    private final ChatGptService chatGptService = mock(ChatGptService.class);
    private final TodoService todoService = new TodoService(taskRepository, chatGptService);

    @Test
    public void getAllTasks_expectAllTasks() {
        //GIVEN
        //WHEN
        todoService.getAllTasks();
        //THEN
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void getTaskById_expectExistingTask() {
        //GIVEN
        KanbanTask task = new KanbanTask("1", "Test Task", TaskStatus.OPEN);
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        //WHEN
        KanbanTask result = todoService.getTaskById("1");
        //THEN
        assertNotNull(result);
        assertEquals("1", result.id());
        assertEquals("Test Task", result.description());
    }

    @Test
    public void addTask_expectCreatingTask() {
        //GIVEN
        KanbanTask task = new KanbanTask("1", "Test Task", TaskStatus.OPEN);
        when(chatGptService.correctText("Test Task")).thenReturn("Test Task");
        //WHEN
        todoService.addTask(task);
        //THEN
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void updateTask_expectUpdatingTask() {
        //GIVEN
        KanbanTask task = new KanbanTask("1", "Test Task", TaskStatus.OPEN);
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        KanbanTask updatedTask = new KanbanTask("1", "Updated Task", TaskStatus.DONE);
        //WHEN
        todoService.updateTask("1", updatedTask);
        //THEN
        verify(taskRepository, times(1)).save(updatedTask);
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    public void deleteTask_expectDeletingTask() {
        //GIVEN
        KanbanTask task = new KanbanTask("1", "Test Task", TaskStatus.OPEN);
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        //WHEN
        todoService.deleteTask("1");
        //THEN
        verify(taskRepository, times(1)).delete(task);
    }
}