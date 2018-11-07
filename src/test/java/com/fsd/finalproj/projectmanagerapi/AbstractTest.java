package com.fsd.finalproj.projectmanagerapi;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.finalproj.projectmanagerapi.controller.ProjectManagerController;
import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import com.fsd.finalproj.projectmanagerapi.service.ProjectService;
import com.fsd.finalproj.projectmanagerapi.service.TaskService;
import com.fsd.finalproj.projectmanagerapi.service.UsersService;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ProjectManagerController.class}, secure = false)
@ContextConfiguration(classes = ProjectManagerApiApplication.class)
public abstract class AbstractTest {

        @Autowired
        protected MockMvc mvc;

        @MockBean
        TaskService taskService;

        @MockBean
        UsersService usersService;

        @MockBean
        ProjectService projectService;


        protected void setUp() {
           // mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            Task mockTask = new Task();
            mockTask.setTaskId(1);

            List<Task> taskList = new ArrayList<>();
            taskList.add(mockTask);
            BDDMockito.given(taskService.getAllTasks()).willReturn(taskList);

            List<Users> mockUsers = new ArrayList<>();
            Users mockUser = new Users();
            mockUser.setFirstName("John");
            mockUser.setLastName("Doe");
            mockUser.setUserId(1);
            mockUsers.add(mockUser);

            BDDMockito.given(usersService.getAllUsers()).willReturn(mockUsers);

            Project project = new Project();
            project.setProjectId(1);
            project.setProject("Mock Project");
            Date startDate = Date.valueOf(LocalDate.now());
            Date endDate = Date.valueOf(LocalDate.now().plusDays(2));
            project.setStartDate(startDate);
            project.setEndDate(endDate);
            project.setPriority(1);

            List<Project> projects = new ArrayList<>();
            projects.add(project);
            BDDMockito.given(projectService.getAllProjects()).willReturn(projects);
        }
        protected String mapToJson(Object obj) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        }
        protected <T> T mapFromJson(String json, Class<T> clazz)
                throws JsonParseException, JsonMappingException, IOException {

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, clazz);
        }
}
