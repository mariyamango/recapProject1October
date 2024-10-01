package com.example.recapProject1October.controller;

import com.example.recapProject1October.dto.KanbanTask;
import com.example.recapProject1October.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/todo")
    public List<KanbanTask> getAllTasks(){
        return mainService.getAllTasks();
    }
    @GetMapping("/todo/{id}")
    public KanbanTask getById(@PathVariable String id){
        return mainService.getTaskById(id);
    }

    @PostMapping("/todo")
    public void addTask(@RequestBody KanbanTask kanbanTask){
        mainService.addTask(kanbanTask);
    }

    @PutMapping("/todo/{id}")
    public void updateTask(@PathVariable String id, @RequestBody KanbanTask task){
        mainService.updateTask(task);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTask(@PathVariable String id){
        mainService.deleteTask(id);
    }
}