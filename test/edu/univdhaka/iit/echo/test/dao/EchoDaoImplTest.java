package edu.univdhaka.iit.echo.test.dao;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.univdhaka.iit.echo.dao.EchoDao;
import edu.univdhaka.iit.echo.dao.EchoDaoImpl;
import edu.univdhaka.iit.echo.domain.Echo;

public class EchoDaoImplTest {

	private static final int ID = 1;
	private static final int VERSION = 0;
	private static final int ECHO_ID = 5;
	private static final String CREATEDDATE  = null;
	private static final Date LASTMODIFIEDDATE  = null;
	private static final double ACCURACY = 12.5;
	private static final String ADDRESS = "dHAKA";
	private static final String ECHO = "what a joke";
	private static final Date TIMESTAMP  = null;
	private static final double ALTITUDE = 12.3;
	private static final String GEOTIMESTAMP  = null;
	private static final double LATITUDE = 12.1;
	private static final double LONGITUDE = 12.4;
	private static final boolean ANONYMOUS = true;
	private static final String GEOLOCATION = "Dhaka";
	
	private EchoDao echoDao;
	
	@Before
	public void setUp() {
		echoDao = new EchoDaoImpl();
	}

	/*@Test
	public void testInsertEcho() {
		Echo echo =  new Echo();
		
		echo.setVersion(VERSION);
		echo.setCreatedDate(CREATEDDATE);
		echo.setLastModifiedDate(LASTMODIFIEDDATE);
		echo.setAccuracy(ACCURACY);
		echo.setAddress(ADDRESS);
		echo.setEcho(ECHO);
		echo.setTimeStamp(TIMESTAMP);
		echo.setAltitude(ALTITUDE);
		echo.setGeoTimeStamp(GEOTIMESTAMP);
		echo.setLatitude(LATITUDE);
		echo.setLongitude(LONGITUDE);
		echo.setAnonymous(ANONYMOUS);
		echo.setGeoLocation(GEOLOCATION);
		
		echoDao.insertEcho(echo);
	}*/
	
	@Test
	public void testDeleteEcho() {
		echoDao.deleteEcho(ECHO_ID);
		
		// Check By Asserting
		Echo newEcho = echoDao.selectOneEcho(ECHO_ID);
				
		Assert.assertNull(newEcho);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateEcho() {
		Echo echo =  new Echo();
		
		echo.setId(ID);
		echo.setVersion(VERSION);
		echo.setCreatedDate(CREATEDDATE);
		echo.setLastModifiedDate(LASTMODIFIEDDATE);
		echo.setAccuracy(ACCURACY);
		echo.setAddress(ADDRESS);
		echo.setEcho(ECHO);
		echo.setTimeStamp(TIMESTAMP);
		echo.setAltitude(ALTITUDE);
		echo.setGeoTimeStamp(GEOTIMESTAMP);
		echo.setLatitude(LATITUDE);
		echo.setLongitude(LONGITUDE);
		echo.setAnonymous(ANONYMOUS);
		echo.setGeoLocation(GEOLOCATION);

		echoDao.updateEcho(echo, ECHO_ID);
		
		// Check by Asserting 
		Echo newEcho = echoDao.selectOneEcho(ECHO_ID);
		
		Assert.assertNotNull(newEcho);
		
		Assert.assertEquals(VERSION, newEcho.getVersion());
		Assert.assertEquals(CREATEDDATE, newEcho.getCreatedDate());
		Assert.assertEquals(LASTMODIFIEDDATE, newEcho.getLastModifiedDate());
		Assert.assertEquals(ACCURACY, newEcho.getAccuracy());
		Assert.assertEquals(ADDRESS, newEcho.getAddress());
		Assert.assertEquals(ECHO, newEcho.getEcho());
		Assert.assertEquals(TIMESTAMP, newEcho.getTimeStamp());
		Assert.assertEquals(ALTITUDE, newEcho.getAltitude());
		Assert.assertEquals(GEOTIMESTAMP, newEcho.getGeoTimeStamp());
		Assert.assertEquals(LATITUDE, newEcho.getLatitude());
		Assert.assertEquals(LONGITUDE, newEcho.getLongitude());
		Assert.assertEquals(ANONYMOUS, newEcho.isAnonymous());
		Assert.assertEquals(GEOLOCATION, newEcho.getGeoLocation());
	}

	@Test
	public void testSelectEcho() {
		Echo newEcho = echoDao.selectOneEcho(ECHO_ID);
		
		Assert.assertNotNull(newEcho);
		
		Assert.assertEquals(VERSION, newEcho.getVersion());
		Assert.assertEquals(CREATEDDATE, newEcho.getCreatedDate());
		Assert.assertEquals(LASTMODIFIEDDATE, newEcho.getLastModifiedDate());
		Assert.assertEquals(ACCURACY, newEcho.getAccuracy());
		Assert.assertEquals(ADDRESS, newEcho.getAddress());
		Assert.assertEquals(ECHO, newEcho.getEcho());
		Assert.assertEquals(TIMESTAMP, newEcho.getTimeStamp());
		Assert.assertEquals(ALTITUDE, newEcho.getAltitude());
		Assert.assertEquals(GEOTIMESTAMP, newEcho.getGeoTimeStamp());
		Assert.assertEquals(LATITUDE, newEcho.getLatitude());
		Assert.assertEquals(LONGITUDE, newEcho.getLongitude());
		Assert.assertEquals(ANONYMOUS, newEcho.isAnonymous());
		Assert.assertEquals(GEOLOCATION, newEcho.getGeoLocation());
	}

	@Test
	public void testGetAllEcho() {
		echoDao.getAllEcho();
	}

}
