package com.fsd.finalproj.projectmanagerapi;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;

import com.fsd.finalproj.projectmanagerapi.pojo.ParentTask;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import javafx.scene.Parent;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import junitparams.JUnitParamsRunner;

public class TaskServiceTest extends AbstractServiceTest {
	
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();


	List<Task> lstTasks= new ArrayList<>();
	List<ParentTask> lstParentTasks= new ArrayList<>();
    
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

		ParentTask ptask1 = new ParentTask();
		ptask1.setParentId(1);
		ptask1.setParentTask("Parent1");
		ptask1.setTaskSet(new HashSet<>());
		ParentTask ptask2 = new ParentTask();
		ptask2.setParentId(1);
		ptask2.setParentTask("Parent1");
		ptask2.setTaskSet(new HashSet<>());

		List<ParentTask> taskList = new ArrayList<>();
		lstParentTasks.add(ptask1);
		lstParentTasks.add(ptask2);
    }
	
	@Test
    @junitparams.Parameters(source= TestDataTask.class, method = "provideAddTasks")
	public void testAddTask(Task addtask) {
		Mockito.when(taskDao.save(addtask))
	      .thenReturn(addtask);
		lstTasks.add(addtask);
		Mockito.when(taskDao.findAll())
	      .thenReturn(lstTasks);
		addtask.equals(null);
		addtask.equals(new ParentTask());
		lstTasks = taskService.addTask(addtask);
		boolean lstSucccess = true;
		if(!lstTasks.contains(addtask)) {
			lstSucccess = false;
		}
		assertTrue("Task add Failed", lstSucccess);

	}
	
	@Test
    @junitparams.Parameters(source= TestDataTask.class, method = "provideDelTasks")
	public void testDeleteTask(long taskId) {
		Mockito.when(taskDao.findAll())
	      .thenReturn(lstTasks);
		lstTasks = taskService.deleteTask(taskId);
		ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		Mockito.verify(taskDao).delete( valueCapture.capture());
		long argTaskId = valueCapture.getValue();
		Task delTask = new Task();
		delTask.setTaskId(argTaskId);
		boolean avail = lstTasks.remove(delTask);
		boolean lstSucccess = false;
		if(avail && !lstTasks.contains(delTask)) {
			lstSucccess = true;
		}
		assertTrue("Task del Failed", lstSucccess);

	}
	
	@Test
    @junitparams.Parameters(source= TestDataTask.class, method = "provideEditTasks")
	public void testEditTask(Task edittask) {
		Mockito.when(taskDao.save(edittask))
	      .thenReturn(edittask);
		lstTasks.add(edittask);
		Mockito.when(taskDao.findAll())
	      .thenReturn(lstTasks);
		lstTasks = taskService.editTask(edittask);
		boolean lstSucccess = true;
		String actulEditTaskName= null;
		if(!lstTasks.contains(edittask)) {
			actulEditTaskName = lstTasks.get(lstTasks.indexOf(edittask)).getTask();
			lstSucccess = false;
		}
		assertTrue("Task add Failed", lstSucccess);
		assertFalse(edittask.getTask().equals(actulEditTaskName));

	}


	@Test
    @junitparams.Parameters(source= TestDataTask.class, method = "provideTasks")
	public void testviewTasks(List<Task> expectedLstTask) {
		Mockito.when(taskDao.findAll())
	      .thenReturn(lstTasks);
		lstTasks = taskService.viewTasks();
		boolean lstSucccess = true;
		if(!expectedLstTask.containsAll(lstTasks)) {
			lstSucccess = false;
		}
		assertTrue("Task Viewing failed", lstSucccess);
	}
	
	@Test
    @junitparams.Parameters(source= TestDataTask.class, method = "provideTasksForSort")
	public void testSortTasks(List<Task> expectedLstTask,int sortType) {
		Mockito.when(taskDao.findAllByOrderByStartDateAsc())
	      .thenReturn(lstTasks);
		Mockito.when(taskDao.findAllByOrderByEndDateAsc())
	      .thenReturn(lstTasks);
		Mockito.when(taskDao.findAllByOrderByPriorityAsc())
	      .thenReturn(lstTasks);
		Mockito.when(taskDao.findAllByOrderByStatusAsc())
				.thenReturn(lstTasks);
		lstTasks = taskService.sortTasks(sortType);
		boolean lstSucccess = true;
		if(!expectedLstTask.containsAll(lstTasks)) {
			lstSucccess = false;
		}
		assertTrue("Task Sorting failed", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideParentTasks")
	public void testviewParentTasks(List<Task> expectedLstTask) {
		Mockito.when(parentTaskDao.findAll())
				.thenReturn(lstParentTasks);
		lstParentTasks= parentTaskService.getAllParentTasks();
		boolean lstSucccess = true;
		if(!expectedLstTask.containsAll(lstParentTasks)) {
			lstSucccess = false;
		}
		assertTrue("Task Viewing failed", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideAddParentTasks")
	public void testAddParentTask(ParentTask addtask) {
		Mockito.when(parentTaskDao.save(addtask))
				.thenReturn(addtask);
		lstParentTasks.add(addtask);
		Mockito.when(parentTaskDao.findAll())
				.thenReturn(lstParentTasks);
		taskService.addParentTask(addtask);
		addtask.hashCode();
		addtask.getParentTask();
		addtask.getParentId();
		addtask.getTaskSet();
		addtask.equals(null);
		addtask.equals(addtask);
		addtask.equals(new Task());
		ParentTask pt = new ParentTask();
		pt.setParentId(-1);
		addtask.equals(pt);
		lstParentTasks = parentTaskService.saveTask(addtask);
		boolean lstSucccess = true;
		if(!lstParentTasks.contains(addtask)) {
			lstSucccess = false;
		}
		assertTrue("Task add Failed", lstSucccess);

	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideSearchByName")
	public void testSearchTaskByName(List<Task> expectedTaskLst, String projectName) {
		Mockito.when(taskDao.findByTaskContainingIgnoreCase(projectName))
				.thenReturn(expectedTaskLst);
		/*Mockito.when(usersService.searchUserByName(searchUserName))
				.thenReturn(expectedUserLst);*/
		lstTasks = taskService.searchTaskByName(projectName);
		boolean lstSucccess = true;
		if(!lstTasks.containsAll(expectedTaskLst)) {
			lstSucccess = false;
		}
		assertTrue("Task Search failed", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideSearchByProjectId")
	public void testSearchTaskByProjectId(List<Task> expectedTaskLst, long projectId) {
		Mockito.when(taskDao.findByProject(projectId))
				.thenReturn(expectedTaskLst);
		/*Mockito.when(usersService.searchUserByName(searchUserName))
				.thenReturn(expectedUserLst);*/
		lstTasks = taskService.searchTaskByProjectId(projectId);
		boolean lstSucccess = true;
		if(!lstTasks.containsAll(expectedTaskLst)) {
			lstSucccess = false;
		}
		assertTrue("Task Search failed", lstSucccess);
	}

	@Test
	@junitparams.Parameters(source= TestDataTask.class, method = "provideSearchById")
	public void testSearchTaskById(Task expectedTask, long taskId) {
		Mockito.when(taskDao.findOne(taskId))
				.thenReturn(expectedTask);
		/*Mockito.when(usersService.searchUserByName(searchUserName))
				.thenReturn(expectedUserLst);*/
		Task resultTask = taskService.searchTaskByTaskId(taskId);
		boolean lstSucccess = true;
		if(!expectedTask.equals(resultTask)) {
			lstSucccess = false;
		}
		assertTrue("Task Search failed", lstSucccess);
	}

}
