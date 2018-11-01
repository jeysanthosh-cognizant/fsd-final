package com.fsd.finalproj.projectmanagerapi.controller;

import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectManagerController {

    @Autowired
    private TaskDao taskDao;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> getAllTasks(){

        List<Task> tasks = new ArrayList<>();

        return (List<Task>)taskDao.findAll();
    }
}
