package org.xiyoulinux.idao;

import org.xiyoulinux.model.Community;

/**
 * Created by zhoupan on 10/31/16.
 */
public interface Icommunity {
    public boolean insert(Community community);

    public boolean delete(int community_id);

    public boolean update(Community community);

    public Community getCommunityByID(int community_id);

    public Community getCommunityByName(String title);
}
