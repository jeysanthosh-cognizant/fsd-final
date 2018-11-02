package com.fsd.finalproj.projectmanagerapi.dao;

import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ProjectDao extends CrudRepository<Project, Long> {
}
