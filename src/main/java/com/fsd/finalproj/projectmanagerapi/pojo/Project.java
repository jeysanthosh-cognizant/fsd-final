package com.fsd.finalproj.projectmanagerapi.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private int projectId;
    private String project;
    private Date startDate;
    private Date endDate;
    private int priority;

    @JsonIgnore
    private Set<Task> taskSet;

    @Formula("(select count(distinct t.task_id) from test.task t where t.project_id=project_id)")
    private int numberOfTasks;

    @Formula("(select count(distinct t.task_id) from test.task t where t.project_id=project_id and t.status='Completed')")
    private int completedTasks;

    Users user;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    /**
     * @return the taskSet
     */
    public Set<Task> getTaskSet() {
        return taskSet;
    }
    /**
     * @param taskSet the taskSet to set
     */
    public void setTaskSet(final Set<Task> taskSet) {
        this.taskSet = taskSet;
    }
    /**
     * @return the numberOfTasks
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }
    /**
     * @param numberOfTasks the numberOfTasks to set
     */
    public void setNumberOfTasks(final int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }
    /**
     * @return the completedTasks
     */
    public int getCompletedTasks() {
        return completedTasks;
    }
    /**
     * @param completedTasks the completedTasks to set
     */
    public void setCompletedTasks(final int completedTasks) {
        this.completedTasks = completedTasks;
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
    @Override
    public String toString() {
        return "Project [projectId=" + projectId + ", project=" + project + ", startDate=" + startDate + ", endDate="
                + endDate + ", priority=" + priority + ", numberOfTasks=" + numberOfTasks + ", completedTasks="
                + completedTasks + "]";
    }
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (int) (projectId ^ (projectId >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Project other = (Project) obj;
        if (projectId != other.projectId) {
            return false;
        }
        return true;
    }
}
