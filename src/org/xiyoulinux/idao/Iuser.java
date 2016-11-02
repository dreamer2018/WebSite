package org.xiyoulinux.idao;

import org.xiyoulinux.model.User;

/**
 * Created by zhoupan on 10/25/16.
 */
public interface Iuser {
    public boolean insert(User user);

    public boolean delete(int user_id);

    public boolean update(User user);

    public User getUserByName(String user_name);

    public User getUserByID(int user_id);

    public boolean check(String name,String passwd);
}