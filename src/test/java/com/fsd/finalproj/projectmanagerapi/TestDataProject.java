package com.fsd.finalproj.projectmanagerapi;

import com.fsd.finalproj.projectmanagerapi.pojo.Project;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("PMD")
public class TestDataProject {
	
	public static Object[] provideProjects() {
    	Project project1 = new Project();
    	project1.setEndDate(new Date(System.currentTimeMillis()));
    	project1.setPriority(40);
    	project1.setProjectId(111);
    	project1.setStartDate(new Date(System.currentTimeMillis()));
    	//project1.setStatus("Completed");
    	Project project2 = new Project();
    	project2.setEndDate(new Date(System.currentTimeMillis()));
    	project2.setPriority(90);
    	project2.setProjectId(222);
    	project2.setStartDate(new Date(System.currentTimeMillis()));
    	//project2.setStatus("Inprogress");

		List<Project> projectList = new ArrayList<>();
		projectList.add(project1);
		projectList.add(project2);
		return (new Object[]{
				projectList
		}
				);
	}
	
	public static Object[] provideAddProjects() {
		Users user = new Users();
	   	Project project1 = new Project();
    	project1.setEndDate(new Date(System.currentTimeMillis()));
    	project1.setPriority(40);
    	project1.setProjectId(111);
    	project1.setStartDate(new Date(System.currentTimeMillis()));
    	project1.setUser(user);
    	//project1.setStatus("Completed");
    	Project project2 = new Project();
    	project2.setEndDate(new Date(System.currentTimeMillis()));
    	project2.setPriority(90);
    	project2.setProjectId(222);
    	project2.setStartDate(new Date(System.currentTimeMillis()));
    	project2.setUser(user);
    	//project2.setStatus("Inprogress");

		return (new Object[]{
				project1,project2
		}
				);
	}
	
	
	public static Object[] provideDelProjects() {
		

		return (new Object[]{
				111,222
		}
				);
	}

	
	public static Object[] provideEditProjects() {
		Users user = new Users();
	   	Project project1 = new Project();
    	project1.setEndDate(new Date(System.currentTimeMillis()));
    	project1.setPriority(40);
    	project1.setProjectId(111);
    	project1.setStartDate(new Date(System.currentTimeMillis()));
    	project1.setProject("COMS");
    	project1.setUser(user);
    	//project1.setStatus("Completed");
    	Project project2 = new Project();
    	project2.setEndDate(new Date(System.currentTimeMillis()));
    	project2.setPriority(90);
    	project2.setProject("TIM");
    	project2.setProjectId(222);
    	project2.setStartDate(new Date(System.currentTimeMillis()));
    	//project2.setStatus("Inprogress");
       	project2.setUser(user);
		return (new Object[][]{
				{project1,project1.getProject()},{project2,project2.getProject()}
		}
				);
	}
	
	
	public static Object[] provideProjectsForSort() {
	   	Project project1 = new Project();
    	project1.setEndDate(new Date(System.currentTimeMillis()));
    	project1.setPriority(40);
    	project1.setProjectId(111);
    	project1.setStartDate(new Date(System.currentTimeMillis()));
    	//project1.setStatus("Completed");
    	Project project2 = new Project();
    	project2.setEndDate(new Date(System.currentTimeMillis()));
    	project2.setPriority(90);
    	project2.setProjectId(222);
    	project2.setStartDate(new Date(System.currentTimeMillis()));
    	//project2.setStatus("Inprogress");

		List<Project> projectList = new ArrayList<>();
		projectList.add(project1);
		projectList.add(project2);
		return (new Object[][]{
				{projectList,1},{projectList,2},{projectList,3},{projectList,4},{projectList,5}
		}
				);
	}

	
	public static Object[] provideProjectsForSearch() {
	   	Project project1 = new Project();
    	project1.setEndDate(new Date(System.currentTimeMillis()));
    	project1.setPriority(40);
    	project1.setProjectId(111);
    	project1.setStartDate(new Date(System.currentTimeMillis()));
    	List<Project> projectList = new ArrayList<>();
		projectList.add(project1);
    	//project1.setStatus("Completed");
    	Project project2 = new Project();
    	project2.setEndDate(new Date(System.currentTimeMillis()));
    	project2.setPriority(90);
    	project2.setProjectId(222);
    	project2.setStartDate(new Date(System.currentTimeMillis()));
    	//project2.setStatus("Inprogress");

		List<Project> projectList1 = new ArrayList<>();
		projectList1.add(project2);
		return (new Object[][]{
				{projectList,project1.getProject()},{projectList,project2.getProject()}
		}
				);
	}

}
