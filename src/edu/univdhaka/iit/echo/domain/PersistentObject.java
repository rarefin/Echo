package edu.univdhaka.iit.echo.domain;

import java.sql.Date;

// Abstract Class for General Information
public abstract class PersistentObject {
    private String createdDate;
	private Date lastModifiedDate;
	private UserAccount lastModifiedBy;
    private UserAccount createdBy;
    
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public UserAccount getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(UserAccount lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public UserAccount getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserAccount createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}  
}
