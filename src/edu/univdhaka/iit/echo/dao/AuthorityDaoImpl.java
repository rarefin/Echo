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

import edu.univdhaka.iit.echo.domain.Authority;
import edu.univdhaka.iit.web.service.MailServices;

public class AuthorityDaoImpl implements AuthorityDao{
	private static final Logger log = LoggerFactory.getLogger(AuthorityDaoImpl.class);
	
	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();
	
	@Override
	public void insert(Authority authority) {
		log.debug("insert() > insert authority info in the database");
		try {
			String query = "INSERT INTO authority (version, name)" 
					 + "VALUES(?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, authority.getVersion());
				preparedStatement.setString(2, authority.getName());
				
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to prepare authority info to inset", e);
			}
		} catch (Exception e) {
			log.error("Unable to insert authority info", e);
		} finally {
			db.closeConnection();
		}	
	}

	@Override
	public List<Authority> getAllAuthorityInfo() {
		log.debug("getAllAuthorityInfo() > get authority info of all users");
		
		String query = "SELECT * FROM authority";
		List<Authority> list = new ArrayList<Authority>();
		try {
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					Authority authority = new Authority();
					
					authority.setId(rs.getInt("id"));
					authority.setVersion(rs.getInt("version"));
					authority.setName(rs.getString("name"));
			
					list.add(authority);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to set all user's authority info from authority table ", e);
			}
		}catch (Exception e) {
			log.error("Unable to select all user's authority info from authority table ", e);
		}finally{
			db.closeConnection();
		}
		return list;
	}

	@Override
	public void delete(int id) {
		log.debug("delete() > delete authority info");
		try {
			String query = "DELETE FROM authority WHERE id  = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare authority info to delete", e);
			}
		}catch(Exception e) {
			log.error("Unable to delete authority info", e);
		}finally{
			db.closeConnection();
		}
		
	}

	@Override
	public void update(Authority authority, int id) {
		log.debug("upadte() > update authority info");
		try{
			String query = "UPDATE authority SET version = ?, name = ? WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, authority.getVersion());
				preparedStatement.setString(2, authority.getName());
				preparedStatement.setInt(3, id);
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to prepare auhtority upadate info", e);
			}
		} catch(Exception e){
			log.error("Unable to update authority info", e);
		}finally{
			db.closeConnection();
		}
		
	}

	@Override
	public Authority select(int id) {
		log.debug("select() > select authority info of a user");
		
		Authority authority = new Authority();
		try{
			String query = "SELECT * FROM authority WHERE id  = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					
					authority.setId(rs.getInt("id"));
					authority.setVersion(rs.getInt("version"));
					authority.setName(rs.getString("name"));
				}
				if (!flag)
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to set all authority info from authority table ", e);
			}
		}catch (Exception e) {
			log.error("Unable to select authority info from authority table ", e);
		}finally {
			db.closeConnection();
		}
		return authority;
	}
}
