package com.TodoAPI.TodoAPI.service;

import com.TodoAPI.TodoAPI.entity.Task;

import java.util.List;

public interface TaskService {
    public List<Task> findTasks();

    public Task findTask(int id);

    public Task saveTask(Task task);

    public Task updateTask(Task task,int id);

    public void deleteTask(int id);


}
