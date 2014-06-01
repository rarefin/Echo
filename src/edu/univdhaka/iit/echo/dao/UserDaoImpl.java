package edu.univdhaka.iit.echo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.domain.UserAccount;


/**
 * @author robin
 * This class is the implementation of UserDao interface. This class contains several methods. 
 * These methods deal with the 'user' table of database
 *
 */
public class UserDaoImpl implements UserDao {

	private static final Logger log = LoggerFactory
			.getLogger(UserDaoImpl.class);

	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.UserDao#insertUser(edu.univdhaka.iit.echo.domain.UserAccount)
	 * This method helps to insert new user's information in the user table
	 */
	@Override
	public void insertUser(UserAccount user) {
		log.debug("insertUser() -> create user and insert user information in database");
		try {
			String query = "INSERT INTO user (version, firstName, lastName, emailAddress, "
					+ "userName, password)"
					+ "VALUES(?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, user.getVersion());
				preparedStatement.setString(2, user.getFirstName());
				preparedStatement.setString(3, user.getLastName());
				preparedStatement.setString(4, user.getEmailAddress());
				preparedStatement.setString(5, user.getUserName());
				preparedStatement.setString(6, user.getPassword());

				preparedStatement.execute();

			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to prepare statement to insert in user", e);
			}
		} catch (Exception e) {
			log.error("Unable to insert in user", e);
		} finally {
			db.closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.UserDao#getAllUser()
	 * this method helps to get all user from table
	 */
	@Override
	public List<UserAccount> getAllUser() {
		log.debug("getAllUser() > get all Users");

		String query = "SELECT * FROM user";
		List<UserAccount> list = new ArrayList<UserAccount>();
		try {
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					UserAccount user = new UserAccount();
					user.setId(rs.getInt("id"));
					user.setVersion(rs.getInt("version"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setUserName(rs.getString("userName"));
					user.setEmailAddress(rs.getString("emailAddress"));
					list.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to get All user info", e);
			}
		} catch (Exception e) {
			log.error("Unable to select all user from user table", e);
		} finally {
			db.closeConnection();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.UserDao#deleteUser(java.lang.String)
	 * this method helps to delete a user from 'user' table...  this is used when an user deactivate an 
	 *  echo account
	 */
	@Override
	public void deleteUser(String userName) {
		log.debug("deleteUser() > Delete the user from database");
		try {
			String query = "DELETE FROM user WHERE userName = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, userName);
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare info for user delete", e);
			}
		} catch (Exception e) {
			log.error("Unable to delete user from user info", e);
		} finally {
			db.closeConnection();
		}

	}

	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.UserDao#updateUser(edu.univdhaka.iit.echo.domain.UserAccount, java.lang.String)
	 * this method helps to update an information of a user using his/her user name
	 */
	@Override
	public void updateUser(UserAccount user, String userName) {
		log.debug("updateUser() > Update user Information");
		try {
			String query = "UPDATE user SET version = ?,firstName = ?, lastName = ?, "
					+ "userName = ?, password = ?, emailAddress = ? WHERE userName = ?";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, user.getVersion());
				preparedStatement.setString(2, user.getFirstName());
				preparedStatement.setString(3, user.getLastName());
				preparedStatement.setString(4, user.getUserName());
				preparedStatement.setString(5, user.getPassword());
				preparedStatement.setString(6, user.getEmailAddress());
				preparedStatement.setString(7, userName);

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to prepare update info for user table", e);
			}
		} catch (Exception e) {
			log.error("Unable to update user in user table", e);
		} finally {
			db.closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.UserDao#findByUserName(java.lang.String)
	 * this method helps to find a user from table using his/her user name
	 */
	@Override
	public UserAccount findByUserName(String userName) {
		log.debug("findByUserName() > find the User from database using userName");

		UserAccount user = new UserAccount();
		try {
			String query = "SELECT * FROM user WHERE userName = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, userName);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					user.setId(rs.getInt("id"));
					user.setVersion(rs.getInt("version"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setUserName(rs.getString("userName"));
					user.setEmailAddress(rs.getString("emailAddress"));
					user.setPassword(rs.getString("password"));

				}
				if (!flag)
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to get user info using userName", e);
			}
		} catch (Exception e) {
			log.error("Unable to find using userName", e);
		} finally {
			db.closeConnection();
		}
		return user;
	}

	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.UserDao#findByEmail(java.lang.String)
	 * this method helps to find a user from table using his/her email
	 */
	@Override
	public UserAccount findByEmail(String emailAddress) {
		log.debug("findByEmail() > find the User from database using email");
		UserAccount user = new UserAccount();
		try {
			String query = "SELECT * FROM user WHERE emailAddress = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, emailAddress);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					user.setId(rs.getInt("id"));
					user.setVersion(rs.getInt("version"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setUserName(rs.getString("userName"));
					user.setEmailAddress(rs.getString("emailAddress"));
					user.setPassword(rs.getString("password"));
				}
				if (!flag)
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to get user info using email", e);
			}
		} catch (Exception e) {
			log.error("Unable to find using email", e);
		} finally {
			db.closeConnection();
		}
		return user;
	}
}
