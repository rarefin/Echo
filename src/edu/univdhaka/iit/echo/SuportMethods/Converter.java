package edu.univdhaka.iit.echo.SuportMethods;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this class contains some methods those convert some data.
 */
public class Converter {
	
	private static final Logger log = LoggerFactory
			.getLogger(Converter.class);
	
	
	/**
	 * this method returns the date as string from current date
	 */
	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEEE, d-MMM-yyyy 'at' hh:mm:ss a zz");

		Date time = new Date();
		String dateString = formatter.format(time);
		
		return dateString;
	}
	
	/**
	 * this method converts Inputstream to byte array
	 */
	public static byte[] byteArrayFromInputStream(InputStream input){
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
	        byte[] buffer = new byte[1000000];

	        for (int len; (len = input.read(buffer)) != -1;)
	            os.write(buffer, 0, len);

	        os.flush();

	        return os.toByteArray();
	    }
	    catch (IOException e){
	        return null;
	    }
	}
	
	/**
	 * this method converts byte array to inputstream
	 */
	public static InputStream inputStreamFromByteArray(byte[] byteBuffer){
		
		InputStream myInputStream = new ByteArrayInputStream(byteBuffer);
		
		return myInputStream;
	}
	
}
