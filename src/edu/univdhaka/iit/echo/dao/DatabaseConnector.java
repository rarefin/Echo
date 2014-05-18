package edu.univdhaka.iit.echo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.web.service.MailServices;

public class DatabaseConnector {

	private static final Logger log = LoggerFactory.getLogger(DatabaseConnector.class);
	
	private static final String DATABASE_NAME = "ECHO";
	private static final String DATABASE_USER_NAME = "root";
	private static final String DATABASE_PASSWORD = "";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	public Connection getConnection() {
		log.debug("getConnection() -> This method suppose to create database conection");
		Connection connect = null;
		try {
			Class.forName(DRIVER_NAME);
			connect = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Unable to get connection", e);
		}

		return connect;
	}

	public Connection openConnection() {
		log.debug("openConnection() -> This method suppose to open database conection");
		return getConnection();
	}
	
	public void closeConnection(){
		log.debug("closeConnection() -> This method suppose to close database conection");
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Unable to close connection", e);

		}
	}

}