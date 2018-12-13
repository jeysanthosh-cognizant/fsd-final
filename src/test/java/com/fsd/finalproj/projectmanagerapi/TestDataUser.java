package com.fsd.finalproj.projectmanagerapi;

import com.fsd.finalproj.projectmanagerapi.pojo.Users;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("PMD")
public class TestDataUser {
	
	public static Object[] provideUsers() {
		Users user1 = new Users();
		user1.setUserId(1);
		user1.setFirstName("Jey");
		user1.setLastName("Santhosh");
		user1.setEmployeeId(275469);

		Users user2 = new Users();
		user2.setUserId(2);
		user2.setFirstName("Jey1");
		user2.setLastName("Santhosh1");
		user2.setEmployeeId(2754691);

		List<Users> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		return (new Object[]{
				userList
		}
				);
	}
	
	public static Object[] provideAddUsers() {
		Users user1 = new Users();
		user1.setUserId(1);
		user1.setFirstName("Jey2");
		user1.setLastName("Santhosh2");
		user1.setEmployeeId(2754692);

		Users user2 = new Users();
		user2.setUserId(2);
		user2.setFirstName("Jey3");
		user2.setLastName("Santhosh3");
		user2.setEmployeeId(2754693);

		return (new Object[]{
				user1,user2
		}
				);
	}
	
	
	public static Object[] provideDelUsers() {
		

		return (new Object[]{
				1,2
		}
				);
	}

	
	public static Object[] provideEditUsers() {
		Users user1 = new Users();
		user1.setUserId(1);
		user1.setFirstName("Jey4");
		user1.setLastName("Santhosh4");
		user1.setEmployeeId(2754694);

		Users user2 = new Users();
		user2.setUserId(2);
		user2.setFirstName("Jey5");
		user2.setLastName("Santhosh5");
		user2.setEmployeeId(2754695);

		return (new Object[][]{
				{user1,user1.getFirstName()},{user2,user2.getFirstName()}
		}
				);
	}
	
	
	public static Object[] provideUsersForSort() {
		Users user1 = new Users();
		user1.setUserId(1);
		user1.setFirstName("Jey6");
		user1.setLastName("Santhosh6");
		user1.setEmployeeId(2754696);

		Users user2 = new Users();
		user2.setUserId(2);
		user2.setFirstName("Jey7");
		user2.setLastName("Santhosh7");
		user2.setEmployeeId(2754697);

		List<Users> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		return (new Object[][]{
				{userList,1},{userList,2},{userList,3},{userList,4}
		}
				);
	}

	public static Object[] provideSearchByName() {
		Users user1 = new Users();
		user1.setUserId(1);
		user1.setFirstName("Jey8");
		user1.setLastName("Santhosh8");
		user1.setEmployeeId(2754698);
		List<Users> user1List = new ArrayList<>();
		user1List.add(user1);
		
		Users user2 = new Users();
		user2.setUserId(2);
		user2.setFirstName("Jey9");
		user2.setLastName("Santhosh9");
		user2.setEmployeeId(2754699);
		List<Users> user2List = new ArrayList<>();
		user2List.add(user2);

		return (new Object[][]{
				{user1List,"Santhosh8"},{user2List,"Santhosh9"}
		}
				);
	}


}
