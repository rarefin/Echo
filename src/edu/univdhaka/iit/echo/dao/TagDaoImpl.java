package edu.univdhaka.iit.echo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.univdhaka.iit.echo.domain.Tag;

public class TagDaoImpl implements TagDao {
	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();
	
	@Override
	public void insert(Tag tag) {
		try {
			String query = "INSERT INTO tag (version, title)" 
					 + "VALUES(?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, tag.getVersion());
				preparedStatement.setString(2, tag.getTitle());
				
				preparedStatement.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
		
		} finally {
			db.closeConnection();
		}	
	}

	@Override
	public List<Tag> getAllTagInfo() {
		String query = "SELECT * FROM tag";
		List<Tag> list = new ArrayList<Tag>();
		try {
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					Tag tag = new Tag();
					
					tag.setId(rs.getInt("id"));
					tag.setVersion(rs.getInt("version"));
					tag.setTitle(rs.getString("title"));
			
					list.add(tag);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			
		}finally{
			db.closeConnection();
		}
		return list;
	}

	@Override
	public void delete(int id) {
		try {
			String query = "DELETE FROM tag WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			
		}finally{
			db.closeConnection();
		}
		
	}

	@Override
	public void update(Tag tag, int id) {
		try{
			String query = "UPDATE tag SET version = ?, "
							 +	"title =  ? WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, tag.getVersion());
				preparedStatement.setString(2, tag.getTitle());
				preparedStatement.setInt(3, id);
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch(Exception e){
			
		}finally{
			db.closeConnection();
		}
		
	}

	@Override
	public Tag select(int id) {
		Tag tag = new Tag();
		try{
			String query = "SELECT * FROM tag WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					
					tag.setId(rs.getInt("id"));
					tag.setVersion(rs.getInt("version"));
					tag.setTitle(rs.getString("title"));
				}
				if (!flag)
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			
		}finally {
			db.closeConnection();
		}
		return tag;
	}

}
