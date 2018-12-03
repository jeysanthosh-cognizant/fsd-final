package com.fsd.finalproj.projectmanagerapi.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue
    private int userId;
    private String firstName;
    private String lastName;
    private int employeeId;

    @Column(name = "project_id", unique = true, nullable = false)
    private int projectId;
    private int taskId;

    @JoinColumn(name="Project_Id",nullable=true,insertable=false,updatable=true)
    @JsonIgnore
    //@JsonBackReference
    private Project project;

    @JoinColumn(name="Task_Id",nullable=true,insertable=false,updatable=true)
    //@JsonIgnore
    @JsonBackReference
    private Task task;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
     * @return the task
     */
    public Task getTask() {
        return task;
    }
    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", EmployeeId="
                + employeeId + "]";
    }

    @Override
    public int hashCode() {
        final long prime = 31;
        long result = 1;
        result = prime * result + employeeId;
        result = prime * result + userId;
        return (int)result;
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
        Users other = (Users) obj;
        if (userId != other.userId) {
            return false;
        }
        return true;
    }
}
