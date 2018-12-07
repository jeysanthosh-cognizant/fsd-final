package com.fsd.finalproj.projectmanagerapi.pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private long taskId;

    private String task;
    private Date startDate;
    private Date endDate;
    private long priority;
    private String status;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, mappedBy="task")
    private Users user;

    public ParentTask getParentTask() {
        return parentTask;
    }

    public void setParentTask(ParentTask parentTask) {
        this.parentTask = parentTask;
    }

    /**
     * Parentask field
     */

    @ManyToOne
    @JoinColumn(name="parentId",nullable=true,insertable=true,updatable=true)
    @NotFound(action = NotFoundAction.IGNORE)
    private ParentTask parentTask;
    /**
     * task field
     */

    @ManyToOne
    @JoinColumn(name="projectId",nullable=false,insertable=true,updatable=true)
    private Project project;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
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

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
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
