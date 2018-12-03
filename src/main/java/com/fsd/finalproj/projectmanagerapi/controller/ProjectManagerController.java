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
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public List<Users> addUser(@RequestBody Users user){

        return this.usersService.addUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public List<Users> saveUser(@RequestBody Users user){
        return this.usersService.editUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public List<Users> deleteUser(@RequestBody Users user){
        return this.usersService.deleteUser(user.getUserId());
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Users> getAllUsers(@RequestParam(value = "sorttype", required = false) Long sortType, @RequestParam(value="name",required = false) String userName){

        if(sortType != null){
            return this.usersService.sortUsers(sortType);
        }

        if(userName!=null){
            return this.usersService.searchUserByName(userName);
        }
        return this.usersService.viewUsers();
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public List<Project> addProject(@RequestBody Project project){
        return this.projectService.addProject(project);
    }

    @RequestMapping(value = "/projects", method = RequestMethod.PUT)
    public List<Project> saveProject(@RequestBody Project project){
        return this.projectService.editProject(project);
    }

    @RequestMapping(value = "/projects", method = RequestMethod.DELETE)
    public List<Project> deleteProject(@RequestBody Project project){
        return this.projectService.deleteProject(project.getProjectId());
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public List<Project> getAllProjects(@RequestParam(value = "sorttype", required = false) Long sortType, @RequestParam(value="projectName",required = false) String projectName){

        if(sortType != null){
            return this.projectService.sortProjects(sortType);
        }

        if(projectName!=null){
            return this.projectService.searchProjectByName(projectName);
        }
        return this.projectService.viewProjects();
    }
}
