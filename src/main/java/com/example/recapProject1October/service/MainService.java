package com.example.recapProject1October.service;

import com.example.recapProject1October.dto.KanbanTask;
import com.example.recapProject1October.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    private final TaskRepository taskRepository;

    @Autowired
    public MainService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<KanbanTask> getAllTasks() {
        return taskRepository.findAll();
    }

    public KanbanTask getTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void addTask (KanbanTask task) {
        taskRepository.save(task);
    }
}
