package com.fsd.finalproj.projectmanagerapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "parent_task")
public class ParentTask {

    @Id
    private int parentId;

    private String parentTask;

    /**
     * ParentTask Field
     */
    @OneToMany(cascade= {CascadeType.ALL} ,fetch = FetchType.EAGER, mappedBy="parentTask")
    @JsonIgnore
    private Set<Task> taskSet;

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

    /**
     * @return the taskSet
     */
    public Set<Task> getTaskSet() {
        return taskSet;
    }
    /**
     * @param taskSet the taskSet to set
     */
    public void setTaskSet(Set<Task> taskSet) {
        this.taskSet = taskSet;
    }
}
