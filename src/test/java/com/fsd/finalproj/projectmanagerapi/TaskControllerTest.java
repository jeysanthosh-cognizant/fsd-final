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

import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.service.TaskService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class TaskControllerTest extends AbstractTest{

	@ClassRule
	public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

	/*@Autowired
	private MockMvc mvc;*/

	List<Task> lstTasks= new ArrayList<>();

	@Before
	public void setUp() {
		Task task1 = new Task();
		task1.setEndDate(new Date(System.currentTimeMillis()));
		task1.setPriority(40);
		task1.setTaskId(111);
		task1.setStartDate(new Date(System.currentTimeMillis()));
		task1.setTask("COMS1");
		task1.setStatus("Completed");
		lstTasks.add(task1);
		Task task2 = new Task();
		task2.setEndDate(new Date(System.currentTimeMillis()));
		task2.setPriority(40);
		task2.setTaskId(222);
		task2.setTask("COMS2");
		task2.setStartDate(new Date(System.currentTimeMillis()));
		task2.setStatus("Completed");
		lstTasks.add(task2);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideTasks")
	public void testlistAllTasks(List<Task> expectedLsttask) throws Exception{


		BDDMockito.given(taskService.viewTasks()).willReturn(lstTasks);

		MvcResult result = mvc.perform(get("/tasks")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Task> lstResultTask = mapper.readValue(resultJson, new TypeReference<List<Task>>(){});

		boolean lstSucccess = true;
		for(Task actualTask: lstResultTask) {
			if(!expectedLsttask.contains(actualTask)) {
				lstSucccess = false;
				break;
			}
		}
		assertTrue("Task listing is not correct", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideAddTasks")
	public void testAddTask(Task addedTask) throws Exception{
		lstTasks.add(addedTask);
		BDDMockito.given(taskService.addTask(addedTask)).willReturn(lstTasks);
		ObjectMapper mapper = new ObjectMapper();
		String inputJson = mapper.writeValueAsString(addedTask);
		RequestBuilder request = post("/tasks").content(inputJson)./*
			        .param("employeeId", addedTask.getEmployeeId()+"")
			        .param("firstName", addedTask.getFirstName()+"")
			        .param("lastName", addedTask.getLastName()+"")
			        .param("taskId", addedTask.getTaskId()+"").*/
				contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		List<Task> lstResultTask = mapper.readValue(resultJson, new TypeReference<List<Task>>(){});
		/*System.out.println(resultJson);*/
		boolean lstSucccess = true;
		for(Task actualTask: lstResultTask) {
			if(!lstTasks.contains(actualTask)) {
				lstSucccess = false;
				break;
			}
		}
		assertTrue("Task Addition failed", lstSucccess);
	}


	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideEditTasks")
	public void testEditTask(Task editTask) throws Exception{
		//lstTasks.add(editTask);
		lstTasks.add(editTask);
		BDDMockito.given(taskService.editTask(editTask)).willReturn(lstTasks);
		/*BDDMockito.given(taskDao.findAll()).willReturn(lstTasks);*/
		ObjectMapper mapper = new ObjectMapper();
		String inputJson = mapper.writeValueAsString(editTask);
		String uri = "/tasks";
		RequestBuilder request = put(uri).content(inputJson).
				contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		List<Task> lstResultTask = mapper.readValue(resultJson, new TypeReference<List<Task>>(){});
		boolean lstSucccess = false;
		Task resultTask=null;
		for(Task actualTask: lstResultTask) {
			if(lstTasks.contains(editTask)) {
				lstSucccess = true;
				resultTask = lstTasks.get(lstTasks.indexOf(editTask));
				break;
			}
		}
		if(!editTask.equals(resultTask)) {
			lstSucccess = false;
		}
		assertTrue("Task Modification failed", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideDelTasks")
	public void testDeleteTask(long delTaskId) throws Exception{
		Task deleteTask = new Task();
		deleteTask.setTaskId(delTaskId);
		//lstTasks.add(editTask);
		lstTasks.remove(deleteTask);
		BDDMockito.given(taskService.deleteTask(deleteTask.getTaskId())).willReturn(lstTasks);
		ObjectMapper mapper = new ObjectMapper();
		//String inputJson = mapper.writeValueAsString(editTask);
		String uri = "/tasks?taskId="+deleteTask.getTaskId();
		RequestBuilder request = delete(uri).
				contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		List<Task> lstResultTask = mapper.readValue(resultJson, new TypeReference<List<Task>>(){});

		boolean lstSucccess = true;
		if(lstResultTask.contains(deleteTask)) {
			lstSucccess = false;
		}
		assertTrue("Task delete failed", lstSucccess);
	}


	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideTasksForSort")
	public void testSortAllTasks(List<Task> expectedLsttask,int sortType) throws Exception{


		BDDMockito.given(taskService.sortTasks(sortType)).willReturn(lstTasks);

		MvcResult result = mvc.perform(get("/tasks?sorttype="+sortType)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Task> lstResultTask = mapper.readValue(resultJson, new TypeReference<List<Task>>(){});

		boolean lstSucccess = true;
		for(Task actualTask: lstResultTask) {
			if(!expectedLsttask.contains(actualTask)) {
				lstSucccess = false;
				break;
			}
		}
		assertTrue("Task Sorting is not correct", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideSearchByName")
	public void testSearchTaskByName(List<Task> expectedTaskLst, String projectName) throws Exception{

		BDDMockito.given(taskService.searchTaskByName(projectName)).willReturn(expectedTaskLst);

		MvcResult result = mvc.perform(get("/tasks?taskName="+projectName)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Task> lstResultTask = mapper.readValue(resultJson, new TypeReference<List<Task>>(){});

		boolean lstSucccess = true;
		for(Task actualTask: lstResultTask) {
			if(!expectedTaskLst.contains(actualTask)) {
				lstSucccess = false;
				break;
			}
		}
		assertTrue("Task listing is not correct", lstSucccess);

	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideSearchByProjectId")
	public void testSearchTaskByProjectId(List<Task> expectedTaskLst, long projectId) throws Exception{
		BDDMockito.given(taskService.searchTaskByProjectId(projectId)).willReturn(expectedTaskLst);

		MvcResult result = mvc.perform(get("/tasks?projectId="+projectId)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Task> lstResultTask = mapper.readValue(resultJson, new TypeReference<List<Task>>(){});

		boolean lstSucccess = true;
		for(Task actualTask: lstResultTask) {
			if(!expectedTaskLst.contains(actualTask)) {
				lstSucccess = false;
				break;
			}
		}
		assertTrue("Task listing is not correct", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideSearchById")
	public void testSearchTaskById(Task expectedTask, long taskId) throws Exception {

		BDDMockito.given(taskService.searchTaskByTaskId(taskId)).willReturn(expectedTask);

		MvcResult result = mvc.perform(get("/tasks?taskId="+taskId)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		// .andExpect(jsonPath("$[0].title", is("SpringTest")));
		String resultJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Task> lstResultTask = mapper.readValue(resultJson, new TypeReference<List<Task>>(){});

		boolean lstSucccess = true;
		for(Task actualTask: lstResultTask) {
			if(!expectedTask.equals(actualTask)) {
				lstSucccess = false;
				break;
			}
		}
		assertTrue("Task listing is not correct", lstSucccess);
	}


}
