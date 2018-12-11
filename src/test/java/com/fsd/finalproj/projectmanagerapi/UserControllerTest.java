package com.fsd.finalproj.projectmanagerapi;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

//import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import com.fsd.finalproj.projectmanagerapi.ProjectManagerApiApplication;
import com.fsd.finalproj.projectmanagerapi.controller.ProjectManagerController;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import com.fsd.finalproj.projectmanagerapi.service.UsersService;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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


@SuppressWarnings("PMD")
public class UserControllerTest extends AbstractTest {
	
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();


    
	@Autowired
    private MockMvc mvc;
 

    List<Users> lstUsers= new ArrayList<>();
    
    @Before
	public void setUp() {
		Users user1 = new Users();
    	user1.setUserId(1);
    	user1.setFirstName("Jey1");
    	user1.setLastName("Santhosh1");
    	user1.setEmployeeId(2754691);

		Users user2 = new Users();
    	user2.setUserId(2);
    	user2.setFirstName("Jey2");
    	user2.setLastName("Santhosh2");
    	user2.setEmployeeId(2754692);
    	lstUsers.add(user2);
    }
    
    
    @Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideUsers")
    public void testlistAllUsers(List<Users> expectedLstuser) throws Exception{
    	BDDMockito.given(usersService.viewUsers()).willReturn(lstUsers);
    	
      	MvcResult result = mvc.perform(get("/users")
      		      .contentType(MediaType.APPLICATION_JSON))
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	String resultJson = result.getResponse().getContentAsString();
      	ObjectMapper mapper = new ObjectMapper();
      	List<Users> lstResultUser = mapper.readValue(resultJson, new TypeReference<List<Users>>(){});
      	boolean lstSucccess = true;
      	for(Users actualUser: lstResultUser) {
      		if(!expectedLstuser.contains(actualUser)) {
      			lstSucccess = false;
      			break;
      		}
      	}
	    assertTrue("User listing is not correct", lstSucccess);
    }

  @Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideAddUsers")
    public void testAddUser(Users addedUser) throws Exception{
    	lstUsers.add(addedUser);
    	BDDMockito.given(usersService.addUser(addedUser)).willReturn(lstUsers);
    	ObjectMapper mapper = new ObjectMapper();
    	 String inputJson = mapper.writeValueAsString(addedUser);
    	 RequestBuilder request = post("/users").content(inputJson)
			        .param("employeeId", addedUser.getEmployeeId()+"")
			        .param("firstName", addedUser.getFirstName()+"")
			        .param("lastName", addedUser.getLastName()+"")
			        .param("userId", addedUser.getUserId()+"").
			       contentType(MediaType.APPLICATION_JSON);
      	MvcResult result = mvc.perform(request)
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	String resultJson = result.getResponse().getContentAsString();
      	List<Users> lstResultUser = mapper.readValue(resultJson, new TypeReference<List<Users>>(){});
      	boolean lstSucccess = true;
      	for(Users actualUser: lstResultUser) {
      		if(!lstUsers.contains(actualUser)) {
      			lstSucccess = false;
      			break;
      		}
      	}
	    assertTrue("User Addition failed", lstSucccess);
    }


    @Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideEditUsers")
    public void testEditUser(Users editUser,String expectedUserName) throws Exception{
    	//lstUsers.add(editUser);
    	lstUsers.add(editUser);
    	BDDMockito.given(usersService.editUser(editUser)).willReturn(lstUsers);
    	ObjectMapper mapper = new ObjectMapper();
    	 String inputJson = mapper.writeValueAsString(editUser);
    	 String uri = "/users";
    	 RequestBuilder request = put(uri).content(inputJson).
			       contentType(MediaType.APPLICATION_JSON);
      	MvcResult result = mvc.perform(request)
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	String resultJson = result.getResponse().getContentAsString();
      	List<Users> lstResultUser = mapper.readValue(resultJson, new TypeReference<List<Users>>(){});
      	boolean lstSucccess = false;
		Users resultUser=null;
      	for(Users actualUser: lstResultUser) {
      		if(lstUsers.contains(editUser)) {
      			lstSucccess = true;
      			resultUser = lstUsers.get(lstUsers.indexOf(editUser));
      			break;
      		}
      	}
      	if(!editUser.equals(resultUser)) {
      		lstSucccess = false;
      	}
	    assertTrue("User Modification failed", lstSucccess);
    }
    
    
    @Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideAddUsers")
    public void testDeleteUser(Users deleteUser) throws Exception{
    	//lstUsers.add(editUser);
    	lstUsers.remove(deleteUser);
    	BDDMockito.given(usersService.deleteUser(deleteUser.getUserId())).willReturn(lstUsers);
    	ObjectMapper mapper = new ObjectMapper();
    	 //String inputJson = mapper.writeValueAsString(editUser);
    	 String uri = "/users?userId="+deleteUser.getUserId();
    	 RequestBuilder request = delete(uri).
			       contentType(MediaType.APPLICATION_JSON);
      	MvcResult result = mvc.perform(request)
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	String resultJson = result.getResponse().getContentAsString();
      	List<Users> lstResultUser = mapper.readValue(resultJson, new TypeReference<List<Users>>(){});
      	boolean lstSucccess = true;
      	for(Users actualUser: lstResultUser) {
      		if(lstUsers.contains(deleteUser)) {
      			lstSucccess = false;
      			break;
      		}
      	}
      assertTrue("User delete failed", lstSucccess);
    }
    
    
    @Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideUsersForSort")
    public void testSortAllUsers(List<Users> expectedLstuser,int sortType) throws Exception{
    	BDDMockito.given(usersService.sortUsers(sortType)).willReturn(lstUsers);
      	MvcResult result = mvc.perform(get("/users?sortType="+sortType)
      		      .contentType(MediaType.APPLICATION_JSON))
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	String resultJson = result.getResponse().getContentAsString();
      	ObjectMapper mapper = new ObjectMapper();
      	List<Users> lstResultUser = mapper.readValue(resultJson, new TypeReference<List<Users>>(){});
      	boolean lstSucccess = true;
      	for(Users actualUser: lstResultUser) {
      		if(!expectedLstuser.contains(actualUser)) {
      			lstSucccess = false;
      			break;
      		}
      	}
	    assertTrue("User Sorting is not correct", lstSucccess);
    }

    @Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideSearchByName")
    public void testSearchUserByName(List<Users> expectedUserLst,String searchUserName) throws Exception{
    	BDDMockito.given(usersService.searchUserByName(searchUserName)).willReturn(expectedUserLst);
      	MvcResult result = mvc.perform(get("/users?userName="+searchUserName)
      		      .contentType(MediaType.APPLICATION_JSON))
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	String resultJson = result.getResponse().getContentAsString();
      	ObjectMapper mapper = new ObjectMapper();
      	List<Users> lstResultUser = mapper.readValue(resultJson, new TypeReference<List<Users>>(){});
      	boolean lstSucccess = true;
      	for(Users actualUser: lstResultUser) {
      		if(actualUser.getLastName().equals(searchUserName)) {
      			lstSucccess = true;
      			break;
      		}
      	}
	    assertTrue("User Search is not correct", lstSucccess);

    }
    

}
