package org.xiyoulinux.dao;

import com.sun.javafx.geom.AreaOp;
import org.xiyoulinux.idao.Ievents;
import org.xiyoulinux.model.Events;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TransferQueue;

/**
 * Created by zhoupan on 11/1/16.
 */


public class EventsDAO implements Ievents {

    @Override
    public boolean insert(Events events) {
        boolean rtu = false;
        if (null == events) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into events(title,content,poster_url,date,time,address,tips) values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, events.getTitle());
            ps.setString(2, events.getContent());
            ps.setString(3, events.getPoster_url());
            ps.setString(4, events.getDate());
            ps.setString(5, events.getTime());
            ps.setString(6, events.getAddress());
            ps.setString(7, events.getTips());
            rtu = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
        }
        return rtu;
    }

    @Override
    public boolean delete(int event_id) {
        boolean rtu = false;
        if (event_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;

        try {
            String sql = "delete from events where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, event_id);
            rtu = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
        }
        return rtu;
    }

    @Override
    public boolean update(Events events) {
        boolean rtu = false;
        if (events == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update events set title = ? and content = ? and poster_url= ? and date= ? and time = ? and address = ? and tips = ? where id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, events.getTitle());
            ps.setString(2, events.getContent());
            ps.setString(3, events.getPoster_url());
            ps.setString(4, events.getDate());
            ps.setString(5, events.getTime());
            ps.setString(6, events.getAddress());
            ps.setString(7, events.getTips());
            ps.setInt(8, events.getId());
            rtu = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
        }
        return rtu;
    }

    @Override
    public Events getEventsByID(int event_id) {
        if (event_id <= 0) {
            return null;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,title,content,poster_url,date,time,address,tips,reader from events where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, event_id);
            Events events = new Events();
            events.setId();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Events getEventsByTitle(String title) {
        return null;
    }
}
