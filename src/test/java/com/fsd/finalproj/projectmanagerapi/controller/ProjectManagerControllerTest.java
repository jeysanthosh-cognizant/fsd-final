package com.fsd.finalproj.projectmanagerapi.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.finalproj.projectmanagerapi.AbstractTest;
import com.fsd.finalproj.projectmanagerapi.ProjectManagerApiApplication;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.awt.print.Book;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ProjectManagerController.class}, secure=false)
@ContextConfiguration(classes = ProjectManagerApiApplication.class)
public class ProjectManagerControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAllTasks() throws Exception {
        MvcResult result =  mvc.perform(MockMvcRequestBuilders.get("/tasks").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        List<Task> tasklist = (List<Task>)mapFromJson(result.getResponse().getContentAsString(),Object.class);
         assertTrue(tasklist.size() > 0);
    }

    @Test
    public void addUser() throws Exception {

        Users testUSer = new Users();
        testUSer.setFirstName("John");
        testUSer.setLastName("Doe");
        testUSer.setEmployeeId(123456);

    }

}