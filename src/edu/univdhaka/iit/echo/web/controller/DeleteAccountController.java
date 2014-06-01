package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;
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

/**
 * Servlet implementation class deleteAccountController. This class helps delete a Echo account when 
 * an user request to delete his/her account.... doGet() method of any servlet class get data
 * from the jsp page and doPost() method give information to the web page
 */
@WebServlet("/deleteAccount")
public class DeleteAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = LoggerFactory
			.getLogger(DeleteAccountController.class);
	

	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		log.debug("init() > intialize all the services");
		userDao = new UserDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doPost()");
		HttpSession session = req.getSession();
		String userName = ((String) session.getAttribute("userName"));
		
        String action = req.getParameter("action");
        System.out.println("input: "+req.getParameter("action"));
        

        if (action.equals("deleteAccount")){
        	userDao.deleteUser(userName);
    		resp.sendRedirect("login");
        }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
	}
}
