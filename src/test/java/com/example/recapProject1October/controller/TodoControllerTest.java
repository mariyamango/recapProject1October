package com.example.recapProject1October.controller;

import com.example.recapProject1October.model.KanbanTask;
import com.example.recapProject1October.model.TaskStatus;
import com.example.recapProject1October.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void getAllTasks_shouldReturnTasksList() throws Exception {
        KanbanTask task1 = new KanbanTask("1", "Task 1", TaskStatus.OPEN);
        KanbanTask task2 = new KanbanTask("2", "Task 2", TaskStatus.OPEN);
        when(todoService.getAllTasks()).thenReturn(Arrays.asList(task1, task2));
        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));
    }

    @Test
    void getTaskById_shouldReturnTask() throws Exception {
        KanbanTask task1 = new KanbanTask("1", "Task 1", TaskStatus.OPEN);
        when(todoService.getTaskById("1")).thenReturn(task1);
        mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("Task 1"));
    }

    @Test
    void addTask_shouldAddTask() throws Exception {
        KanbanTask task = new KanbanTask("1", "Task 1", TaskStatus.OPEN);
        String taskJson = "{\"id\":\"1\",\"description\":\"Task 1\",\"status\":\"OPEN\"}";
        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk());
        verify(todoService, times(1)).addTask(task);
    }

    @Test
    void updateTask_shouldUpdateTask() throws Exception {
        KanbanTask updateTask = new KanbanTask("1", "Updated Task 1", TaskStatus.DONE);
        String taskJson = "{\"id\":\"1\",\"description\":\"Updated Task 1\",\"status\":\"DONE\"}";
        mockMvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                        .andExpect(status().isOk());
        verify(todoService, times(1)).updateTask("1", updateTask);
    }

    @Test
    void deleteTask_shouldRemoveTask() throws Exception {
        mockMvc.perform(delete("/api/todo/1"))
                .andExpect(status().isOk());
        verify(todoService, times(1)).deleteTask("1");
    }
}