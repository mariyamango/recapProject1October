package com.example.recapProject1October.controller;

import com.example.recapProject1October.model.KanbanTask;
import com.example.recapProject1October.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<KanbanTask> getAllTasks(){
        return todoService.getAllTasks();
    }
    @GetMapping("/{id}")
    public KanbanTask getById(@PathVariable String id){
        KanbanTask task = todoService.getTaskById(id);
        if (task == null) {
            throw new NullPointerException("Task not found");
        }
        return task;
    }

    @PostMapping
    public void addTask(@RequestBody KanbanTask kanbanTask){
        todoService.addTask(kanbanTask);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable String id, @RequestBody KanbanTask task){
        todoService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id){
        todoService.deleteTask(id);
    }
}