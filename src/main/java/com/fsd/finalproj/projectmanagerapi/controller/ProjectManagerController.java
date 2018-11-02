package com.fsd.finalproj.projectmanagerapi.controller;

import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;

import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectManagerController {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UsersDao usersDao;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> getAllTasks(){

        List<Task> tasks = new ArrayList<>();

        return (List<Task>)taskDao.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public List<Users> addUser(@RequestBody Users user){

        this.usersDao.save(user);

        return (List<Users>)this.usersDao.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Users> getAllUsers(){

        return (List<Users>)this.usersDao.findAll();
    }
}
