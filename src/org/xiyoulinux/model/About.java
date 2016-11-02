package org.xiyoulinux.model;

/**
 * Created by zhoupan on 10/31/16.
 */
public class About {
    private int id;
    private String title;
    private String content;
    private String picture_url;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPicture_url() {
        return picture_url;
    }
}
