package com.fsd.finalproj.projectmanagerapi.dao;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ProjectDao extends CrudRepository {
}
