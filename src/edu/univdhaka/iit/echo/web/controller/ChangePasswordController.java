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

import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.web.service.UserService;
import edu.univdhaka.iit.web.service.UserServiceImpl;

/**
 * This servlet controls changepassword.jsp for  changing password....first get the information 
 * from page for change..then verify and change it from database using Dao(database access object)
 * 
 */
@WebServlet("/changePassword")
public class ChangePasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(ChangePasswordController.class);

	private UserService userService;

	@Override
	public void init() throws ServletException {
		log.debug("init() > intialize all the services");
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> this method supppose to return password change page");

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/changePassword.jsp");

		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doPost()");

		UserAccount user = new UserAccount();

		HttpSession session = req.getSession();
		String userName = ((String) session.getAttribute("userName"));
		String oldPassword = req.getParameter("oldPassword");
		
		user.setUserName(userName);
		user.setPassword(req.getParameter("newPassword"));
		user.setConfirmedPassword(req.getParameter("reTypePassword"));
		
		System.out.println(user);
		log.debug("doPost() -> user={}", user);

		Map<String, String> messages = new HashMap<String, String>();
		
		if (!validateChangePassword(user, oldPassword, messages)) {
			System.out.println(messages.values());
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/WEB-INF/jsp/changePassword.jsp");
			System.out.println(messages.values());
			
			req.setAttribute("error", messages);
			requestDispatcher.forward(req, resp);
		} else {
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/WEB-INF/jsp/changePassword.jsp");
			userService.changePassword(user, userName);
			messages.put("changePassword.done", "Your password has been changed");
			req.setAttribute("error", messages);
			requestDispatcher.forward(req, resp);
		}
		
	}
	
	private boolean validateChangePassword(UserAccount user, String oldPassword,
			Map<String, String> messages) {
		
		boolean isValid = true;
		
		if (oldPassword.isEmpty()) {
			messages.put("oldPassword.empty", "Old Password is Required");
			isValid = false;
		}
		if (user.getPassword().isEmpty()) {
			messages.put("newPassword.empty", "New Password is Required");
			isValid = false;
		}
		if (user.getConfirmedPassword().isEmpty()) {
			messages.put("confirmPassword.empty", "Please confirm password");
			isValid = false;
		}
		if(isValid) {
			if(!(userService.checkOldPassword(user.getUserName(), oldPassword))) {
				messages.put("passwordMissmatch.error", "Old Password is wrong");
				isValid = false;
			}
		}
		if (isValid) {
			if (user.getPassword().length()< 5) {
				messages.put("password.short",
						"Password must be atleast 5 characters long");
				isValid = false;
			}
		}
		if(isValid) {
			if(!(user.getPassword().equals(user.getConfirmedPassword()))) {
				messages.put("passwordMissmatch.error", "New Password and Confirmed Password Mismatched");
				isValid = false;
			}
		}
		return isValid;
	}
}
