package edu.univdhaka.iit.echo.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.dao.UserDao;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.web.service.UserService;
import edu.univdhaka.iit.web.service.UserServiceImpl;


/**
 * Servlet implementation class forgotPasswordController....
 * this class helps to recover the password using the email services
 */
@WebServlet("/forgotPassword")
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(ForgotPasswordController.class);

	private UserService userService;
	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		log.debug("init() -> intialize all the services");
		userDao = new UserDaoImpl();
		userService = new UserServiceImpl();
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
		Map<String, String> messages = new HashMap<String, String>();

		// if verification is not ok then show error message
		if (!validatePasswordRecovery(user, messages)) {
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/WEB-INF/jsp/forgotPassword.jsp");
			System.out.println(messages.values());
			req.setAttribute("error", messages);
			requestDispatcher.forward(req, resp);
		}

		else {
			if (userService.passwordRecovery(user) == true) {
				RequestDispatcher requestDispatcher = req
						.getRequestDispatcher("/WEB-INF/jsp/forgotPassword.jsp");
				System.out.println(messages.values());
				messages.put("email.sent",
						"Passeord recovery email has been sent.Please check your email.");
				req.setAttribute("error", messages);
				requestDispatcher.forward(req, resp);
			}
		}
	}

	// validate forgot password field
	private boolean validatePasswordRecovery(UserAccount user,
			Map<String, String> messages) {

		UserAccount verifiedUser = userDao.findByEmail(user.getEmailAddress());
		boolean isValid = true;

		if (user.getEmailAddress().isEmpty()) {
			messages.put("email.empty", "Email is Required");
			isValid = false;
		} else if (!user.getEmailAddress().matches(
				"([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			messages.put("invalidEmail.error",
					"Invalid email, please try again.");
			isValid = false;
		}
		if (isValid == true && verifiedUser == null) {
			messages.put("noAccount.empty",
					"There is no account with this email address");
		}

		if (isValid == true && userService.passwordRecovery(user) == false) {
			messages.put("sentEmail.error",
					"Email is not sent.Please try again");
		}

		return isValid;
	}
}
