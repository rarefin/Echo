package edu.univdhaka.iit.echo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.univdhaka.iit.echo.domain.Authority;
import edu.univdhaka.iit.echo.domain.IssueCategory;
import edu.univdhaka.iit.echo.domain.Photo;
import edu.univdhaka.iit.echo.domain.UserAccount;

public class PhotoDaoImpl implements PhotoDao {
	private static final Logger log = LoggerFactory
			.getLogger(PhotoDaoImpl.class);

	DatabaseConnector db = new DatabaseConnector();
	Connection connection = db.openConnection();

	@Override
	public void insert(Photo photo) {
		log.debug("insert() > insert photo in the database");

		try {
			String query = "INSERT INTO photo (version, thumbnail, original, contentType)"
					+ "VALUES(?, ?, ?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, photo.getVersion());
				preparedStatement.setBlob(2, photo.getThumbnail());
				preparedStatement.setBlob(3, photo.getOriginal());
				preparedStatement.setString(4, photo.getContentType());

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

	@Override
	public List<Photo> getAllPhotoInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		log.debug("delete() > delete photo");
		try {
			String query = "DELETE FROM photo WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
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

	@Override
	public void update(Photo photo, int id) {
		log.debug("upadte() > edit photo info");

		try {
			String query = "UPDATE photo SET version = ?, thumbnail = ?, original = ?, "
					+ "contentType = ? WHERE id = ?";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, photo.getVersion());
				preparedStatement.setBlob(2, photo.getThumbnail());
				preparedStatement.setBlob(3, photo.getOriginal());
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

	@Override
	public Photo select(int id) {
		log.debug("select() > select photo");

		Photo photo = new Photo();
		try {
			String query = "SELECT * FROM photo WHERE id = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					photo.setId(rs.getInt("id"));
					photo.setVersion(rs.getInt("version"));
					photo.setThumbnail(rs.getBlob("thumbnail"));
					photo.setOriginal(rs.getBlob("original"));
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
