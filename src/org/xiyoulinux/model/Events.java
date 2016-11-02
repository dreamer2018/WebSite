package org.xiyoulinux.model;


/**
 * Created by zhoupan on 10/31/16.
 */
public class Events {
    private int id;
    private String title;
    private String content;
    private String poster_url;
    private String date;
    private String time;
    private String address;
    private String tips;
    private int reader;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public void setReader(int reader) {
        this.reader = reader;
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

    public String getPoster_url() {
        return poster_url;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    public String getTips() {
        return tips;
    }

    public int getReader() {
        return reader;
    }
}
