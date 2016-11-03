package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Ievents;
import org.xiyoulinux.model.Events;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhoupan on 11/1/16.
 */


public class EventsDAO implements Ievents {

    /**
     * 分页要使用的一些东西
     */
    /*每页显示的数据量*/
    private static final int PAGE_SIZE = 10;
    /*数据库中的数据量*/
    private int allCount;
    /*总的分页数*/
    private int allPageCount;
    /*当前页面*/
    private int currentPage;

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getAllPageCount() {
        return allPageCount;
    }

    public void setAllPageCount(int allPageCount) {
        this.allPageCount = allPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


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

    @Override
    public ArrayList<Events> getEventsByPage(int page, String title) {
        currentPage = page;
        ArrayList<Events> list = new ArrayList<Events>();

        // 若未指定title,则默认全查
        if (null == title || title.equals("")) {
            title = "";
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(events) as AllRecord from Events where title like ?";
            conn = ConnectionManager.getInstance().getConnection();
            ps = conn.prepareStatement(sql1);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();
            if (rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            ps.close();
            // 记算总页数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;
            // 如果当前页数大于总页数,则赋值为总页数
            if (allPageCount > 0 && currentPage > allPageCount) {
                currentPage = allPageCount;
            }
            // 获取第currentPage页数据
            String sql2 = "select * from Events where title like ? limit ?,?";
            ps = conn.prepareStatement(sql2);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, PAGE_SIZE * (currentPage - 1));
            ps.setInt(3, PAGE_SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events events = new Events();
                events.setId(rs.getInt(1));
                events.setTitle(rs.getString(2));
                events.setContent(rs.getString(3));
                events.setPoster_url(rs.getString(4));
                events.setDate(rs.getString(5));
                events.setTime(rs.getString(6));
                events.setAddress(rs.getString(7));
                events.setTips(rs.getString(8));
                events.setReader(rs.getInt(9));
                // 将该用户信息插入列表
                list.add(events);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }
}
