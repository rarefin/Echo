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

import edu.univdhaka.iit.echo.dao.EchoDao;
import edu.univdhaka.iit.echo.dao.EchoDaoImpl;
import edu.univdhaka.iit.echo.dao.IssueCategoryDao;
import edu.univdhaka.iit.echo.dao.IssueCategoryDaoImpl;
import edu.univdhaka.iit.echo.dao.PhotoDao;
import edu.univdhaka.iit.echo.dao.PhotoDaoImpl;

/**
 * Servlet implementation class deleteAccountController....This class helps to
 * delete a posted echo of a user
 */
@WebServlet("/deleteEcho")
public class DeleteEchoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(DeleteEchoController.class);

	private IssueCategoryDao issueCategoryDao;
	private PhotoDao photoDao;
	private EchoDao echoDao;

	@Override
	public void init() throws ServletException {
		log.debug("init() > intialize all the services");

		issueCategoryDao = new IssueCategoryDaoImpl();
		photoDao = new PhotoDaoImpl();
		echoDao = new EchoDaoImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doPost()");
		HttpSession session = req.getSession();
		String userName = ((String) session.getAttribute("userName"));

		int echoId = Integer.parseInt(req.getParameter("deleteEchoId"));
		System.out.println("Delete echo using this id" + echoId);

		// deleting all information from database when deleting an echo
		int isuseCategoryId = echoDao.getIssueCategoryId(echoId);
		issueCategoryDao.delete(isuseCategoryId);
		echoDao.deleteEcho(echoId);
		photoDao.deletePhoto(echoId);
		resp.sendRedirect("myEchos");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}
}
