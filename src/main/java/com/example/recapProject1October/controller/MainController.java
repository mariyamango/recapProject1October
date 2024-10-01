package com.example.recapProject1October.controller;

import com.example.recapProject1October.dto.KanbanTask;
import com.example.recapProject1October.service.MainService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/todo")
    public void addTask(@RequestBody KanbanTask kanbanTask){
        mainService.addTask(kanbanTask);
    }
}