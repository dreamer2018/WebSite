package org.xiyoulinux.idao;

import org.xiyoulinux.model.About;

/**
 * Created by zhoupan on 10/31/16.
 */

public interface Iabout {
    public boolean insert(About about);

    public boolean delete(int about_id);

    public boolean update(About about);

    public About getAboutByID(int about_id);

    public About getAboutByTitle(String title);
}
