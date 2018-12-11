package com.fsd.finalproj.projectmanagerapi;

import com.fsd.finalproj.projectmanagerapi.controller.ProjectManagerController;
import com.fsd.finalproj.projectmanagerapi.dao.ParentTaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.ProjectDao;
import com.fsd.finalproj.projectmanagerapi.dao.TaskDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.service.ParentTaskService;
import com.fsd.finalproj.projectmanagerapi.service.ProjectService;
import com.fsd.finalproj.projectmanagerapi.service.TaskService;
import com.fsd.finalproj.projectmanagerapi.service.UsersService;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ProjectManagerApiApplication.class)
public abstract class AbstractServiceTest {

    @MockBean
    UsersService usersService;

    @MockBean
    ProjectService projectService;

    @MockBean
    TaskService taskService;

    @MockBean
    ParentTaskService parentTaskService;

    @MockBean
    UsersDao usersDao;

    @MockBean
    TaskDao taskDao;

    @MockBean
    ParentTaskDao parentTaskDao;

    @MockBean
    ProjectDao projectDao;

}
