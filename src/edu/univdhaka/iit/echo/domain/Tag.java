package edu.univdhaka.iit.echo.domain;

import java.io.Serializable;

public class Tag implements Serializable {

    private int id;
    private int version;
    private String title;
    private Echo echo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

	public Echo getEcho() {
		return echo;
	}

	public void setEcho(Echo echo) {
		this.echo = echo;
	}
}
