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
import edu.univdhaka.iit.web.service.MailServices;
import edu.univdhaka.iit.web.service.UserService;
import edu.univdhaka.iit.web.service.UserServiceImpl;

/**
 * Servlet implementation class RegistrationController....This class helps to create a account after verify
 * if the user giving the correct information
 */
@WebServlet("/register")
public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(RegistrationController.class);

	private MailServices mailServices;
	private UserService userService;
	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		log.debug("init() > intialize all the services");
		mailServices = new MailServices();
		userService = new UserServiceImpl();
		userDao = new UserDaoImpl();
	}

	// going to the register page
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> this method supppose to return registration page");

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/register.jsp");

		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doPost()");

		UserAccount user = new UserAccount();

		user.setFirstName(req.getParameter("firstName"));
		user.setLastName(req.getParameter("lastName"));
		user.setEmailAddress(req.getParameter("email"));
		user.setUserName(req.getParameter("userName"));
		user.setPassword(req.getParameter("password"));
		user.setConfirmedPassword(req.getParameter("reTypePassword"));

		log.debug("doPost() -> user={}", user);

		Map<String, String> messages = new HashMap<String, String>();
		
		if (!validateRegistration(user, messages)) {
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			System.out.println(messages.values());
			
			req.setAttribute("error", messages);
			requestDispatcher.forward(req, resp);
		} else {
			
			HttpSession session = req.getSession();
			session.setAttribute("userName", user.getUserName());
			userService.createNewUser(user);
			mailServices.RegistrationEmail(user);
			resp.sendRedirect("login");
		}
	}

	/* 
	 * validate user for registration... if the user give correct information for registration
	*	then return true else return false
	*/
	private boolean validateRegistration(UserAccount registeringUser,
			Map<String, String> messages) {
		
		String accountExists = userService.checkAccount(registeringUser);
		boolean isValid = true;
		
		if (registeringUser.getFirstName().isEmpty()) {
			messages.put("firstname.empty", "First Name is Required");
			isValid = false;
		}
		if (registeringUser.getLastName().isEmpty()) {
			messages.put("lastname.empty", "Last Name is Required");
			isValid = false;
		}
		if (registeringUser.getUserName().isEmpty()) {
			messages.put("username.empty", "User Name is Required");
			isValid = false;
		}
		if (registeringUser.getPassword().isEmpty()) {
			messages.put("password.empty", "Password is Required");
			isValid = false;
		}
		if (registeringUser.getConfirmedPassword().isEmpty()) {
			messages.put("passwordConfirmed.empty", "Please confirm password");
			isValid = false;
		}
		if (registeringUser.getEmailAddress().isEmpty()) {
			messages.put("email.empty", "Email is Required");
			isValid = false;
		} else if (!registeringUser.getEmailAddress().matches(
				"([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			messages.put("email.error", "Invalid email, please try again.");
			isValid = false;
		}
		
		if(isValid) {
			if(accountExists.equals("withUserName")) {
				messages.put("accountWithUserName.error", "Exists an echo account with this user name");
				isValid = false;
			}
			if(accountExists.equals("withEmail")) {
				messages.put("accountWithEmail.error", "Exists an echo account with this email");
				isValid = false;
			}
		}
		if (isValid) {
			if (registeringUser.getPassword().length()< 5) {
				messages.put("password.short",
						"Password must be atleast 5 characters long");
				isValid = false;
			}
		}
		if (isValid) {
			if (!registeringUser.getPassword().equals(
					registeringUser.getConfirmedPassword())) {
				messages.put("password.mismatched",
						"Password and Confirmed Password Mismatched");
				isValid = false;
			}
		}
		
		return isValid;
	}

}
