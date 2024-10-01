package com.example.recapProject1October.service;

import com.example.recapProject1October.model.KanbanTask;
import com.example.recapProject1October.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TaskRepository taskRepository;
    private final ChatGptService chatGptService;

    public List<KanbanTask> getAllTasks() {
        return taskRepository.findAll();
    }

    public KanbanTask getTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void addTask (KanbanTask task) {
        String correctedDescription = chatGptService.correctText(task.description());
        KanbanTask correctedTask = new KanbanTask(task.id(), correctedDescription,task.status());
        taskRepository.save(correctedTask);
    }

    public void updateTask (String id, KanbanTask task) {
        KanbanTask taskToUpdate = taskRepository.findById(id).orElse(null);
        if (taskToUpdate != null) {
            taskRepository.delete(taskToUpdate);
            taskRepository.save(task);
        }
        else throw new NullPointerException("Task not found");
    }

    public void deleteTask (String id) {
        KanbanTask taskToDelete = taskRepository.findById(id).orElse(null);
        if (taskToDelete != null) {
            taskRepository.delete(taskToDelete);
        }
        else throw new NullPointerException("Task not found");
    }
}
