package com.fsd.finalproj.projectmanagerapi.controller;

import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.ParentTask;
import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;

import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import com.fsd.finalproj.projectmanagerapi.service.ProjectService;
import com.fsd.finalproj.projectmanagerapi.service.TaskService;
import com.fsd.finalproj.projectmanagerapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RestController
public class ProjectManagerController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UsersService usersService;


    @Autowired
    private ProjectService projectService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public List<Users> addUser(@RequestBody Users user){

        return this.usersService.addUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public List<Users> saveUser(@RequestBody Users user){
        return this.usersService.editUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public List<Users> deleteUser(@RequestParam(value = "userId", required = false) Long userId){
        return this.usersService.deleteUser(userId);
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
    public List<Project> deleteProject(@RequestParam(value = "projectId", required = false) Long projectId){
        return this.projectService.deleteProject(projectId);
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

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public String addTask(@RequestBody Task task){
        taskService.addTask(task);
        String responseMsg = messageSource.getMessage("ADDTASK_MESSAGE_SUCCESS", new Object[] {task.getTask()}, Locale.US);
        return responseMsg;
    }

    @RequestMapping(value = "/parenttask",
            method = RequestMethod.POST,produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String addParentTask(@RequestBody ParentTask parentTask){
        taskService.addParentTask(parentTask);
        String responseMsg = messageSource.getMessage("PARENT_TASK_MESSAGE_SUCCESS", new Object[] {parentTask.getParentTask()},Locale.US);
        return responseMsg;
    }

    @RequestMapping(value = "/parenttask",
            method = RequestMethod.GET,produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ParentTask> viewParentTasks() {
        return taskService.viewParentTasks();
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    public String saveTask(@RequestBody Task task){
        taskService.editTask(task);
        String responseMsg = messageSource.getMessage("EDITTASK_MESSAGE_SUCCESS", new Object[] {task.getTask()},Locale.US);
        return responseMsg;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.DELETE)
    public List<Task> deleteTask(@RequestParam(value = "taskId", required = false) Long taskId){
        return this.taskService.deleteTask(taskId);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> getAllTasks(@RequestParam(value = "sorttype", required = false) Long sortType, @RequestParam(value="taskId",required = false) Long taskId,
                                  @RequestParam(value="projectId",required = false) Long projectId,
                                  @RequestParam(value="taskName",required = false) String taskName){

        if(sortType != null){
            return this.taskService.sortTasks(sortType);
        }

        if(taskName!=null){
            return this.taskService.searchTaskByName(taskName);
        }
        if(projectId!=null){
            return taskService.searchTaskByProjectId(projectId);
        }
        if(taskId!=null){
            List<Task> taskList = new ArrayList<>();
            taskList.add(taskService.searchTaskByTaskId(taskId));
            return taskList;
        }
        return this.taskService.viewTasks();
    }
}
