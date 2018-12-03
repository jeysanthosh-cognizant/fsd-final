package com.fsd.finalproj.projectmanagerapi.service;

import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersDao userDao;

    public List<Users> addUser(Users user) {
        userDao.save(user);
        return viewUsers();
    }


    public List<Users> deleteUser(long userId) {
        userDao.delete(userId);
        return viewUsers();
    }

    public List<Users> editUser(Users user) {
        userDao.save(user);
        return viewUsers();
    }

    public List<Users> viewUsers() {
        Iterable<Users> lstItr = userDao.findAll();
        List<Users> lstUsers = new ArrayList<>();
        lstItr.forEach(lstUsers::add);
        return lstUsers;
    }

    public List<Users> sortUsers(long sortType) {
        // TODO Auto-generated method stub
        Iterable<Users> lstItr = null;
        List<Users> lstUsers = new ArrayList<>();
        if(sortType ==1) {
            lstItr = userDao.findAllByOrderByFirstNameAsc();
        }else if(sortType ==2) {
            lstItr = userDao.findAllByOrderByLastNameAsc();
        }else if(sortType ==3) {
            lstItr = userDao.findAllByOrderByEmployeeIdAsc();
        }else {
            lstItr = userDao.findAllByOrderByFirstNameAsc();
        }

        lstItr.forEach(lstUsers::add);
        return lstUsers;
    }

    public List<Users> searchUserByName(String lastName){
        Iterable<Users> lstItr = null;
        List<Users> lstUsers = new ArrayList<>();
        lstItr = userDao.findByLastNameContainingIgnoreCase(lastName);
        lstItr.forEach(lstUsers::add);
        return lstUsers;

    }
}
