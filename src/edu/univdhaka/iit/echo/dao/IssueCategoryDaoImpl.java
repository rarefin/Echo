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

import edu.univdhaka.iit.echo.domain.IssueCategory;

/**
 * @author robin
 *This class implements the IssuecategoryDao interface
 */
public class IssueCategoryDaoImpl implements IssueCategoryDao {
	private static final Logger log = LoggerFactory.getLogger(IssueCategoryDaoImpl.class);
	
	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();
	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.IssueCategoryDao#insert(edu.univdhaka.iit.echo.domain.IssueCategory, int)
	 * this method helps to insert echo data in the 'issue_category' table. it's parameters  are IssueCategory object 
	 * issue user id....this method returns issue category id from the 'issue_category' table which will be a reference 
	 * in the 'echo' table.....
	 */
	@Override
	public int insert(IssueCategory category, int userId) {
		log.debug("insert() > insert issue category info in the database");
		int issueCategoryId = -1;
		try {
			String query = "INSERT INTO issue_category (version, createdDate, lastModifiedDate, isNew, title, createdBy_id)" 
					 + "VALUES(?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setInt(1, category.getVersion());
				preparedStatement.setString(2, category.getCreatedDate());
				preparedStatement.setDate(3, category.getLastModifiedDate());
				preparedStatement.setBoolean(4, category.isNew());
				preparedStatement.setString(5, category.getTitle());
				preparedStatement.setInt(6, userId);
				
				preparedStatement.execute();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
				    issueCategoryId = rs.getInt(1);
				} else {
					log.error("Unable to get Issue Category id from inserting Issue Category");
				}
				System.out.println("Issue category key: "+  issueCategoryId);
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare issue category info to insert", e);
			}
		} catch (Exception e) {
			log.error("Unable to insert issue category info", e);
		} finally {
			db.closeConnection();
		}	
		return issueCategoryId;
	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.IssueCategoryDao#getAllIssueCategoryInfo()
	 * this method will help to get all issue catgory info
	 */
	@Override
	public List<IssueCategory> getAllIssueCategoryInfo() {
		log.debug("getAllIssueCategoryInfo() > get issue category info of all users");
		
		List<IssueCategory> list = new ArrayList<IssueCategory>();
		try {
			String query = "SELECT * FROM issue_category";
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					IssueCategory category = new IssueCategory();
					
					category.setId(rs.getInt("id"));
					category.setVersion(rs.getInt("version"));
					category.setCreatedDate(rs.getString("createdDate"));
					category.setLastModifiedDate(rs.getDate("lastModifiedDate"));
					category.setNew(rs.getBoolean("isNew"));
					category.setTitle(rs.getString("title"));
			
					list.add(category);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to set all user's issue category info from authority table ", e);
			}
		}catch (Exception e) {
			log.error("Unable to select all user's issue category info from authority table ", e);
		}finally{
			db.closeConnection();
		}
		return list;
	}

	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.IssueCategoryDao#delete(int)
	 * this method will help to delete a category using issue category id
	 */
	@Override
	public void delete(int id) {
		log.debug("delete() > delete issue categoy info");
		try {
			String query = "DELETE FROM issue_category WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare issue category info to delete", e);
			}
		}catch(Exception e) {
			log.error("Unable to delete issue category info", e);
		}finally{
			db.closeConnection();
		}	
	}

	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.IssueCategoryDao#update(edu.univdhaka.iit.echo.domain.IssueCategory, int)
	 * this method will help to update a issue category using its id
	 */
	@Override
	public void update(IssueCategory category, int id) {
		log.debug("upadte() > update issue category info");
		
		try{
			String query = "UPDATE issue_category SET version = ?, "
							 +	"createdDate = ?, lastModifiedDate = ?, "
							 + "isNew = ?, title =  ? WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, category.getVersion());
				preparedStatement.setString(2, category.getCreatedDate());
				preparedStatement.setDate(3, category.getLastModifiedDate());
				preparedStatement.setBoolean(4, category.isNew());
				preparedStatement.setString(5, category.getTitle());
				preparedStatement.setInt(6, id);
				
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to prepare issue category upadate info", e);
			}
		} catch(Exception e){
			log.error("Unable to update issue category info", e);
		}finally{
			db.closeConnection();
		}
		
	}

	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.IssueCategoryDao#select(int)
	 * this method helps to find a issue category using its id 
	 */
	@Override
	public IssueCategory select(int id) {
		log.debug("select() > select issue category info of a user");
		
		IssueCategory category = new IssueCategory();
		try{
			String query = "SELECT * FROM issue_category WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					
					category.setId(rs.getInt("id"));
					category.setVersion(rs.getInt("version"));
					category.setCreatedDate(rs.getString("createdDate"));
					category.setLastModifiedDate(rs.getDate("lastModifiedDate"));
					category.setNew(rs.getBoolean("isNew"));
					category.setTitle(rs.getString("title"));
				}
				if (!flag)
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to set all issue category info from authority table ", e);
			}
		}catch (Exception e) {
			log.error("Unable to select issue category info from authority table ", e);
		}finally {
			db.closeConnection();
		}
		return category;
	}

}
