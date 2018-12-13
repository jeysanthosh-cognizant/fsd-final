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

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "ParentTask [parentId=" + parentId + ", parentTask=" + parentTask + "]";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + parentId;
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        ParentTask other = (ParentTask) obj;
        if (parentId != other.parentId) {
            return false;
        }
        return true;
    }
}
