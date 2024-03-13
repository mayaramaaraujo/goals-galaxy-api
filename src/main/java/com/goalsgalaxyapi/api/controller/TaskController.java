package com.goalsgalaxyapi.api.controller;

import com.goalsgalaxyapi.domain.model.Task;
import com.goalsgalaxyapi.domain.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) {
        return taskRepository.getReferenceById(id);
    }

    @PostMapping
    public void save(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }
}
