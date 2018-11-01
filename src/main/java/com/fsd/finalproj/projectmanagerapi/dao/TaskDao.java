package com.fsd.finalproj.projectmanagerapi.dao;

import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TaskDao extends CrudRepository<Task, Long>{

}
