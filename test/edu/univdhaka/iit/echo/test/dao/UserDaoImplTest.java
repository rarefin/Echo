package edu.univdhaka.iit.echo.test.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.univdhaka.iit.echo.dao.UserDao;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.UserAccount;

public class UserDaoImplTest {
	
	private static final int ID = 1; 
	private static final int VERSION = 0;
	private static final String FIRST_NAME = "Rifat";
	private static final String LAST_NAME = "Arefin";
	private static final String EMAIL = "rifat.arefin@hotmail.com";
	private static final String USER_NAME = "robin";
	private static final String PASSWORD = "password";

	private UserDao userDao;

	@Before
	public void setUp() {
		userDao = new UserDaoImpl();
	}

	@Test
	public void testInsertUser() {
		UserAccount user = new UserAccount();

		user.setVersion(VERSION);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setEmailAddress(EMAIL);
		user.setUserName(USER_NAME);
		user.setPassword(PASSWORD);

		userDao.insertUser(user);
		
		// Check By Asserting
		UserAccount userInfo = userDao.findByUserName("robin");
		
		Assert.assertNotNull(userInfo);
		//Assert.assertEquals(ID, userInfo.getId());
		Assert.assertEquals(VERSION, userInfo.getVersion());
		Assert.assertEquals(FIRST_NAME, userInfo.getFirstName());
		Assert.assertEquals(LAST_NAME, userInfo.getLastName());
		Assert.assertEquals(EMAIL, userInfo.getEmailAddress());
		Assert.assertEquals(USER_NAME, userInfo.getUserName());
		Assert.assertEquals(PASSWORD, userInfo.getPassword());
	
	}
	
	@Test
	public void testDeleteUser() {
		userDao.deleteUser(USER_NAME);
		
		// Check By Asserting
		UserAccount userInfo = userDao.findByUserName(USER_NAME);
				
		Assert.assertNull(userInfo);
	}

	@Test
	public void testUpdateUser() {
		UserAccount user = new UserAccount();

		user.setVersion(VERSION);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setEmailAddress(EMAIL);
		user.setUserName(USER_NAME);
		user.setPassword(PASSWORD);

		userDao.updateUser(user, USER_NAME);
		
		//Check By Asserting
		UserAccount userInfo = userDao.findByUserName("robin");
		
		Assert.assertNotNull(userInfo);
		Assert.assertEquals(ID, userInfo.getId());
		Assert.assertEquals(VERSION, userInfo.getVersion());
		Assert.assertEquals(FIRST_NAME, userInfo.getFirstName());
		Assert.assertEquals(LAST_NAME, userInfo.getLastName());
		Assert.assertEquals(EMAIL, userInfo.getEmailAddress());
		Assert.assertEquals(USER_NAME, userInfo.getUserName());
		Assert.assertEquals(PASSWORD, userInfo.getPassword());
	}

	@Test
	public void testFindByUserName() {
		UserAccount userInfo = userDao.findByUserName(USER_NAME);
		
		Assert.assertNotNull(userInfo);
		Assert.assertEquals(ID, userInfo.getId());
		Assert.assertEquals(VERSION, userInfo.getVersion());
		Assert.assertEquals(FIRST_NAME, userInfo.getFirstName());
		Assert.assertEquals(LAST_NAME, userInfo.getLastName());
		Assert.assertEquals(EMAIL, userInfo.getEmailAddress());
		Assert.assertEquals(USER_NAME, userInfo.getUserName());
		Assert.assertEquals(PASSWORD, userInfo.getPassword());
	}

	@Test
	public void testFindByEmail() {
		UserAccount userInfo = userDao.findByEmail(EMAIL);
		
		Assert.assertNotNull(userInfo);
		Assert.assertEquals(ID, userInfo.getId());
		Assert.assertEquals(VERSION, userInfo.getVersion());
		Assert.assertEquals(FIRST_NAME, userInfo.getFirstName());
		Assert.assertEquals(LAST_NAME, userInfo.getLastName());
		Assert.assertEquals(EMAIL, userInfo.getEmailAddress());
		Assert.assertEquals(USER_NAME, userInfo.getUserName());
		Assert.assertEquals(PASSWORD, userInfo.getPassword());
	}

	/*@Test
	public void testgetAllUserInfo() {
		userDao.getAllUserInfo();
	}*/
}