package com.fsd.finalproj.projectmanagerapi.service;

import com.fsd.finalproj.projectmanagerapi.dao.ProjectDao;
import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Autowired
    UsersDao userDao;

    @Autowired
    TaskDao taskDao;

    public List<Project> addProject(Project project) {
        Users user  = project.getUser();
        project.setUser(null);
        //project.setTaskSet(null);
        Project savedProject = projectDao.save(project);
        user.setProject(savedProject);
        //user.setTasks(null);
        userDao.save(user);
        return viewProjects();
    }

    public List<Project> deleteProject(long projectId) {
        Project project = projectDao.findOne(projectId);
        Users user = project.getUser();
        if(user!=null) {
            user.setProject(null);
            user.setTask(null);
            userDao.save(user);
        }
        Set<Task> taskSet = project.getTaskSet();
        for(Task task:taskSet) {
            task.setProject(null);
            task.setUser(null);
            taskDao.save(task);
            taskDao.delete((long)task.getTaskId());
        }
        project.getTaskSet().clear();
        project.setTaskSet(null);
        project.setUser(null);
        projectDao.save(project);
        projectDao.delete(projectId);
        return viewProjects();
    }

    public List<Project> editProject(Project project) {
        projectDao.save(project);
        Users user = project.getUser();
        user.setProject(project);
        userDao.save(user);
        //project.
        return viewProjects();
    }

    public List<Project> viewProjects() {
        List<Project> lstProjects = new ArrayList<>();
        Iterable<Project> lstItr = projectDao.findAll();
        lstItr.forEach(lstProjects::add);
        return lstProjects;
    }

    public List<Project> sortProjects(long sortType) {
        // TODO Auto-generated method stub
        Iterable<Project> lstItr = null;
        List<Project> lstProjects = new ArrayList<>();
        if(sortType ==1) {
            lstItr = projectDao.findAllByOrderByStartDateAsc();
        }else if(sortType ==2) {
            lstItr = projectDao.findAllByOrderByEndDateAsc();
        }else if(sortType ==3) {
            lstItr = projectDao.findAllByOrderByPriorityAsc();
        }else if(sortType ==4) {
            lstItr = projectDao.findAllByOrderByCompletedTasksAsc();
        }else {
            lstItr = projectDao.findAllByOrderByStartDateAsc();
        }

        lstItr.forEach(lstProjects::add);
        return lstProjects;
    }

    public List<Project> searchProjectByName(String project) {
        Iterable<Project> lstItr = null;
        List<Project> lsProjects = new ArrayList<>();
        lstItr = projectDao.findByProjectContainingIgnoreCase(project);
        lstItr.forEach(lsProjects::add);
        return lsProjects;

    }
}
