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

@WebServlet("/myEchos")
public class MyEchoController extends HttpServlet {

	private static final long serialVersionUID = 7429250758571675333L;

	private static final Logger log = LoggerFactory
			.getLogger(MyEchoController.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> supposed to return to user's psted echos's page");
		
		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/myEchos.jsp");
		requestDispatcher.forward(req, resp);
		
	}
}
