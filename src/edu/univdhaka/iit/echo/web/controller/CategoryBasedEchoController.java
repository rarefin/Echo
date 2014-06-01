package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.dao.EchoDao;
import edu.univdhaka.iit.echo.dao.EchoDaoImpl;
import edu.univdhaka.iit.echo.dao.PhotoDao;
import edu.univdhaka.iit.echo.dao.PhotoDaoImpl;
import edu.univdhaka.iit.echo.domain.Echo;


/**
 * This servlet controls the category based echos...this servlet helps to fetch all echo of a particular
 * category from database using Dao to show it on the web page
 *
 */
@WebServlet("/categoryBasedEcho")
public class CategoryBasedEchoController extends HttpServlet {

	private static final long serialVersionUID = 7429250758571675333L;

	private static final Logger log = LoggerFactory
			.getLogger(CategoryBasedEchoController.class);
	EchoDao echoDao = new EchoDaoImpl();
	PhotoDao photoDao = new PhotoDaoImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> supposed to return to user's psted echos's page");

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/categoryBasedEcho.jsp");
		
		String issueCategory = req.getParameter("issueCategory");
		System.out.println("Issue category: " + issueCategory);
		
		// finding the category based echo from database
		List<Echo> list = echoDao.findEchoByIssueCategory(issueCategory);
		Collections.reverse(list);
		
		// if an user select the Anonymous his user name will be hidden
		for(int i=0; i<list.size(); i++){
			if(list.get(i).isAnonymous()==true) {
				list.get(i).setUserName("Annonymous");
			}
		}
		
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).getEcho());
		}
		// sending the echo of a selected category to web page
		req.setAttribute("categoryBasedEchos", list);

		requestDispatcher.forward(req, resp);
		
		log.debug("doGet() -> category based echo shown on web page");

	}
}
