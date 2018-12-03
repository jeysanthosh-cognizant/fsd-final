package com.fsd.finalproj.projectmanagerapi.service;

import com.fsd.finalproj.projectmanagerapi.dao.ParentTaskDao;
import com.fsd.finalproj.projectmanagerapi.pojo.ParentTask;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentTaskService {

    @Autowired
    ParentTaskDao parentTaskDao;

    public List<ParentTask> getAllParentTasks(){
        return (List<ParentTask>)this.parentTaskDao.findAll();
    }

    public List<ParentTask> saveTask(ParentTask task){
        this.parentTaskDao.save(task);
        return (List<ParentTask>)this.parentTaskDao.findAll();
    }
}
