package com.fsd.finalproj.projectmanagerapi.dao;

import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ProjectDao extends CrudRepository<Project, Long> {

    public List<Project> findAllByOrderByStartDateAsc();
    public List<Project> findAllByOrderByEndDateAsc();
    public List<Project> findAllByOrderByPriorityAsc();
    public List<Project> findAllByOrderByCompletedTasksAsc();
    public List<Project> findByProjectContainingIgnoreCase(String project);
}
