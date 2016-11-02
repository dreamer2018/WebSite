package org.xiyoulinux.idao;

import org.xiyoulinux.model.Links;

/**
 * Created by zhoupan on 10/31/16.
 */
public interface Ilinks {
    public boolean insert(Links links);

    public boolean delete(int link_id);

    public boolean update(Links links);

    public Links getLinksByID(int link_id);

    public Links getLinksByName(String name);
}
