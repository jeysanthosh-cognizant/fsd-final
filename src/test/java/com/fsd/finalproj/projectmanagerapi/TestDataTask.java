package com.fsd.finalproj.projectmanagerapi;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;

import com.fsd.finalproj.projectmanagerapi.pojo.ParentTask;
import com.fsd.finalproj.projectmanagerapi.pojo.Task;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;


public class TestDataTask {
	
	public static Object[] provideTasks() {
    	Task task1 = new Task();
    	task1.setEndDate(new Date(System.currentTimeMillis()));
    	task1.setPriority(40);
    	task1.setTaskId(111);
    	task1.setStartDate(new Date(System.currentTimeMillis()));
    	task1.setStatus("Completed");
    	Task task2 = new Task();
    	task2.setEndDate(new Date(System.currentTimeMillis()));
    	task2.setPriority(90);
    	task2.setTaskId(222);
    	task2.setStartDate(new Date(System.currentTimeMillis()));
    	task2.setStatus("Inprogress");

		List<Task> taskList = new ArrayList<>();
		taskList.add(task1);
		taskList.add(task2);
		return (new Object[]{
				taskList
		}
				);
	}
	
	public static Object[] provideAddTasks() {
	   	Task task1 = new Task();
    	task1.setEndDate(new Date(System.currentTimeMillis()));
    	task1.setPriority(40);
    	task1.setTaskId(111);
    	task1.setStartDate(new Date(System.currentTimeMillis()));
    	task1.setStatus("Completed");
    	task1.setTask("Task1");
		Users user = new Users();
		task1.setUser(user);
		task1.setParentTask(null);
    	Task task2 = new Task();
    	task2.setEndDate(new Date(System.currentTimeMillis()));
    	task2.setPriority(90);
    	task2.setTaskId(222);
    	task2.setStartDate(new Date(System.currentTimeMillis()));
    	task2.setStatus("Inprogress");
		task2.setTask("Task2");
		task2.setUser(user);
		ParentTask pt = new ParentTask();
		pt.setParentTask(null);
		task1.setParentTask(pt);
		return (new Object[]{
				task1,task2
		}
				);
	}
	
	
	public static Object[] provideDelTasks() {
		

		return (new Object[]{
				111,222
		}
				);
	}

	
	public static Object[] provideEditTasks() {
	   	Task task1 = new Task();
    	task1.setEndDate(new Date(System.currentTimeMillis()));
    	task1.setPriority(40);
    	task1.setTaskId(111);
    	task1.setStartDate(new Date(System.currentTimeMillis()));
    	task1.setTask("COMS");
    	task1.setStatus("Completed");
    	Task task2 = new Task();
    	task2.setEndDate(new Date(System.currentTimeMillis()));
    	task2.setPriority(90);
    	task2.setTask("TIM");
    	task2.setTaskId(222);
    	task2.setStartDate(new Date(System.currentTimeMillis()));
    	task2.setStatus("Inprogress");

		return (new Object[][]{
				{task1},{task2}
		}
				);
	}
	
	
	public static Object[] provideTasksForSort() {
	   	Task task1 = new Task();
    	task1.setEndDate(new Date(System.currentTimeMillis()));
    	task1.setPriority(40);
    	task1.setTaskId(111);
    	task1.setStartDate(new Date(System.currentTimeMillis()));
    	task1.setStatus("Completed");
    	Task task2 = new Task();
    	task2.setEndDate(new Date(System.currentTimeMillis()));
    	task2.setPriority(90);
    	task2.setTaskId(222);
    	task2.setStartDate(new Date(System.currentTimeMillis()));
    	task2.setStatus("Inprogress");

		List<Task> taskList = new ArrayList<>();
		taskList.add(task1);
		taskList.add(task2);
		return (new Object[][]{
				{taskList,1},{taskList,2},{taskList,3},{taskList,4}
		}
				);
	}

	public static Object[] provideParentTasks() {
		ParentTask task1 = new ParentTask();
		task1.setParentId(1);
		task1.setParentTask("Parent1");
		task1.setTaskSet(new HashSet<>());
		ParentTask task2 = new ParentTask();
		task2.setParentId(1);
		task2.setParentTask("Parent1");
		task2.setTaskSet(new HashSet<>());

		List<ParentTask> taskList = new ArrayList<>();
		taskList.add(task1);
		taskList.add(task2);
		return (new Object[]{
				taskList
		}
		);
	}

	public static Object[] provideAddParentTasks() {
		ParentTask task1 = new ParentTask();
		task1.setParentId(1);
		task1.setParentTask("Parent1");
		task1.setTaskSet(new HashSet<>());
		ParentTask task2 = new ParentTask();
		task2.setParentId(1);
		task2.setParentTask("Parent1");
		task2.setTaskSet(new HashSet<>());

		return (new Object[]{
				task1,task2
		}
		);
	}

	public static Object[] provideSearchByName(){
		Task task1 = new Task();
		task1.setEndDate(new Date(System.currentTimeMillis()));
		task1.setPriority(40);
		task1.setTaskId(111);
		task1.setStartDate(new Date(System.currentTimeMillis()));
		task1.setTask("COMS");
		task1.setStatus("Completed");
		Task task2 = new Task();
		task2.setEndDate(new Date(System.currentTimeMillis()));
		task2.setPriority(90);
		task2.setTask("TIM");
		task2.setTaskId(222);
		task2.setStartDate(new Date(System.currentTimeMillis()));
		task2.setStatus("Inprogress");

		List<Task> taskList = new ArrayList<>();
		taskList.add(task1);
		taskList.add(task2);
		return (new Object[][]{
				{taskList,"COMS"},{taskList,"TIM"}
		}
		);
	}

	public static Object[] provideSearchByProjectId(){
		Task task1 = new Task();
		task1.setEndDate(new Date(System.currentTimeMillis()));
		task1.setPriority(40);
		task1.setTaskId(111);
		task1.setStartDate(new Date(System.currentTimeMillis()));
		task1.setTask("COMS");
		task1.setStatus("Completed");
		Task task2 = new Task();
		task2.setEndDate(new Date(System.currentTimeMillis()));
		task2.setPriority(90);
		task2.setTask("TIM");
		task2.setTaskId(222);
		task2.setStartDate(new Date(System.currentTimeMillis()));
		task2.setStatus("Inprogress");

		List<Task> taskList = new ArrayList<>();
		taskList.add(task1);
		taskList.add(task2);
		return (new Object[][]{
				{taskList,1},{taskList,2}
		}
		);
	}

	public static Object[] provideSearchById(){
		Task task1 = new Task();
		task1.setEndDate(new Date(System.currentTimeMillis()));
		task1.setPriority(40);
		task1.setTaskId(111);
		task1.setStartDate(new Date(System.currentTimeMillis()));
		task1.setTask("COMS");
		task1.setStatus("Completed");
		Task task2 = new Task();
		task2.setEndDate(new Date(System.currentTimeMillis()));
		task2.setPriority(90);
		task2.setTask("TIM");
		task2.setTaskId(222);
		task2.setStartDate(new Date(System.currentTimeMillis()));
		task2.setStatus("Inprogress");

		return (new Object[][]{
				{task1,111},{task2,222}
		}
		);
	}
}
