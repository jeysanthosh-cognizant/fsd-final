package com.fsd.finalproj.projectmanagerapi;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.fsd.finalproj.projectmanagerapi.dao.UsersDao;
import com.fsd.finalproj.projectmanagerapi.pojo.Users;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@SuppressWarnings("PMD")
public class UserServiceTest extends AbstractServiceTest{
	
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();



List<Users> lstUsers= new ArrayList<>();

	public UserServiceTest() {
		
	}
	
	/*public UserServiceTest(String jm) {
	//	this();
	}*/
    
    @Before
	public void setUp() {
		Users user1 = new Users();
    	user1.setUserId(1);
    	user1.setFirstName("Jey1");
    	user1.setLastName("Santhosh1");
    	user1.setEmployeeId(2754691);
    	lstUsers.add(user1);
		Users user2 = new Users();
    	user2.setUserId(2);
    	user2.setFirstName("Jey2");
    	user2.setLastName("Santhosh2");
    	user2.setEmployeeId(2754692);
    	lstUsers.add(user2);
    }
	
	@Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideAddUsers")
	public void testAddUser(Users adduser) {
		Mockito.when(usersDao.save(adduser))
	      .thenReturn(adduser);
		lstUsers.add(adduser);
		Mockito.when(usersDao.findAll())
	      .thenReturn(lstUsers);
		Mockito.when(usersService.addUser(adduser))
				.thenReturn(lstUsers);
		lstUsers = usersService.addUser(adduser);
		boolean lstSucccess = true;
		if(!lstUsers.contains(adduser)) {
			lstSucccess = false;
		}
		assertTrue("User add Failed", lstSucccess);

	}
	
	@Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideDelUsers")
	public void testDeleteUser(long userId) {
		Mockito.when(usersDao.findAll())
	      .thenReturn(lstUsers);
		Mockito.when(usersService.deleteUser(userId))
				.thenReturn(lstUsers);
		lstUsers = usersService.deleteUser(userId);
		/*ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		Mockito.verify(usersDao).delete( valueCapture.capture());*/
		//long argUserId = valueCapture.getValue();
		Users delUser = new Users();
		delUser.setUserId(userId);
		boolean avail = lstUsers.remove(delUser);
		boolean lstSucccess = false;
		if(avail && !lstUsers.contains(delUser)) {
			lstSucccess = true;
		}
		assertTrue("User del Failed", lstSucccess);

	}
	
	@Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideEditUsers")
	public void testEditUser(Users edituser,String expectedEditUserName) {
		Mockito.when(usersDao.save(edituser))
	      .thenReturn(edituser);
		lstUsers.add(edituser);
		Mockito.when(usersDao.findAll())
	      .thenReturn(lstUsers);
		Mockito.when(usersService.editUser( edituser))
				.thenReturn(lstUsers);
		lstUsers = usersService.editUser( edituser);
		boolean lstSucccess = true;
		String actulEditUserName= null;
		if(!lstUsers.contains(edituser)) {
			actulEditUserName = lstUsers.get(lstUsers.indexOf(edituser)).getFirstName();
			lstSucccess = false;
		}
		assertTrue("User add Failed", lstSucccess);
		assertFalse(expectedEditUserName.equals(actulEditUserName));

	}


	@Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideUsers")
	public void testviewUsers(List<Users> expectedLstUser) {
		Mockito.when(usersDao.findAll())
	      .thenReturn(lstUsers);
		lstUsers = usersService.viewUsers();
		boolean lstSucccess = true;
		if(!expectedLstUser.containsAll(lstUsers)) {
			lstSucccess = false;
		}
		assertTrue("User Viewing failed", lstSucccess);
	}
	
	@Test
    @junitparams.Parameters(source= TestDataUser.class, method = "provideUsersForSort")
	public void testSortUsers(List<Users> expectedLstUser,int sortType) {
		Mockito.when(usersDao.findAllByOrderByFirstNameAsc())
	      .thenReturn(lstUsers);
		Mockito.when(usersDao.findAllByOrderByLastNameAsc())
	      .thenReturn(lstUsers);
		Mockito.when(usersDao.findAllByOrderByEmployeeIdAsc())
	      .thenReturn(lstUsers);
		lstUsers = usersService.sortUsers(sortType);
		boolean lstSucccess = true;
		if(!expectedLstUser.containsAll(lstUsers)) {
			lstSucccess = false;
		}
		assertTrue("User Sorting failed", lstSucccess);
	}
	
	@Test
	 @junitparams.Parameters(source= TestDataUser.class, method = "provideSearchByName")
	    public void testSearchUserByName(List<Users> expectedUserLst,String searchUserName) {
		Mockito.when(usersDao.findByLastNameContainingIgnoreCase(searchUserName))
	      .thenReturn(expectedUserLst);
		Mockito.when(usersService.searchUserByName(searchUserName))
				.thenReturn(expectedUserLst);
		lstUsers = usersService.searchUserByName(searchUserName);
		boolean lstSucccess = true;
		if(!lstUsers.containsAll(expectedUserLst)) {
			lstSucccess = false;
		}
		assertTrue("User Search failed", lstSucccess);
	}
	

}
