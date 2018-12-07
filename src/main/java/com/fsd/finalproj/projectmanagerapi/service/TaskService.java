package com.fsd.finalproj.projectmanagerapi.service;

import com.fsd.finalproj.projectmanagerapi.dao.ParentTaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.ParentTask;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private ParentTaskDao parentTaskDao;

    @Autowired
    private UsersDao usersDao;

    public List<Task> addTask(Task task) {
        Users user = task.getUser();
        task.setUser(null);
        if(task.getParentTask()==null || task.getParentTask().getParentTask()==null) {
            task.setParentTask(null);
        }
        Task savedTask =taskDao.save(task);
        user.setTask(savedTask);
        usersDao.save(user);
        return viewTasks();
    }

    public List<ParentTask> addParentTask(ParentTask parentTask){
        parentTaskDao.save(parentTask);
        return viewParentTasks();
    }

    public List<ParentTask> viewParentTasks() {
        Iterable<ParentTask> lstItr = parentTaskDao.findAll();
        List<ParentTask> lstTasks = new ArrayList<>();
        lstItr.forEach(lstTasks::add);
        return lstTasks;
    }

    public List<Task> deleteTask(long taskId) {
        taskDao.delete(taskId);
        return viewTasks();
    }

    public List<Task> editTask(Task task) {
        taskDao.save(task);
        return viewTasks();
    }

    public List<Task> viewTasks() {
        Iterable<Task> lstItr = taskDao.findAll();
        List<Task> lstTasks = new ArrayList<>();
        lstItr.forEach(lstTasks::add);
        return lstTasks;
    }

    public List<Task> sortTasks(long sortType) {
        Iterable<Task> lstItr = null;
        List<Task> lstTasks = new ArrayList<>();
        if(sortType ==1) {
            lstItr = taskDao.findAllByOrderByStartDateAsc();
        }else if(sortType ==2) {
            lstItr = taskDao.findAllByOrderByEndDateAsc();
        }else if(sortType ==3) {
            lstItr = taskDao.findAllByOrderByPriorityAsc();
        }else {
            lstItr = taskDao.findAllByOrderByStatusAsc();
        }

        lstItr.forEach(lstTasks::add);
        return lstTasks;
    }

    public List<Task> searchTaskByName(String taskName){
        Iterable<Task> lstItr = null;
        List<Task> lstTasks = new ArrayList<>();
        lstItr = taskDao.findByTaskContainingIgnoreCase(taskName);
        lstItr.forEach(lstTasks::add);
        return lstTasks;
    }

    public List<Task> searchTaskByProjectId(long projectId){
        Iterable<Task> lstItr = null;
        List<Task> lstTasks = new ArrayList<>();
        lstItr = taskDao.findByProject(projectId);
        lstItr.forEach(lstTasks::add);
        return lstTasks;
    }

    public Task searchTaskByTaskId(long taskId){
        return taskDao.findOne(taskId);
    }

}
