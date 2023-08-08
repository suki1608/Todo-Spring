package com.TodoAPI.TodoAPI.service;

import com.TodoAPI.TodoAPI.entity.Task;
import com.TodoAPI.TodoAPI.exception.TaskNotFoundException;
import com.TodoAPI.TodoAPI.repository.TaskRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task findTask(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isEmpty()) throw  new TaskNotFoundException("No such task exist with the id - " + id);
        return task.get();
    }

    @Override
    public Task saveTask(Task task) {
        Task savedTask = null;
        try {
            savedTask = taskRepository.save(task);
        } catch (ConstraintViolationException ex) {
            return null;
        }
        return savedTask;

    }

    @Override
    public Task updateTask(Task task,int id) {
        Task savedTask = null;
        try {
            savedTask = taskRepository.save(task);
        } catch (ConstraintViolationException ex) {
            return null;
        }
        return savedTask;
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
