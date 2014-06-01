package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class LogoutController....This class helps to log out
 * and redirect to login page
 */
@WebServlet("/logOut")
public class LogOutController extends HttpServlet {

	private static final long serialVersionUID = 7429250758571675333L;

	private static final Logger log = LoggerFactory
			.getLogger(LogOutController.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> supposed to return home page");
		
		// invalidate the session then go to the login page
		req.getSession().invalidate();
		resp.sendRedirect("login");
		
	}
}
