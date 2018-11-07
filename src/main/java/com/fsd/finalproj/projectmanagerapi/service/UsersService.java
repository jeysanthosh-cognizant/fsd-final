package com.fsd.finalproj.projectmanagerapi.service;

import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersDao usersDao;


    public List<Users> saveUser(Users user){

        this.usersDao.save(user);

        return (List<Users>)this.usersDao.findAll();
    }

    public List<Users> getAllUsers(){

        return (List<Users>)this.usersDao.findAll();
    }
}
