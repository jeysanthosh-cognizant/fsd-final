package com.fsd.finalproj.projectmanagerapi.controller;

import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;

import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import com.fsd.finalproj.projectmanagerapi.service.ProjectService;
import com.fsd.finalproj.projectmanagerapi.service.TaskService;
import com.fsd.finalproj.projectmanagerapi.service.UsersService;
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
    private TaskService taskService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> getAllTasks(){

       // List<Task> tasks = new ArrayList<>();

        return taskService.getAllTasks();
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public List<Task> addTask(@RequestBody Task task ){

        return taskService.saveTask(task);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public List<Users> addUser(@RequestBody Users user){

        return this.usersService.saveUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Users> getAllUsers(){

        return this.usersService.getAllUsers();
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public List<Project> addProject(@RequestBody Project project){
        return this.projectService.saveProject(project);
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public List<Project> getAllProjects(){
        return this.projectService.getAllProjects();
    }
}
