package com.fsd.finalproj.projectmanagerapi.pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private int taskId;
    private int parentId;
    private int projectId;
    private String task;
    private Date startDate;
    private Date endDate;
    private int priority;
    private String status;

    @JsonManagedReference
    private Users user;

    /**
     * Parentask field
     */
    @JoinColumn(name="Parent_Id",nullable=true,insertable=true,updatable=true)
    private ParentTask parentTask;
    /**
     * task field
     */
    @JoinColumn(name="Project_Id",nullable=false,insertable=true,updatable=true)
    private Project project;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }
    /**
     * @param project the project to set
     */
    public void setProject(final Project project) {
        this.project = project;
    }

    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(final Users user) {
        this.user = user;
    }
}
