package edu.univdhaka.iit.echo.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * this class contains the attributes related to posted text called echo.
 *
 */
public class Echo extends PersistentObject implements Serializable {

	private int id;
	private int version;

	private String userName;
	private String echo;
	private UserAccount postedBy;
	private String issueCategory;
	private Set<Tag> tags;
	private Photo photo;
	private String address;
	private Date timeStamp;

	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public String getEcho() {
		return echo;
	}


	public void setEcho(String echo) {
		this.echo = echo;
	}


	public UserAccount getPostedBy() {
		return postedBy;
	}


	public void setPostedBy(UserAccount postedBy) {
		this.postedBy = postedBy;
	}


	public String getIssueCategory() {
		return issueCategory;
	}


	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}


	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getAccuracy() {
		return accuracy;
	}


	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}


	public double getAltitude() {
		return altitude;
	}


	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}


	public String getGeoLocation() {
		return geoLocation;
	}


	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}


	public String getGeoTimeStamp() {
		return geoTimeStamp;
	}


	public void setGeoTimeStamp(String string) {
		this.geoTimeStamp = string;
	}


	public boolean isAnonymous() {
		return anonymous;
	}


	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}


	// GeoData
	private double longitude;
	private double latitude;
	private double accuracy;
	private double altitude;
	private double speed;
	private String geoLocation;
	private String geoTimeStamp;

	private boolean anonymous;


	@Override
	public String toString() {
		return "Echo{" + "id=" + id + ", postedText='" + echo + '\''
				+ ", timestamp=" + geoTimeStamp + ", postedBy=" + postedBy
				+ '}';
	}


	public Photo getPhoto() {
		return photo;
	}


	public void setPhoto(Photo photo) {
		this.photo = photo;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
