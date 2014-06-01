package edu.univdhaka.iit.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.dao.UserDao;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.echo.repository.EchoFactory;

/**
 * this is the implementation class for UserService interface
 *
 */
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private UserDao userDao;
	private MailServices mailServices;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
		mailServices = new MailServices();
	}
	
	// this method creates a new user using necessary info
	@Override
	public void createNewUser(UserAccount user) {
		log.debug("createNewUser()-> new user={}", user);

        userDao.insertUser(user);

        log.debug("createNewUser() -> New user created succesfuly");
		
	}
	
	/*
	 *  (non-Javadoc)
	 * @see edu.univdhaka.iit.web.service.UserService#passwordRecovery(edu.univdhaka.iit.echo.domain.UserAccount)
	 * this method provide password recovery option... returns true if recovery email is sent
	 */
	@Override
	public boolean passwordRecovery(UserAccount user) {
		if(mailServices.passwordRecoveryEmail(user) == true) {
			return true;
		}
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see edu.univdhaka.iit.web.service.UserService#changePassword(edu.univdhaka.iit.echo.domain.UserAccount, java.lang.String)
	 * this method helps to change password of a user...if verification is ok then change password and update it in the database
	 */
	@Override
	public void changePassword(UserAccount user, String userName) {
		
		UserAccount user1 = userDao.findByUserName(userName);
		user1.setPassword(user.getPassword());
		user1.setConfirmedPassword(user.getConfirmedPassword());
		userDao.updateUser(user, userName);
		
	}
	
	// This method verify user if he/she can login or not
	@Override
	public UserAccount verifyUser(UserAccount user) {
		log.debug("verifyUser()-> user={}", user);

		UserAccount verifiedUser = userDao.findByUserName(user.getUserName());
		
		if (verifiedUser != null
				&& verifiedUser.getPassword().equals(user.getPassword())) {
			return verifiedUser;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.web.service.UserService#checkAccount(edu.univdhaka.iit.echo.domain.UserAccount)
	 * This method checks whether there exists an account with a user name or an email
	 * if exists then return with what the account exists
	 */
	@Override
	public String checkAccount(UserAccount user) {
		log.debug("checkAccount()-> user={}", user);
		
		String accountExists = "no";

		UserAccount checkExistence1 = userDao.findByUserName(user.getUserName());
		UserAccount checkExistence2 = userDao.findByEmail(user.getEmailAddress());
		
		if (checkExistence1  != null) {
			accountExists = "withUserName";
		}
		if (checkExistence2  != null) {
			accountExists = "withEmail";
		}
		
		return accountExists;
	}

	/*
	 * (non-Javadoc)
	 * @see edu.univdhaka.iit.web.service.UserService#checkOldPassword(java.lang.String, java.lang.String)
	 * this method helps to check if the old password matches with the user previous password
	 * when changing password......if matches then return true
	 */
	@Override
	public boolean checkOldPassword(String userName, String oldPassword) {
		log.debug("checkOldPassword()-> check if the old password is correct or not");

		UserAccount verifiedUser = userDao.findByUserName(userName);
		
		if (oldPassword.equals(verifiedUser.getPassword())) {
			return true;
		}

		return false;
	}
}
