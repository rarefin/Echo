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

public class EchoDaoImpl implements EchoDao {

	private static final Logger log = LoggerFactory
			.getLogger(EchoDaoImpl.class);
	
	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();

	@Override
	public void insertEcho(Echo echo) {
		log.debug("insertEcho() > insert echo in the database");
		try {
			String query = "INSERT INTO echo (version, createdDate, lastModifiedDate, "
					+ "accuracy, address, echo, timeStamp, altitude, anonymous, geoLocation,"
					+ " geoTimeStamp, latitude, longitude)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, echo.getVersion());
				preparedStatement.setDate(2, echo.getCreatedDate());
				preparedStatement.setDate(3, echo.getLastModifiedDate());
				preparedStatement.setDouble(4, echo.getAccuracy());
				preparedStatement.setString(5, echo.getAddress());
				preparedStatement.setString(6, echo.getEcho());
				preparedStatement.setDate(7, echo.getTimeStamp());
				preparedStatement.setDouble(8, echo.getAltitude());
				preparedStatement.setBoolean(9, echo.isAnonymous());
				preparedStatement.setString(10, echo.getGeoLocation());
				preparedStatement.setDate(11, echo.getGeoTimeStamp());
				preparedStatement.setDouble(12, echo.getLatitude());
				preparedStatement.setDouble(13, echo.getLongitude());
				
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare statement for inserting echo", e);
			}
		} catch (Exception e) {
			log.error("Unable to insert echo in database", e);
		} finally {
			db.closeConnection();
		}
	}

	@Override
	public List<Echo> getAllEcho() {
		log.debug("getAllEcho() > get all the echo of user from database");
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
					echo.setCreatedDate(rs.getDate("createdDate"));
					echo.setLastModifiedDate(rs.getDate("lastModifiedDate"));
					echo.setAccuracy(rs.getDouble("accuracy"));
					echo.setAddress(rs.getString("address"));
					echo.setEcho(rs.getString("echo"));
					echo.setTimeStamp(rs.getDate("timeStamp"));
					echo.setAltitude(rs.getDouble("altitude"));
					echo.setAnonymous(rs.getBoolean("anonymous"));
					echo.setGeoLocation(rs.getString("geoLocation"));
					echo.setTimeStamp(rs.getDate("geoTimeStamp"));
					echo.setLatitude(rs.getDouble("latitude"));
					echo.setLongitude(rs.getDouble("longitude"));
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

	@Override
	public void deleteEcho(String text) {
		log.debug("deleteEcho() > delete echo from the database");
		try {
				String query = "DELETE FROM echo WHERE echo = ?";
				PreparedStatement preparedStatement = null;
				try {
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, text);
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

	@Override
	public void updateEcho(Echo echo, String text) {
		log.debug("updateEcho() > edit echo in the database");
		try {
			String query = "UPDATE echo SET version = ?, "
					+ "createdDate = ?, lastModifiedDate = ?, "
					+ "accuracy = ?, address = ?, echo = ?,"
					+ " timeStamp = ?, altitude = ?,anonymous = ?, geoLocation = ?, "
					+ " geoTimeStamp = ?, latitude = ?, longitude = ? WHERE echo = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, echo.getVersion());
				preparedStatement.setDate(2, echo.getCreatedDate());
				preparedStatement.setDate(3, echo.getLastModifiedDate());
				preparedStatement.setDouble(4, echo.getAccuracy());
				preparedStatement.setString(5, echo.getAddress());
				preparedStatement.setString(6, echo.getEcho());
				preparedStatement.setDate(7, echo.getTimeStamp());
				preparedStatement.setDouble(8, echo.getAltitude());
				preparedStatement.setBoolean(9, echo.isAnonymous());
				preparedStatement.setString(10, echo.getGeoLocation());
				preparedStatement.setDate(11, echo.getGeoTimeStamp());
				preparedStatement.setDouble(12, echo.getLatitude());
				preparedStatement.setDouble(13, echo.getLongitude());
				preparedStatement.setString(14, text);
				
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

	@Override
	public Echo selectEcho(String text) {
		log.debug("selectEcho() > select an  echo in the database");
		
		Echo echo = new Echo();
		try {
			String query = "SELECT * FROM echo WHERE echo = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, text);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;

					echo.setId(rs.getInt("id"));
					echo.setVersion(rs.getInt("version"));
					echo.setCreatedDate(rs.getDate("createdDate"));
					echo.setLastModifiedDate(rs.getDate("lastModifiedDate"));
					echo.setAccuracy(rs.getDouble("accuracy"));
					echo.setAddress(rs.getString("address"));
					echo.setEcho(rs.getString("echo"));
					echo.setTimeStamp(rs.getDate("timeStamp"));
					echo.setAltitude(rs.getDouble("altitude"));
					echo.setAnonymous(rs.getBoolean("anonymous"));
					echo.setGeoLocation(rs.getString("geoLocation"));
					echo.setTimeStamp(rs.getDate("geoTimeStamp"));
					echo.setLatitude(rs.getDouble("latitude"));
					echo.setLongitude(rs.getDouble("longitude"));
				}
				if (!flag)
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to get an echo info", e);
			}
		} catch (Exception e) {
			log.error("Unable to select an echo", e);
		} finally {
			db.closeConnection();
		}
		return echo;
	}
}