package com.fsd.finalproj.projectmanagerapi;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.fsd.finalproj.projectmanagerapi.controller.ProjectManagerController;
import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import com.fsd.finalproj.projectmanagerapi.service.ProjectService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import junitparams.JUnitParamsRunner;



//@UseParametersRunnerFactory(SpringParametersRunnerFactory.class)
//@RunWith(SpringRunner.class)
@SuppressWarnings("PMD")

public class ProjectControllerTest extends AbstractTest{
	
	public ProjectControllerTest() {
		
	}

	@ClassRule
	public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();



	@Autowired
	private MockMvc mvc;


	List<Project> lstProjects= new ArrayList<>();

	@Before
	public void setUp() {
		Project project1 = new Project();
		project1.setEndDate(new Date(System.currentTimeMillis()));
		project1.setPriority(40);
		project1.setProjectId(111);
		project1.setStartDate(new Date(System.currentTimeMillis()));
		project1.setProject("COMS1");
		//project1.setStatus("Completed");
		lstProjects.add(project1);
		Project project2 = new Project();
		project2.setEndDate(new Date(System.currentTimeMillis()));
		project2.setPriority(40);
		project2.setProjectId(222);
		project2.setProject("COMS2");
		project2.setStartDate(new Date(System.currentTimeMillis()));
		//project2.setStatus("Completed");
		lstProjects.add(project2);
	}

	@Test
	@junitparams.Parameters(source= TestDataProject.class, method = "provideProjects")
	public void testlistAllProjects(List<Project> expectedLstproject) throws Exception{

		BDDMockito.given(projectService.viewProjects()).willReturn(lstProjects);

		MvcResult result = mvc.perform(get("/projects")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Project> lstResultProject = mapper.readValue(resultJson, new TypeReference<List<Project>>(){});
		boolean lstSucccess = true;
		for(Project actualProject: lstResultProject) {
			if(!expectedLstproject.contains(actualProject)) {
				lstSucccess = false;
				break;
			}
		}
		assertTrue("Project listing is not correct", lstSucccess);
	}



	@Test
	@junitparams.Parameters(source= TestDataProject.class, method = "provideAddProjects")
	public void testAddProject(Project addedProject) throws Exception{
		lstProjects.add(addedProject);
		BDDMockito.given(projectService.addProject(addedProject)).willReturn(lstProjects);
		ObjectMapper mapper = new ObjectMapper();
		String inputJson = mapper.writeValueAsString(addedProject);
		RequestBuilder request = post("/projects").content(inputJson)./*
			        .param("employeeId", addedProject.getEmployeeId()+"")
			        .param("firstName", addedProject.getFirstName()+"")
			        .param("lastName", addedProject.getLastName()+"")
			        .param("projectId", addedProject.getProjectId()+"").*/
				contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		List<Project> lstResultProject = mapper.readValue(resultJson, new TypeReference<List<Project>>(){});
		boolean lstSucccess = true;
		for(Project actualProject: lstResultProject) {
			if(!lstProjects.contains(actualProject)) {
				lstSucccess = false;
				break;
			}
		}


		assertTrue("Project Addition failed", lstSucccess);
	}


	@Test
	@junitparams.Parameters(source= TestDataProject.class, method = "provideEditProjects")
	public void testEditProject(Project editProject,String expectedProjectName) throws Exception{
		//lstProjects.add(editProject);
		lstProjects.add(editProject);
		BDDMockito.given(projectService.editProject(editProject)).willReturn(lstProjects);
		ObjectMapper mapper = new ObjectMapper();
		String inputJson = mapper.writeValueAsString(editProject);
		String uri = "/projects";
		RequestBuilder request = put(uri).content(inputJson).
				contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		List<Project> lstResultProject = mapper.readValue(resultJson, new TypeReference<List<Project>>(){});
		boolean lstSucccess = false;
		Project resultProject=null;
		for(Project actualProject: lstResultProject) {
			if(lstProjects.contains(editProject)) {
				lstSucccess = true;
				resultProject = lstProjects.get(lstProjects.indexOf(editProject));
				break;
			}
		}
		if(!editProject.equals(resultProject)) {
			lstSucccess = false;
		}
		assertTrue("Project Modification failed", lstSucccess);
	}


	@Test
	@junitparams.Parameters(source= TestDataProject.class, method = "provideDelProjects")
	public void testDeleteProject(long delProjectId) throws Exception{
		Project deleteProject = new Project();
		deleteProject.setProjectId(delProjectId);
		//lstProjects.add(editProject);
		lstProjects.remove(deleteProject);
		BDDMockito.given(projectService.deleteProject(deleteProject.getProjectId())).willReturn(lstProjects);
		ObjectMapper mapper = new ObjectMapper();
		//String inputJson = mapper.writeValueAsString(editProject);
		String uri = "/projects?projectId="+deleteProject.getProjectId();
		RequestBuilder request = delete(uri).
				contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		List<Project> lstResultProject = mapper.readValue(resultJson, new TypeReference<List<Project>>(){});
		boolean lstSucccess = true;
		if(lstResultProject.contains(deleteProject)) {
			lstSucccess = false;
		}
		assertTrue("Project delete failed", lstSucccess);
	}


	@Test
	@junitparams.Parameters(source= TestDataProject.class, method = "provideProjectsForSort")
	public void testSortAllProjects(List<Project> expectedLstproject,int sortType) throws Exception{
		BDDMockito.given(projectService.sortProjects(sortType)).willReturn(lstProjects);

		MvcResult result = mvc.perform(get("/projects?sorttype="+sortType)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Project> lstResultProject = mapper.readValue(resultJson, new TypeReference<List<Project>>(){});
		boolean lstSucccess = true;
		for(Project actualProject: lstResultProject) {
			if(!expectedLstproject.contains(actualProject)) {
				lstSucccess = false;
				break;
			}
		}
		assertTrue("Project Sorting is not correct", lstSucccess);
	}


	@Test
	@junitparams.Parameters(source= TestDataProject.class, method = "provideProjectsForSearch")
	public void testSearchByName(List<Project> expectedLstproject,String projectName) throws Exception{
		BDDMockito.given(projectService.searchProjectByName(projectName)).willReturn(expectedLstproject);

		MvcResult result = mvc.perform(get("/projects?projectName="+projectName)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Project> lstResultProject = mapper.readValue(resultJson, new TypeReference<List<Project>>(){});
		boolean lstSucccess = true;
		if(!expectedLstproject.containsAll(lstResultProject)) {
			lstSucccess = false;
		}
		assertTrue("Project Sorting is not correct", lstSucccess);
	}


}
