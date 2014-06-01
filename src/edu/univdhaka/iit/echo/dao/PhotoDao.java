package edu.univdhaka.iit.echo.dao;

import java.util.List;

import edu.univdhaka.iit.echo.domain.Photo;

// This is the interface that contains the method to manipulate the 'photo' table in database
public interface PhotoDao {
	
	public void insertPhoto(Photo photo, int echoId);

	public List<Photo> getAllPhoto();

	public void deletePhoto(int echoId);

	public void updatePhoto(Photo photo, int id);

	public Photo selectPhoto(int id);
	
}
