package edu.univdhaka.iit.echo.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// These Class has some methods those help to create and close database connection
public class DatabaseConnector {

	private static final Logger log = LoggerFactory
			.getLogger(DatabaseConnector.class);

	private static final String DATABASE_NAME = "ECHO";
	private static final String DATABASE_USER_NAME = "root";
	private static final String DATABASE_PASSWORD = "";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/"
			+ DATABASE_NAME;
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	// This method gets connection from database
	public Connection getConnection() {
		log.debug("getConnection() -> This method suppose to create database conection");
		Connection connect = null;
		try {
			Class.forName(DRIVER_NAME);
			connect = DriverManager.getConnection(DATABASE_URL,
					DATABASE_USER_NAME, DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Unable to get connection", e);
		}

		return connect;
	}

	// These method helps to open connection using get connection method
	public Connection openConnection() {
		log.debug("openConnection() -> This method suppose to open database conection");
		return getConnection();
	}

	// This method helps to close database connection after using database
	public void closeConnection() {
		log.debug("closeConnection() -> This method suppose to close database conection");
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Unable to close connection", e);
		}
	}

}