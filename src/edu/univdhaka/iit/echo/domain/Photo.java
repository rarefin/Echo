package edu.univdhaka.iit.echo.domain;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.util.UUID;


/**
 * this class contains the attributes related to a photo
 *
 */
public class Photo implements Serializable {

    private int id;
    private int version;
    private byte[] thumbnail;
    private byte[] original;
    private String contentType;
    private UserAccount postedBy;
    private Echo echo;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public byte[] getOriginal() {
		return original;
	}

	public void setOriginal(byte[] original) {
		this.original = original;
	}
	public Echo getEcho() {
		return echo;
	}

	public void setEcho(Echo echo) {
		this.echo = echo;
	}

	public UserAccount getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(UserAccount postedBy) {
		this.postedBy = postedBy;
	}
}
