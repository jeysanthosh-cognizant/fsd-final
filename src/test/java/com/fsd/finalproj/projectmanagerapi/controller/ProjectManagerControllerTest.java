package com.fsd.finalproj.projectmanagerapi.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.finalproj.projectmanagerapi.AbstractTest;
import com.fsd.finalproj.projectmanagerapi.ProjectManagerApiApplication;
import com.fsd.finalproj.projectmanagerapi.pojo.Project;
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
import java.sql.Date;
import java.time.LocalDate;
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

        MvcResult result =  mvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(super.mapToJson(testUSer))).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertTrue(result.getResponse().getStatus()==200);
    }

    @Test
    public void getAllUsers() throws Exception {
        MvcResult result =  mvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        List<Users> userlist = (List<Users>)mapFromJson(result.getResponse().getContentAsString(),Object.class);
        assertTrue(userlist.size() > 0);
    }

    @Test
    public void addProject() throws Exception {

        Project testProject = new Project();
        testProject.setProjectId(1);
        testProject.setProject("Test Project");
        Date startDate = Date.valueOf(LocalDate.now());
        Date endDate = Date.valueOf(LocalDate.now().plusDays(7));
        testProject.setStartDate(startDate);
        testProject.setEndDate(endDate);
        testProject.setPriority(1);

        MvcResult result =  mvc.perform(MockMvcRequestBuilders.post("/projects").contentType(MediaType.APPLICATION_JSON).content(super.mapToJson(testProject))).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertTrue(result.getResponse().getStatus()==200);
    }

    @Test
    public void getAllProjects() throws Exception {
        MvcResult result =  mvc.perform(MockMvcRequestBuilders.get("/projects").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        List<Project> projectlist = (List<Project>)mapFromJson(result.getResponse().getContentAsString(),Object.class);
        assertTrue(projectlist.size() > 0);
    }

}