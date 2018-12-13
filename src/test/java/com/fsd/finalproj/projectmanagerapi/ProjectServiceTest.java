package com.fsd.finalproj.projectmanagerapi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fsd.finalproj.projectmanagerapi.dao.ProjectDao;
import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import com.fsd.finalproj.projectmanagerapi.service.ProjectService;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

public class ProjectServiceTest extends AbstractServiceTest{

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();


	List<Project> lstProjects= new ArrayList<>();

	@Test
    @junitparams.Parameters(source= TestDataProject.class, method = "provideAddProjects")
	public void testAddProject(Project addproject) {
		Mockito.when(projectDao.save(addproject))
				.thenReturn(addproject);
		lstProjects.add(addproject);
		Mockito.when(projectDao.findAll())
				.thenReturn(lstProjects);
		lstProjects = projectService.addProject(addproject);
		addproject.hashCode();
		addproject.equals(null);
		addproject.equals(new Users());
		boolean lstSucccess = true;
		if(!lstProjects.contains(addproject)) {
			lstSucccess = false;
		}
		assertTrue("Project add Failed", lstSucccess);

	}

	@Test
    @junitparams.Parameters(source= TestDataProject.class, method = "provideDelProjects")
	public void testDeleteProject(long projectId) {

		Project project =  new Project();
		project.setProjectId(projectId);
		Users user = new Users();
		user.setUserId(1);
		user.setFirstName("Fname");
		project.setUser(user);

		Set<Task> taskSet =  new HashSet<>();
		Task t = new Task();
		t.setTaskId(1);
		t.setTask("Task1");
		taskSet.add(t);
		project.setTaskSet(taskSet);
		lstProjects.add(project);
		Mockito.when(projectDao.findAll())
				.thenReturn(lstProjects);
		Mockito.when(usersDao.save(project.getUser()))
	      .thenReturn(project.getUser());
		Mockito.when(projectDao.findOne(projectId))
	      .thenReturn(project);
		/*Mockito.when(projectService.deleteProject(projectId))
				.thenReturn(lstProjects);*/
		lstProjects = projectService.deleteProject(projectId);
		ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		Mockito.verify(projectDao).delete( valueCapture.capture());
		long argProjectId = valueCapture.getValue();
		Project delProject = new Project();
		delProject.setProjectId(argProjectId);
		boolean avail = lstProjects.remove(delProject);
		boolean lstSucccess = false;
		if(avail && !lstProjects.contains(delProject)) {
			lstSucccess = true;
		}
		assertTrue("Project del Failed", lstSucccess);

	}

	@Test
    @junitparams.Parameters(source= TestDataProject.class, method = "provideEditProjects")
	public void testEditProject(Project editproject,String expectedEditProjectName) {
		Mockito.when(projectDao.save(editproject))
	      .thenReturn(editproject);
		lstProjects.add(editproject);
		/*Mockito.when(projectService.editProject( editproject))
				.thenReturn(lstProjects);*/
		Mockito.when(usersDao.save(editproject.getUser()))
	      .thenReturn(editproject.getUser());
		Mockito.when(projectDao.findAll())
	      .thenReturn(lstProjects);
		lstProjects = projectService.editProject( editproject);
		boolean lstSucccess = true;
		String actulEditProjectName= null;
		if(!lstProjects.contains(editproject)) {
			actulEditProjectName = lstProjects.get(lstProjects.indexOf(editproject)).getProject();
			lstSucccess = false;
		}
		assertTrue("Project add Failed", lstSucccess);
		assertFalse(expectedEditProjectName.equals(actulEditProjectName));

	}


	@Test
    @junitparams.Parameters(source= TestDataProject.class, method = "provideProjects")
	public void testviewProjects(List<Project> expectedLstProject) {
		Mockito.when(projectDao.findAll())
	      .thenReturn(lstProjects);
		lstProjects = projectService.viewProjects();
		boolean lstSucccess = true;
		if(!expectedLstProject.containsAll(lstProjects)) {
			lstSucccess = false;
		}
		assertTrue("Project Viewing failed", lstSucccess);
	}

	@Test
    @junitparams.Parameters(source= TestDataProject.class, method = "provideProjectsForSort")
	public void testSortProjects(List<Project> expectedLstProject,int sortType) {
		Mockito.when(projectDao.findAllByOrderByStartDateAsc())
	      .thenReturn(lstProjects);
		Mockito.when(projectDao.findAllByOrderByEndDateAsc())
	      .thenReturn(lstProjects);
		Mockito.when(projectDao.findAllByOrderByPriorityAsc())
	      .thenReturn(lstProjects);
		Mockito.when(projectDao.findAllByOrderByStartDateAsc())
				.thenReturn(lstProjects);
		lstProjects = projectService.sortProjects(sortType);
		boolean lstSucccess = true;
		if(!expectedLstProject.containsAll(lstProjects)) {
			lstSucccess = false;
		}
		assertTrue("Project Sorting failed", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataProject.class, method = "provideProjectsForSearch")
	public void testSearchByName(List<Project> expectedLstproject,String projectName) throws Exception{
		BDDMockito.given(projectDao.findByProjectContainingIgnoreCase(projectName)).willReturn(expectedLstproject);
		List<Project> lstResultProject = projectService.searchProjectByName(projectName);
		boolean lstSucccess = true;
		if(!expectedLstproject.containsAll(lstResultProject)) {
			lstSucccess = false;
		}
		assertTrue("Project search is not correct", lstSucccess);
	}
	

}
