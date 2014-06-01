package edu.univdhaka.iit.echo.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.univdhaka.iit.echo.dao.PhotoDao;
import edu.univdhaka.iit.echo.dao.PhotoDaoImpl;
import edu.univdhaka.iit.echo.domain.Photo;

/**
 * Servlet implementation class ImageController....This class helps to view all 
 * images related to an echo
 */
@WebServlet("/image")
public class ImageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PhotoDao photoDao = new PhotoDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Fetching the image from database using echo id and sending it to web page
		String id = req.getParameter("echoId");
		if (id != null && !id.isEmpty()) {
			int echoId = Integer.parseInt(id);
			Photo photo = photoDao.selectPhoto(echoId);
			byte[] fileContent = photo.getOriginal();
			

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write(fileContent);

			resp.setContentType(photo.getContentType());
			OutputStream os = resp.getOutputStream();

			out.writeTo(os);
			os.flush();
		} else {
			throw new RuntimeException("Illegal parameter");
		}
	}
}
