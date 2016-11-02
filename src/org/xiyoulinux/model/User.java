package org.xiyoulinux.model;

/**
 * Created by zhoupan on 10/25/16.
 */
public class User {
    private int id;
    private String name;
    private String passwd;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {

        this.passwd = passwd;
    }

    public void setName(String name) {
        this.name = name;

    }
}
