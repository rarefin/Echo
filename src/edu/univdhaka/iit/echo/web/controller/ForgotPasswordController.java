package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.web.service.MailServices;

@WebServlet("/forgotPassword")
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = -6503843778975343578L;

	private static final Logger log = LoggerFactory.getLogger(ForgotPasswordController.class);
	
	private MailServices mailServices;

	@Override
	public void init() throws ServletException {
		log.debug("init() -> intialize all the services");
		mailServices = new MailServices();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> this method supppose to return password recovery page");

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/forgotPassword.jsp");

		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doPost()");

		UserAccount user = new UserAccount();
		user.setEmailAddress(req.getParameter("email"));

		log.debug("doPost() -> user={}", user);

		mailServices.sendEmail(user);
		resp.sendRedirect("login");
	}
}
