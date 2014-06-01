package edu.univdhaka.iit.echo.domain;
import java.io.Serializable;

/**
 * this class contains the attributes of issue category related to an echo
 *
 */
public class IssueCategory extends PersistentObject implements Serializable {

    private int id;
    private int version;
    private String title;
    private boolean isNew;

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }


    public int getId() {
        return id;
    }

    public boolean isNew() {
        return isNew;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
