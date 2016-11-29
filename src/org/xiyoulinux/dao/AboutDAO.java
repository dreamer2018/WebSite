package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Iabout;
import org.xiyoulinux.model.About;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by xiaozan on 2016/11/7.
 */

public class AboutDAO implements Iabout {

    /***
     分页相关的方法
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
    public boolean insert(About about) {
        boolean rtu = false;
        if (about == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into about(title,content,picture) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, about.getTitle());
            ps.setString(2, about.getContent());
            ps.setString(3, about.getPicture_url());
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
    public boolean delete(int about_id) {
        boolean rtu = false;
        if (about_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delect from about where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, about_id);
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
    public boolean update(About about) {
        boolean rtu = false;
        if (about == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update about set title=?,content=? ,picture=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, about.getTitle());
            ps.setString(2, about.getContent());
            ps.setString(3, about.getPicture_url());
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
    public About getAboutByID(int about_id) {
        About rtu = null;
        if (about_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select id,title,content,picture from about where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, about_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new About();
                rtu.setId(rs.getInt("id"));
                rtu.setTitle(rs.getString("title"));
                rtu.setContent(rs.getString("content"));
                rtu.setPicture_url(rs.getString("picture"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public About getAboutByTitle(String title) {
        if (null == title || title.equals("")) {
            return null;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;

        try {
            String sql = "select id,title,content,picture from events where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            rs.last();
            if (rs.getRow() == 0) {
                return null;
            } else {
                rs.beforeFirst();
                About about = new About();
                while (rs.next()) {
                    about.setId(rs.getInt(1));
                    about.setTitle(rs.getString(2));
                    about.setContent(rs.getString(3));
                    about.setPicture_url(rs.getString(4));
                }
                return about;
            }
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
    public ArrayList<About> getAboutByPage(int page, String title) {
        currentPage = page;
        ArrayList<About> list = new ArrayList<About>();
        // 若未指定title,则默认全查
        if (null == title || title.equals("")) {
            title = "";
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(id) as AllRecord from about where title like ?";
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
            String sql2 = "select * from about where title like ? limit ?,?";
            ps = conn.prepareStatement(sql2);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, PAGE_SIZE * (currentPage - 1));
            ps.setInt(3, PAGE_SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                About about = new About();
                about.setId(rs.getInt(1));
                about.setTitle(rs.getString(2));
                about.setContent(rs.getString(3));
                about.setPicture_url(rs.getString(4));
                // 将该用户信息插入列表
                list.add(about);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }
}
