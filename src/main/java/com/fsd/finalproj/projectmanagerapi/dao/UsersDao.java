package com.fsd.finalproj.projectmanagerapi.dao;

import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UsersDao extends CrudRepository<Users, Long>{
}
