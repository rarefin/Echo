package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.dao.EchoDao;
import edu.univdhaka.iit.echo.dao.EchoDaoImpl;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.Echo;
import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.web.service.UserServiceImpl;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 7429250758571675333L;

	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	private EchoDao echoDao;

	@Override
	public void init() throws ServletException {
		log.debug("init() > intialize all the services");
		echoDao = new EchoDaoImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> supposed to return home page");

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doPost()");

		UserAccount user = new UserAccount();
		Echo echo = new Echo();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEEE, d-MMM-yyyy 'at' hh:mm:ss a zz");

		Date time = new Date();
		String dateString = formatter.format(time);

		HttpSession session = req.getSession();
		
		user.setUserName((String) session.getAttribute("userName"));

		echo.setEcho(req.getParameter("echo"));
		/*echo.setCreatedDate(java.sql.Date.valueOf(dateString));
		echo.setLastModifiedDate(java.sql.Date.valueOf(dateString));
		echo.setTimeStamp(java.sql.Date.valueOf(dateString));
		echo.setGeoTimeStamp(java.sql.Date.valueOf(dateString));*/
		
		log.debug("doPost() -> user={}", user);

		echoDao.insertEcho(echo, user);
		resp.sendRedirect("home");

	}
}
