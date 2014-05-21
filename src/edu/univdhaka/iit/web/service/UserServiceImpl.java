package edu.univdhaka.iit.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.dao.UserDao;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.UserAccount;

public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

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

	@Override
	public boolean checkAccount(UserAccount user) {
		log.debug("checkAccount()-> user={}", user);

		UserAccount checkExistence1 = userDao.findByUserName(user.getUserName());
		UserAccount checkExistence2 = userDao.findByEmail(user.getEmailAddress());
		
		if (checkExistence1  == null && checkExistence2  == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean checkOldPassword(UserAccount user, String oldPassword) {
		log.debug("checkOldPassword()-> check if the old password is correct or not");

		UserAccount verifiedUser = userDao.findByUserName(user.getUserName());
		
		if (oldPassword.equals(verifiedUser.getPassword())) {
			return true;
		}

		return false;
	}

}
