package edu.univdhaka.iit.web.service;

import edu.univdhaka.iit.echo.domain.UserAccount;

public interface UserService {

	UserAccount verifyUser(UserAccount user);
	
	boolean checkAccount(UserAccount user);
	
	boolean checkOldPassword(UserAccount user, String oldPassword);

}
