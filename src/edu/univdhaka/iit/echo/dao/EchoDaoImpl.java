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

import edu.univdhaka.iit.echo.domain.Echo;

/**
 * @author robin
 *This class implements the userDao interface
 */
public class EchoDaoImpl implements EchoDao {

	private static final Logger log = LoggerFactory
			.getLogger(EchoDaoImpl.class);

	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();
	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.EchoDao#insertEcho(edu.univdhaka.iit.echo.domain.Echo, int)
	 * this method helps to insert echo data in the 'echo' table. it's parameters  are Echo object 
	 * issue category id....this method returns echo id from the echo table which will be a reference 
	 * in the 'photo' table.....
	 */
	@Override
	public int insertEcho(Echo echo, int issueCategoryId) {
		log.debug("insertEcho() > insert echo in the database");
		int echoId = -1;
		try {
			String query = "INSERT INTO echo (version, postedBy, createdDate, lastModifiedDate, "
					+ "accuracy, altitude, geoLocation, geoTimeStamp, latitude, longitude,"
					+ " speed, echo, anonymous, issueCategory, createdBy_id, issueCategory_id)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setInt(1, echo.getVersion());
				preparedStatement
						.setString(2, echo.getPostedBy().getUserName());
				preparedStatement.setString(3, echo.getCreatedDate());
				preparedStatement.setDate(4, echo.getLastModifiedDate());
				preparedStatement.setDouble(5, echo.getAccuracy());
				preparedStatement.setDouble(6, echo.getAltitude());
				preparedStatement.setString(7, echo.getGeoLocation());
				preparedStatement.setString(8, echo.getGeoTimeStamp());
				preparedStatement.setDouble(9, echo.getLatitude());
				preparedStatement.setDouble(10, echo.getLongitude());
				preparedStatement.setDouble(11, echo.getSpeed());
				preparedStatement.setString(12, echo.getEcho());
				preparedStatement.setBoolean(13, echo.isAnonymous());
				preparedStatement.setString(14, echo.getIssueCategory());
				preparedStatement.setInt(15, echo.getPostedBy().getId());
				preparedStatement.setInt(16, issueCategoryId);

				preparedStatement.execute();

				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					echoId = rs.getInt(1);
				} else {
					log.error("Unable to get echo id from inserting echo");
				}
				System.out.println("Echo key: " + echoId);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare statement for inserting echo", e);
			}
		} catch (Exception e) {
			log.error("Unable to insert echo in database", e);
		} finally {
			db.closeConnection();
		}
		return echoId;
	}

	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.EchoDao#getAllEcho()
	 * this method will helped to get all echos from the 'echo' table which will be displayed in 
	 * in home page as recent echos.
	 */
	@Override
	public List<Echo> getAllEcho() {
		log.debug("getAllEcho() > get all the echo of all users from database");
		List<Echo> list = new ArrayList<Echo>();
		try {
			String query = "SELECT * FROM echo";
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					Echo echo = new Echo();

					echo.setId(rs.getInt("id"));
					echo.setVersion(rs.getInt("version"));
					echo.setCreatedDate(rs.getString("createdDate"));
					echo.setUserName(rs.getString("postedBy"));
					echo.setIssueCategory(rs.getString("issueCategory"));
					echo.setLastModifiedDate(rs.getDate("lastModifiedDate"));
					echo.setAccuracy(rs.getDouble("accuracy"));
					echo.setEcho(rs.getString("echo"));
					echo.setAltitude(rs.getDouble("altitude"));
					echo.setAnonymous(rs.getBoolean("anonymous"));
					echo.setGeoLocation(rs.getString("geoLocation"));
					echo.setGeoTimeStamp(rs.getString("geoTimeStamp"));
					echo.setLatitude(rs.getDouble("latitude"));
					echo.setLongitude(rs.getDouble("longitude"));
					echo.setSpeed(rs.getDouble("speed"));
					list.add(echo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to get All echo info", e);
			}
		} catch (Exception e) {
			log.error("Unable to select all echo", e);
		} finally {
			db.closeConnection();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.EchoDao#deleteEcho(int)
	 * this method will delete the echo from the table on the basis of echo id
	 */
	@Override
	public void deleteEcho(int echoId) {
		log.debug("deleteEcho() > delete echo from the echo table");
		try {
			String query = "DELETE FROM echo WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, echoId);
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare statement for delete in echo", e);
			}
		} catch (Exception e) {
			log.error("Unable to delete echo", e);
		} finally {
			db.closeConnection();
		}

	}


	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.EchoDao#updateEcho(edu.univdhaka.iit.echo.domain.Echo, int)
	 * this method will help to update data in the echo table of particular echo id
	 */
	@Override
	public void updateEcho(Echo echo, int echoId) {
		log.debug("updateEcho() > edit echo in the database");
		try {
			String query = "UPDATE echo SET version = ?, "
					+ "createdDate = ?, lastModifiedDate = ?, "
					+ "accuracy = ?, address = ?, echo = ?,"
					+ " timeStamp = ?, altitude = ?,anonymous = ?, geoLocation = ?, "
					+ " geoTimeStamp = ?, latitude = ?, longitude = ?, speed = ?, issueCategory = ? WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, echo.getVersion());
				preparedStatement.setString(2, echo.getCreatedDate());
				preparedStatement.setDate(3, echo.getLastModifiedDate());
				preparedStatement.setDouble(4, echo.getAccuracy());
				preparedStatement.setString(5, echo.getAddress());
				preparedStatement.setString(6, echo.getEcho());
				preparedStatement.setDate(7, echo.getTimeStamp());
				preparedStatement.setDouble(8, echo.getAltitude());
				preparedStatement.setBoolean(9, echo.isAnonymous());
				preparedStatement.setString(10, echo.getGeoLocation());
				preparedStatement.setString(11, echo.getGeoTimeStamp());
				preparedStatement.setDouble(12, echo.getLatitude());
				preparedStatement.setDouble(13, echo.getLongitude());
				preparedStatement.setDouble(14, echo.getSpeed());
				preparedStatement.setString(15, echo.getIssueCategory());
				preparedStatement.setInt(16, echoId);

				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to prepare statement for update", e);
			}
		} catch (Exception e) {
			log.error("Unable to update in echo", e);
		} finally {
			db.closeConnection();
		}

	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.EchoDao#selectOneEcho(int)
	 * this method helps to find one echo using echo id
	 */
	@Override
	public Echo selectOneEcho(int echoId) {
		log.debug("selectEcho() > select an  echo from 'echo' table");

		Echo echo = new Echo();
		try {
			String query = "SELECT * FROM echo WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, echoId);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;

					echo.setId(rs.getInt("id"));
					echo.setVersion(rs.getInt("version"));
					echo.setUserName(rs.getString("postedBy"));
					echo.setCreatedDate(rs.getString("createdDate"));
					echo.setLastModifiedDate(rs.getDate("lastModifiedDate"));
					echo.setAccuracy(rs.getDouble("accuracy"));
					echo.setEcho(rs.getString("echo"));
					echo.setIssueCategory(rs.getString("issueCategory"));
					echo.setAltitude(rs.getDouble("altitude"));
					echo.setAnonymous(rs.getBoolean("anonymous"));
					echo.setGeoLocation(rs.getString("geoLocation"));
					echo.setTimeStamp(rs.getDate("geoTimeStamp"));
					echo.setLatitude(rs.getDouble("latitude"));
					echo.setLongitude(rs.getDouble("longitude"));
					echo.setSpeed(rs.getDouble("speed"));
				}
				if (!flag)
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to get an echo", e);
			}
		} catch (Exception e) {
			log.error("Unable to select an echo", e);
		} finally {
			db.closeConnection();
		}
		return echo;
	}

	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.EchoDao#findEchoByUserName(java.lang.String)
	 * this method helps find all echo of a particular user
	 */
	@Override
	public List<Echo> findEchoByUserName(String userName) {
		log.debug("findEchoByUserName(String userName) > get all the echo of a user from 'echo' table");
		List<Echo> list = new ArrayList<Echo>();
		try {
			String query = "SELECT * FROM echo where postedBy = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, userName);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Echo echo = new Echo();

					echo.setId(rs.getInt("id"));
					echo.setVersion(rs.getInt("version"));
					echo.setUserName(rs.getString("postedBy"));
					echo.setCreatedDate(rs.getString("createdDate"));
					echo.setLastModifiedDate(rs.getDate("lastModifiedDate"));
					echo.setAccuracy(rs.getDouble("accuracy"));
					echo.setEcho(rs.getString("echo"));
					echo.setIssueCategory(rs.getString("issueCategory"));
					echo.setAltitude(rs.getDouble("altitude"));
					echo.setAnonymous(rs.getBoolean("anonymous"));
					echo.setGeoLocation(rs.getString("geoLocation"));
					echo.setTimeStamp(rs.getDate("geoTimeStamp"));
					echo.setLatitude(rs.getDouble("latitude"));
					echo.setLongitude(rs.getDouble("longitude"));
					echo.setSpeed(rs.getDouble("speed"));
					list.add(echo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to get All echo of a user", e);
			}
		} catch (Exception e) {
			log.error("Unable to find all echo of a user", e);
		} finally {
			db.closeConnection();
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.EchoDao#findEchoByIssueCategory(java.lang.String)
	 * this method helps to find all echo a particular issue category
	 */
	@Override
	public List<Echo> findEchoByIssueCategory(String issueCategory) {
		log.debug("findEchoByIssueCategory(String issueCategory) > get all echo related to a particular issue category");
		List<Echo> list = new ArrayList<Echo>();
		try {
			String query = "SELECT * FROM echo where issueCategory = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, issueCategory);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Echo echo = new Echo();

					echo.setId(rs.getInt("id"));
					echo.setVersion(rs.getInt("version"));
					echo.setUserName(rs.getString("postedBy"));
					echo.setCreatedDate(rs.getString("createdDate"));
					echo.setLastModifiedDate(rs.getDate("lastModifiedDate"));
					echo.setAccuracy(rs.getDouble("accuracy"));
					echo.setEcho(rs.getString("echo"));
					echo.setIssueCategory(rs.getString("issueCategory"));
					echo.setAltitude(rs.getDouble("altitude"));
					echo.setAnonymous(rs.getBoolean("anonymous"));
					echo.setGeoLocation(rs.getString("geoLocation"));
					echo.setTimeStamp(rs.getDate("geoTimeStamp"));
					echo.setLatitude(rs.getDouble("latitude"));
					echo.setLongitude(rs.getDouble("longitude"));
					echo.setSpeed(rs.getDouble("speed"));
					list.add(echo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to get All echo of a category", e);
			}
		} catch (Exception e) {
			log.error("Unable to find all echo of a category", e);
		} finally {
			db.closeConnection();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.EchoDao#getIssueCategoryId(int)
	 * this method helps to find the issue category id of a particular echo using the echo id. this issue 
	 * category id will help to find the issue from the issue category table
	 */
	@Override
	public int getIssueCategoryId(int echoId) {
		log.debug("getIssueCategoryId(int echoId) > get issue category id from 'echo' table");

		int issueCategoryId = 0;
		String query = "SELECT * FROM echo WHERE id = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, echoId);
			ResultSet rs = preparedStatement.executeQuery();
			issueCategoryId = rs.getInt("issueCategory_id");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Unable to get issue category id", e);
		} finally {
			db.closeConnection();
		}
		return issueCategoryId;
	}
}