package com.fsd.finalproj.projectmanagerapi.dao;

import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UsersDao extends CrudRepository<Users, Long>{

    public List<Users> findAllByOrderByLastNameAsc();
    public List<Users> findAllByOrderByEmployeeIdAsc();
    public List<Users> findAllByOrderByFirstNameAsc();
    public List<Users> findByLastNameContainingIgnoreCase(String lastName);
}
