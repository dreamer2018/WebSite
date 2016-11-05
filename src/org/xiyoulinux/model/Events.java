package org.xiyoulinux.model;


import java.sql.Date;
import java.sql.Time;

/**
 * Created by zhoupan on 10/31/16.
 */
public class Events {
    /*
    * 数据库中定义的字段
    * */
    private int id;
    private String title;
    private String content;
    private String markdown;
    private String poster;
    private String date;
    private String time;
    private String address;
    private String tips;
    private int reader;

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    public void setLabel(String tips) {
        this.tips = tips;
    }

    public void setReader(int reader) {
        this.reader = reader;
    }

    public String getMarkdown() {
        return markdown;
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

    public String getPoster() {
        return poster;
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

    public String getLabel() {
        return tips;
    }

    public int getReader() {
        return reader;
    }
}
