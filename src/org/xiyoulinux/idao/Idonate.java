package org.xiyoulinux.idao;

import org.xiyoulinux.model.Donate;

/**
 * Created by zhoupan on 10/31/16.
 */
public interface Idonate {
    public boolean insert(Donate donate);

    public boolean delete(int about_id);

    public boolean update(Donate donate);

    public Donate getDonateByID(int donate_id);
}
