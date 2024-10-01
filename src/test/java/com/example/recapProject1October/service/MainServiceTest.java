package com.example.recapProject1October.service;

import com.example.recapProject1October.dto.KanbanTask;
import com.example.recapProject1October.dto.TaskStatus;
import com.example.recapProject1October.repository.TaskRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainServiceTest {

    private final TaskRepository taskRepository = mock(TaskRepository.class);
    private final MainService mainService = new MainService(taskRepository);

    @Test
    public void getAllTasks_expectAllTasks() {
        //GIVEN
        //WHEN
        mainService.getAllTasks();
        //THEN
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void getTaskById_expectExistingTask() {
        //GIVEN
        KanbanTask task = new KanbanTask("1", "Test Task", TaskStatus.OPEN);
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        //WHEN
        KanbanTask result = mainService.getTaskById("1");
        //THEN
        assertNotNull(result);
        assertEquals("1", result.id());
        assertEquals("Test Task", result.description());
    }

    @Test
    public void addTask_expectCreatingTask() {
        //GIVEN
        KanbanTask task = new KanbanTask("1", "Test Task", TaskStatus.OPEN);
        //WHEN
        mainService.addTask(task);
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
        mainService.updateTask("1", updatedTask);
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
        mainService.deleteTask("1");
        //THEN
        verify(taskRepository, times(1)).delete(task);
    }
}