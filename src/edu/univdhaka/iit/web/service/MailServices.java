package edu.univdhaka.iit.web.service;

import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.dao.UserDao;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.echo.web.controller.LoginController;

import java.util.*;

public class MailServices {
	private static final Logger log = LoggerFactory.getLogger(MailServices.class);
	
	private String senderEmail = "echo.service@email.com";
	private String senderPassword = "Echo@123";
	private String senderHost = "smtp.mail.com";
	private String senderPort = "465";
	private String recieverEmail;
	private String userName;
	private String forgottenPassword;
	private String emailSubject;
	private String emailBody;
	
	

	private UserDao userDao;

	public MailServices() {
		userDao = new UserDaoImpl();
	}

	public void sendEmail(UserAccount user) {
		log.debug("sendMail() -> suppose to send password recovery mail to user Email");
		
		UserAccount verifiedUser = userDao.findByEmail(user.getEmailAddress());
		recieverEmail = user.getEmailAddress();
		userName = verifiedUser.getUserName();
		forgottenPassword = verifiedUser.getPassword();
		
		emailSubject = "Password Recovery";
		emailBody = "Dear " + verifiedUser.getFullName() + ",\n\n" +
		"You have requested for password recovery\n\n"+
				"User Name: " + userName + "\n"+
				"Password: " + forgottenPassword + "\n";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.user", senderEmail);
		properties.put("mail.smtp.host", senderHost);
		properties.put("mail.smtp.port", senderPort);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		//properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.socketFactory.port", senderPort);
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(properties, auth);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(emailBody);
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					recieverEmail));
			Transport.send(msg);
			log.debug("sendMail() -> Password Recovery message has been sent");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Unable to send password recovery email", e);
		}
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmail, senderPassword);
		}
	}
}