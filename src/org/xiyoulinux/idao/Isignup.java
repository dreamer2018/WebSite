package org.xiyoulinux.idao;

import org.xiyoulinux.model.Signup;

/**
 * Created by zhoupan on 10/31/16.
 */
public interface Isignup {
    public boolean insert(Signup signup);

    public boolean delete(int signup_id);

    public boolean update(Signup signup);

    public Signup getSignupByID(int signup_id);

    public Signup getSignupByName(String name);

    public Signup getSignupByEventsID(int events_id);
}
