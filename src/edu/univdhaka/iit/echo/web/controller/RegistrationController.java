package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;

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

@WebServlet("/register")
public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = 8659464683550550120L;
	
	private static final Logger log = LoggerFactory
			.getLogger(RegistrationController.class);

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
		user.setGender(req.getParameter("gender"));
		user.setEmailAddress(req.getParameter("email"));
		user.setUserName(req.getParameter("userName"));
		user.setPassword(req.getParameter("password"));

		log.debug("doPost() -> user={}", user);

		boolean hasAccount = userService.checkAccount(user);

		if (hasAccount == false) {
			HttpSession session = req.getSession();
			session.setAttribute("userName", user.getUserName());
			userDao.insertUser(user);
			resp.sendRedirect("home");
		} else {
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
