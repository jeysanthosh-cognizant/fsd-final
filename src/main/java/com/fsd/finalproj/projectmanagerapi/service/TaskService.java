package com.fsd.finalproj.projectmanagerapi.service;

import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public List<Task> getAllTasks(){
        return (List<Task>)this.taskDao.findAll();
    }

    public List<Task> saveTask(Task task){
        this.taskDao.save(task);
        return (List<Task>)this.taskDao.findAll();
    }
}
