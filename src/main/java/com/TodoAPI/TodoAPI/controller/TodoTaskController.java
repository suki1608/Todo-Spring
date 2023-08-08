package com.TodoAPI.TodoAPI.controller;

import com.TodoAPI.TodoAPI.entity.Task;
import com.TodoAPI.TodoAPI.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoTaskController {

    private TaskService taskService;

    @Autowired
    public TodoTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> fetchTasks() {
        List<Task> taskList = this.taskService.findTasks();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<?> fetchTask(@PathVariable int taskId) {
        return ResponseEntity.ok(taskService.findTask(taskId));
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> saveTask(@RequestBody Task task) {
        Task savedTask= taskService.saveTask(task);
        if(savedTask == null) return new ResponseEntity<>("Request body not well defined.", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok("Task has been saved.");
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable int taskId) {
        Task curTask = taskService.findTask(taskId);
        if(curTask == null) return new ResponseEntity<>("No such task found",HttpStatus.NOT_FOUND);
        taskService.updateTask(task,taskId);
        return ResponseEntity.ok("Task updated successfully.");
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully.");
    }

}
