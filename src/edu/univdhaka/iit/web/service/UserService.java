package edu.univdhaka.iit.web.service;

import edu.univdhaka.iit.echo.domain.UserAccount;

// This interface contains some method for some user done action
public interface UserService {
	
	public void createNewUser(UserAccount user);
	
	public boolean passwordRecovery(UserAccount user);
	
	public void changePassword(UserAccount user, String useName);
	
	UserAccount verifyUser(UserAccount user);
	
	public String checkAccount(UserAccount user);
	
	boolean checkOldPassword(String userName, String oldPassword);

}
