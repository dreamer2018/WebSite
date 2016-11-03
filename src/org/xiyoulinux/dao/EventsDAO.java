package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Ievents;
import org.xiyoulinux.model.Events;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.*;


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
            ps.executeUpdate();
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
            String sql = "update events set title = ?,content = ?,poster_url= ?,date= ?,time = ?,address = ?,tips = ? where id = ?";
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
//            String sql = "select id,content,poster_url,date,time,address,tips,reader from events where title = ?";
            String sql = "select * from events where title = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, event_id);
            Events events = new Events();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                events.setId(rs.getInt(1));
                events.setTitle(rs.getString(2));
                events.setContent(rs.getString(3));
                events.setPoster_url(rs.getString(4));
                events.setDate(rs.getString(5));
                events.setTime(rs.getString(6));
                events.setAddress(rs.getString(7));
                events.setTips(rs.getString(8));
                events.setReader(rs.getInt(9));
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
        }
        return null;
    }

    @Override
    public Events getEventsByTitle(String title) {
        if (null == title || title.equals("")) {
            return null;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;

        try {
            String sql = "select * from events where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            Events events = new Events();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                events.setId(rs.getInt(1));
                events.setTitle(rs.getString(2));
                events.setContent(rs.getString(3));
                events.setPoster_url(rs.getString(4));
                events.setDate(rs.getString(5));
                events.setTime(rs.getString(6));
                events.setAddress(rs.getString(7));
                events.setTips(rs.getString(8));
                events.setReader(rs.getInt(9));
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
        }
        return null;
    }
}
