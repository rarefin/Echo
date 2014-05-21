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

import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.web.service.UserService;
import edu.univdhaka.iit.web.service.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -6503843778975343578L;

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	private UserService userService;

	@Override
	public void init() throws ServletException {
		log.debug("init() > intialize all the services");
		userService = new UserServiceImpl();
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

		UserAccount user = new UserAccount();
		user.setUserName(req.getParameter("userName"));
		user.setPassword(req.getParameter("password"));

		log.debug("doPost() -> user={}", user);
		
		UserAccount authenticatedUser = userService.verifyUser(user);

		if (authenticatedUser != null) {
			HttpSession session = req.getSession();
			if (!session.isNew()) {
			    session.invalidate();
			    session = req.getSession();
			}
			session.setAttribute("userName", user.getUserName());
			resp.sendRedirect("home");
		} else {
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
