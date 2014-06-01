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

import edu.univdhaka.iit.echo.SuportMethods.Converter;
import edu.univdhaka.iit.echo.domain.Photo;

/**
 * @author robin
 *This class implements the PhotoDao interface
 */
public class PhotoDaoImpl implements PhotoDao {
	private static final Logger log = LoggerFactory
			.getLogger(PhotoDaoImpl.class);

	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.PhotoDao#insertPhoto(edu.univdhaka.iit.echo.domain.Photo, int)
	 * this method helps to insert photo information in the 'photo' table related to a particular echo.
	 *  it's parameters  are Photo object echo id.... echo id is the reference to this photo object 
	 * in the 'photo' table.....
	 */
	@Override
	public void insertPhoto(Photo photo, int echoId) {
		log.debug("insert() > insert photo in the database");

		try {
			String query = "INSERT INTO photo (version, postedBy,  original, contentType, createdBy_id, echo_id)"
					+ "VALUES(?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, photo.getVersion());
				preparedStatement.setString(2, photo.getPostedBy().getUserName());
				//preparedStatement.setBlob(3, Converter.inputStreamFromByteArray(photo.getThumbnail()));
				preparedStatement.setBlob(3, Converter.inputStreamFromByteArray(photo.getOriginal()));
				preparedStatement.setString(4, photo.getContentType());
				preparedStatement.setInt(5, photo.getPostedBy().getId());
				preparedStatement.setInt(6, echoId);

				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare photo info to insert", e);
			}
		} catch (Exception e) {
			log.error("Unable to insert photo info", e);
		} finally {
			db.closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.PhotoDao#getAllPhoto()
	 * this method helps to get all photo from photo table
	 */
	@Override
	public List<Photo> getAllPhoto() {
		log.debug("getAllPhoto() > get all photo from photo table");
		String query = "SELECT * FROM photo";
		List<Photo> list = new ArrayList<Photo>();
		try {
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					Photo photo = new Photo();
					
					photo.setId(rs.getInt("id"));
					photo.setVersion(rs.getInt("version"));
					photo.setThumbnail(rs.getBlob("thumbnail").getBytes(1, (int)rs.getBlob("thumbnail").length()));
					photo.setOriginal(rs.getBlob("original").getBytes(1, (int)rs.getBlob("original").length()));
					photo.setContentType(rs.getString("contentType"));
			
					list.add(photo);
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

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.PhotoDao#deletePhoto(int)
	 * this method helps to delete photo using the reference of echo id...deleted photo
	 * is related to that echo id....
	 */
	@Override
	public void deletePhoto(int echoId) {
		log.debug("delete() > delete photo");
		try {
			String query = "DELETE FROM photo WHERE echo_id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, echoId);
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Unable to prepare photo info to delete", e);
			}
		} catch (Exception e) {
			log.error("Unable to delete photo info", e);
		} finally {
			db.closeConnection();
		}

	}

	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.PhotoDao#updatePhoto(edu.univdhaka.iit.echo.domain.Photo, int)
	 * this method helps to update photo object using photo id
	 */
	@Override
	public void updatePhoto(Photo photo, int id) {
		log.debug("upadte() > edit photo info");

		try {
			String query = "UPDATE photo SET version = ?, thumbnail = ?, original = ?, "
					+ "contentType = ? WHERE id = ?";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, photo.getVersion());
				preparedStatement.setBlob(2, Converter.inputStreamFromByteArray(photo.getThumbnail()));
				preparedStatement.setBlob(3, Converter.inputStreamFromByteArray(photo.getOriginal()));
				preparedStatement.setString(4, photo.getContentType());

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to prepare photo upadate info", e);
			}
		} catch (Exception e) {
			log.error("Unable to update photo info", e);
		} finally {
			db.closeConnection();
		}

	}

	
	/* (non-Javadoc)
	 * @see edu.univdhaka.iit.echo.dao.PhotoDao#selectPhoto(int)
	 * this method helps to select photo a photo using echo id.... selected photo
	 * is related to that echo
	 */
	@Override
	public Photo selectPhoto(int echoId) {
		log.debug("select() > select photo");

		Photo photo = new Photo();
		try {
			String query = "SELECT * FROM photo WHERE echo_id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, echoId);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					photo.setId(rs.getInt("id"));
					photo.setVersion(rs.getInt("version"));
					//photo.setThumbnail(rs.getBlob("thumbnail").getBytes(1, (int)rs.getBlob("thumbnail").length()));
					photo.setOriginal(rs.getBlob("original").getBytes(1, (int)rs.getBlob("original").length()));
					photo.setContentType(rs.getString("contentType"));
				}
				if (!flag)
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("Unable to set photo info", e);
			}
		} catch (Exception e) {
			log.error("Unable to select photo", e);
		} finally {
			db.closeConnection();
		}
		return photo;
	}
}
