package com.fsd.finalproj.projectmanagerapi.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "parent_task")
public class ParentTask {

    @Id
    private int parentId;

    private String parentTask;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }
}
