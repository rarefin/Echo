package edu.univdhaka.iit.echo.dao;

import java.util.List;

import edu.univdhaka.iit.echo.domain.UserAccount;

//This is the interface that contains the method to manipulate the 'user' table in database
public interface UserDao {

	public void insertUser(UserAccount user);

	public List<UserAccount> getAllUser();

	public void deleteUser(String userName);

	public void updateUser(UserAccount user, String userName);

	public UserAccount findByUserName(String userName);

	public UserAccount findByEmail(String emailAdress);


}
