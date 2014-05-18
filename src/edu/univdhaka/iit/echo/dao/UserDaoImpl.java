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

public class UserDaoImpl implements UserDao {

	private static final Logger log = LoggerFactory
			.getLogger(UserDaoImpl.class);

	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();

	@Override
	public void insertUser(UserAccount user) {
		log.debug("insertUser > create user and insert information in database");
		try {
			String query = "INSERT INTO user (version, firstName, lastName, gender, emailAddress, "
					+ "userName, password, accountNonExpired, accountNonLocked, enabled, credentialsNonExpired)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, user.getVersion());
				preparedStatement.setString(2, user.getFirstName());
				preparedStatement.setString(3, user.getLastName());
				preparedStatement.setString(4, user.getGender());
				preparedStatement.setString(5, user.getEmailAddress());
				preparedStatement.setString(6, user.getUserName());
				preparedStatement.setString(7, user.getPassword());
				preparedStatement.setBoolean(8, user.isAccountNonExpired());
				preparedStatement.setBoolean(9, user.isAccountNonLocked());
				preparedStatement.setBoolean(10, user.isEnabled());
				preparedStatement
						.setBoolean(11, user.isCredentialsNonExpired());

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

	@Override
	public List<UserAccount> getAllUserInfo() {
		log.debug("getAllUserInfo() > get all Users");

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
					user.setGender(rs.getString("gender"));
					user.setUserName(rs.getString("userName"));
					user.setEmailAddress(rs.getString("emailAddress"));
					user.setAccountNonExpired(rs
							.getBoolean("accountNonExpired"));
					user.setAccountNonLocked(rs.getBoolean("accountNonLocked"));
					user.setEnabled(rs.getBoolean("enabled"));
					user.setCredentialsNonExpired(rs
							.getBoolean("credentialsNonExpired"));
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

	@Override
	public void updateUser(UserAccount user, int id) {
		log.debug("updateUser() > Update user Information");
		try {
			String query = "UPDATE user SET version = ?,firstName = ?, lastName = ?,gender = ?, "
					+ "userName = ?, password = ?, emailAddress = ?, accountNonExpired = ?, "
					+ "accountNonLocked = ?, enabled = ?, credentialsNonExpired = ? WHERE id = ?";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, user.getVersion());
				preparedStatement.setString(2, user.getFirstName());
				preparedStatement.setString(3, user.getLastName());
				preparedStatement.setString(4, user.getGender());
				preparedStatement.setString(5, user.getEmailAddress());
				preparedStatement.setString(6, user.getUserName());
				preparedStatement.setString(7, user.getPassword());
				preparedStatement.setBoolean(8, user.isAccountNonExpired());
				preparedStatement.setBoolean(9, user.isAccountNonLocked());
				preparedStatement.setBoolean(10, user.isEnabled());
				preparedStatement
						.setBoolean(11, user.isCredentialsNonExpired());
				preparedStatement.setInt(12, id);

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
					user.setGender(rs.getString("gender"));
					user.setUserName(rs.getString("userName"));
					user.setEmailAddress(rs.getString("emailAddress"));
					user.setPassword(rs.getString("password"));
					user.setAccountNonExpired(rs
							.getBoolean("accountNonExpired"));
					user.setAccountNonLocked(rs.getBoolean("accountNonLocked"));
					user.setEnabled(rs.getBoolean("enabled"));
					user.setCredentialsNonExpired(rs
							.getBoolean("credentialsNonExpired"));
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
					user.setGender(rs.getString("gender"));
					user.setUserName(rs.getString("userName"));
					user.setEmailAddress(rs.getString("emailAddress"));
					user.setPassword(rs.getString("password"));
					user.setAccountNonExpired(rs
							.getBoolean("accountNonExpired"));
					user.setAccountNonLocked(rs.getBoolean("accountNonLocked"));
					user.setEnabled(rs.getBoolean("enabled"));
					user.setCredentialsNonExpired(rs
							.getBoolean("credentialsNonExpired"));
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
