package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.SuportMethods.Converter;
import edu.univdhaka.iit.echo.dao.EchoDao;
import edu.univdhaka.iit.echo.dao.EchoDaoImpl;
import edu.univdhaka.iit.echo.dao.IssueCategoryDao;
import edu.univdhaka.iit.echo.dao.IssueCategoryDaoImpl;
import edu.univdhaka.iit.echo.dao.PhotoDao;
import edu.univdhaka.iit.echo.dao.PhotoDaoImpl;
import edu.univdhaka.iit.echo.dao.UserDao;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.Echo;
import edu.univdhaka.iit.echo.domain.IssueCategory;
import edu.univdhaka.iit.echo.domain.Photo;
import edu.univdhaka.iit.echo.domain.UserAccount;

/**
 * Servlet implementation class PostEchoController.... this class helps an user to post an echo 
 * getting all posting information and storing all information in the database using Dao
 */
@WebServlet("/postEcho")
@MultipartConfig
public class PostEchoController extends HttpServlet {

	private static final long serialVersionUID = 7429250758571675333L;

	private static final Logger log = LoggerFactory
			.getLogger(PostEchoController.class);

	private UserAccount user;
	private IssueCategory  category;
	private Echo echo;
	private Photo photo;
	private EchoDao echoDao;
	private UserDao userDao;
	private PhotoDao photoDao;
	private IssueCategoryDao issueCategoryDao;

	@Override
	public void init() throws ServletException {
		log.debug("init() > intialize all the services");
		user = new UserAccount();
		echo = new Echo();
		photo = new Photo();
		category = new IssueCategory();
		echoDao = new EchoDaoImpl();
		userDao = new UserDaoImpl();
		photoDao = new PhotoDaoImpl();
		issueCategoryDao = new IssueCategoryDaoImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doGet() -> supposed to return home page");

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/postEcho.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("doPost()");
		log.debug("doPost()-> middle1 of the method");
		
		HttpSession session = req.getSession();
		String userName = ((String) session.getAttribute("userName"));
		
		// Getting value from jsp page for issue category.These value will be inserted in the issue category table when posting echo
		category.setCreatedDate(Converter.getTime());
		if(req.getParameter("preDefinedCategory").equals("other")) {
			category.setTitle(req.getParameter("userDefinedCategory"));
			category.setNew(true);
		}
		else {
			category.setTitle(req.getParameter("preDefinedCategory"));
			category.setNew(false);
		}
		
		
		// Getting value from jsp page for echo.These value will be inserted in the 'echo' table when posting echo
		System.out.println("found by goolgle map: " + req.getParameter("geoLocation"));
		
		echo.setPostedBy(userDao.findByUserName(userName));
		echo.setCreatedDate(Converter.getTime());
		echo.setLatitude(Double.parseDouble(req.getParameter("latitude")));
		echo.setLongitude(Double.parseDouble(req.getParameter("longitude")));
		echo.setAccuracy(Double.parseDouble(req.getParameter("accuracy")));
		echo.setAltitude(Double.parseDouble(req.getParameter("altitude")));
		echo.setSpeed(Double.parseDouble(req.getParameter("speed")));
		echo.setGeoLocation(req.getParameter("geoLocation"));
		echo.setGeoTimeStamp(req.getParameter("geoTimeStamp"));
		echo.setEcho(req.getParameter("text"));
		echo.setIssueCategory(category.getTitle());
		
		String anonymous = req.getParameter("anonymous");
		if (anonymous != null && !anonymous.isEmpty()
				&& anonymous.contains("on")) {
			echo.setAnonymous(true);
		}
		else {
			echo.setAnonymous(false);
		}
		
		// Getting value from jsp page for photo.These value will be inserted in the 'photo' table when posting echo

		Part photoPart = req.getPart("photo");
		InputStream inputStream = null;
		
		if (photoPart != null) {
			// prints out some information for debugging
			System.out.println("Photo name: " + photoPart.getName());
			System.out.println("Photo Size: " + photoPart.getSize());
			System.out.println("Contetnt Type" + photoPart.getContentType());

			inputStream = photoPart.getInputStream();
		}
		
		photo.setPostedBy(userDao.findByUserName(userName));
		
		if (inputStream != null) {
			
			photo.setContentType(photoPart.getContentType());
			photo.setOriginal(Converter.byteArrayFromInputStream(inputStream));
			
		}
		
		
		// insert issue category in issue category table
		int issueCategoryId = 0;
		issueCategoryId = issueCategoryDao.insert(category, userDao.findByUserName(userName).getId());
		System.out.println("Issue Category key: " + issueCategoryId);
		
		// insert Echo in the echo table
		int echoId = 0;
		if(issueCategoryId == -1 || issueCategoryId == 0) {
			System.out.println("Unable to insert issue category in the table");
		}
		else {
			echoId = echoDao.insertEcho(echo, issueCategoryId);
			System.out.println("Echo key: " + echoId);
		}
		
		// insert Photo in the photo table
		if(echoId == -1 || echoId == 0) {
			System.out.println("Unable to insert echo in the table");
		}
		else {
			photoDao.insertPhoto(photo, echoId);
		}

		log.debug("doPost() -> user={}", user);
		log.debug("doPost() -> issueCategory={}", category);
		log.debug("doPost() -> echo={}", echo);
		log.debug("doPost() -> photo={}", photo);
		
		resp.sendRedirect("myEchos");

	}
}
