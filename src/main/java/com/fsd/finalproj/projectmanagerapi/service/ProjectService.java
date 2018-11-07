package com.fsd.finalproj.projectmanagerapi.service;

import com.fsd.finalproj.projectmanagerapi.dao.ProjectDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public List<Project> getAllProjects(){
        return (List<Project>)this.projectDao.findAll();
    }

    public List<Project> saveProject(Project project){
        this.projectDao.save(project);
        return (List<Project>)this.projectDao.findAll();
    }
}
