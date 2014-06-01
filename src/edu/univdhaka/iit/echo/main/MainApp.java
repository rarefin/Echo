package edu.univdhaka.iit.echo.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import edu.univdhaka.iit.echo.dao.DatabaseConnector;
import edu.univdhaka.iit.echo.dao.UserDaoImpl;
import edu.univdhaka.iit.echo.domain.UserAccount;
import edu.univdhaka.iit.echo.repository.EchoFactory;
import edu.univdhaka.iit.echo.service.EchoService;
import edu.univdhaka.iit.echo.service.EchoServiceImpl;
import edu.univdhaka.iit.echo.service.UserService;
import edu.univdhaka.iit.echo.service.UserServiceImpl;

public class MainApp {

	public static void main(String[] args) {

		
		DatabaseConnector connector = new DatabaseConnector();

		Connection connection = null;
		Statement stmt = null;

		try {

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			connector.getConnection();
			System.out.println("Connected database successfully...");

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					connection.close();
			} catch (SQLException se) {

			}// do nothing
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		UserAccount user = new UserAccount();
		UserService userService = new UserServiceImpl();
		EchoService echoService = new EchoServiceImpl();
		UserDaoImpl dat = new UserDaoImpl();

		//user.setId(1);
		user.setVersion(1253);
		user.setFirstName("Rifat");
		user.setLastName("Arefin");
		user.setEmailAddress("rifat_arefin007@yahoo.com");
		user.setUserName("robin15");
		user.setPassword("password");

		userService.createNewUser(user);

		String[] echos = {
				"An accident occurs in Farmgate, ",
				"LoadSheddig has baecome a major problem here that need to be fixed",
				"Corruption is the constraints of our development.We should be stopped ",
				"Java gives you, the motto, your lifetime. " };

		for (String echo : echos) {
			echoService.postEcho(echo, user);
		}
		
		UserAccount user1 = new UserAccount();

		//user1.setId(10);
		user1.setVersion(1253);
		user1.setFirstName("Rifat");
		user1.setLastName("Arefin");
		user1.setEmailAddress("rifat_arefin007@yahoo.com");
		user1.setUserName("robin15");
		user1.setPassword("password");

		userService.createNewUser(user1);

		String[] echos1 = {
				"An accident occurs in Farmgate, ",
				"LoadSheddig has baecome a major problem here that need to be fixed",
				"Corruption is the constraints of our development.It should be stopped ",
				"Java gives you, the motto, your lifetime. " };

		for (String echo : echos) {
			echoService.postEcho(echo, user);
		}

		dat.insertUser(user);
		dat.insertUser(user1);
		System.out.println(dat.getAllUser());
		

	}

}