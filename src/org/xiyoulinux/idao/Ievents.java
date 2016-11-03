package org.xiyoulinux.idao;

import org.xiyoulinux.model.Events;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhoupan on 10/31/16.
 */
public interface Ievents {
    public boolean insert(Events events);

    public boolean delete(int event_id);

    public boolean update(Events events);

    public Events getEventsByID(int event_id);

    public Events getEventsByTitle(String title);

    public List<Events> getEventsByPage(int page,String title);
}
