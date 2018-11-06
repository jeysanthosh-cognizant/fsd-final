package com.fsd.finalproj.projectmanagerapi;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.finalproj.projectmanagerapi.controller.ProjectManagerController;
import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.service.TaskService;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ProjectManagerController.class}, secure = false)
@ContextConfiguration(classes = ProjectManagerApiApplication.class)
public abstract class AbstractTest {

        @Autowired
        protected MockMvc mvc;
       // @Autowired
        //WebApplicationContext webApplicationContext;

        @MockBean
        TaskService taskService;

        /*@MockBean
        UsersDao usersDao;*/


        protected void setUp() {
           // mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            Task mockTask = new Task();
            mockTask.setTaskId(1);

            List<Task> taskList = new ArrayList<>();
            taskList.add(mockTask);
            BDDMockito.given(taskService.getAllTasks()).willReturn(taskList);
            //this.taskDao.save(mockTask);
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
