package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.dao.UserDao;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.web.service.UserService;
import edu.univdhaka.iit.web.service.UserServiceImpl;

/**
 * Servlet implementation class LoginController....This class will help receive information
 * from jsp page and then will verify and finally will produce a result if the user can login or not
 */

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -6503843778975343578L;

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	private UserService userService;
	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		log.debug("init() > intialize all the services");
		userService = new UserServiceImpl();
		userDao = new UserDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> this method supppose to return login page");

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doPost()");

		// getting user information for login from page
		UserAccount user = new UserAccount();
		user.setUserName(req.getParameter("userName"));
		user.setPassword(req.getParameter("password"));

		log.debug("doPost() -> user={}", user);
		
		UserAccount authenticatedUser = userService.verifyUser(user);

		Map<String, String> messages = new HashMap<String, String>();
		
		// validate user to login
		if (!validateRegistration(user, messages)) {
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			System.out.println(messages.values());
			
			req.setAttribute("error", messages);
			requestDispatcher.forward(req, resp);
		}
		else {
			HttpSession session = req.getSession();
			if (!session.isNew()) {
			    session.invalidate();
			    session = req.getSession();
			}
			session.setAttribute("userName", user.getUserName());
			resp.sendRedirect("home");
		}
	}
	
	private boolean validateRegistration(UserAccount loginUser,
			Map<String, String> messages) {
		
		boolean isValid = true;
		UserAccount verifiedUser = userDao.findByUserName(loginUser.getUserName());
		
		if (loginUser.getUserName().isEmpty()) {
			messages.put("username.empty", "User Name is Required");
			isValid = false;
		}
		if (loginUser.getPassword().isEmpty()) {
			messages.put("password.empty", "Password is Required");
			isValid = false;
		}
		if(isValid) {
			if(verifiedUser == null) {
				messages.put("onAccount.error", "There is no account with this user name");
				isValid = false;
			}
		}
		if(isValid) {
			if((!verifiedUser.getPassword().equals(loginUser.getPassword()))) {
				messages.put("passwordMissmatch.error", "User name and Password Mismatched");
				isValid = false;
			}
		}
		
		return isValid;
	}

}
