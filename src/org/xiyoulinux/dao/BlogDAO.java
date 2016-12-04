package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Iblog;
import org.xiyoulinux.model.About;
import org.xiyoulinux.model.Blog;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 11/29/16.
 */

public class BlogDAO implements Iblog {


    /***
     分页相关的方法
     */
    /*每页显示的数据量*/
    private static final int PAGE_SIZE = 20;
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
    public boolean insert(Blog blog) {
        boolean rtu = false;
        if (blog == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into blog(title,author,date,time,summary,url) values(?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getAuthor());
            ps.setString(3, blog.getDate());
            ps.setString(4, blog.getTime());
            ps.setString(5, blog.getSummary());
            ps.setString(6, blog.getUrl());
            ps.executeUpdate();
            rtu = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return rtu;
        }
    }

    @Override
    public boolean delete(int blog_id) {
        boolean rtu = false;
        if (blog_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from blog where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, blog_id);
            ps.executeUpdate();
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return rtu;
        }
    }

    @Override
    public boolean update(Blog blog) {
        boolean rtu = false;
        if (blog == null) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update blog set title=?,author=?,date=?,time=?,summary=?,url=? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getAuthor());
            ps.setString(3, blog.getDate());
            ps.setString(4, blog.getTime());
            ps.setString(5, blog.getSummary());
            ps.setString(6, blog.getUrl());
            ps.setInt(7, blog.getId());
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public Blog getBlogByID(int blog_id) {
        Blog rtu = null;
        if (blog_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select id,title,author,date,time,summary,url,status from blog where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, blog_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Blog();
                rtu.setId(rs.getInt("id"));
                rtu.setTitle(rs.getString("title"));
                rtu.setAuthor(rs.getString("author"));
                rtu.setDate(rs.getString("date"));
                rtu.setTime(rs.getString("time"));
                rtu.setSummary(rs.getString("summary"));
                rtu.setUrl(rs.getString("url"));
                rtu.setStatus(rs.getInt("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public ArrayList<Blog> getBlogByTitle(String title) {
        if (null == title || title.equals("")) {
            return null;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ArrayList<Blog> list = new ArrayList<>();

        try {
            String sql = "select id,title,author,date,time,summary,url,status from blog where title = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt(1));
                blog.setTitle(rs.getString(2));
                blog.setAuthor(rs.getString(3));
                blog.setDate(rs.getString(4));
                blog.setTime(rs.getString(5));
                blog.setSummary(rs.getString(6));
                blog.setUrl(rs.getString(7));
                blog.setStatus(rs.getInt(8));
                list.add(blog);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
        }
        return list;
    }
    @Override
    public ArrayList getBlogByPage(int page, String title) {
        currentPage = page;
        ArrayList<Blog> list = new ArrayList<>();
        // 若未指定title,则默认全查
        if (null == title || title.equals("")) {
            title = "";
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(id) as AllRecord from blog where title like ?";
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


            String sql2 = "select * from blog where title like ? limit ?,?";
            ps = conn.prepareStatement(sql2);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, PAGE_SIZE * (currentPage - 1));
            ps.setInt(3, PAGE_SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt(1));
                blog.setTitle(rs.getString(2));
                blog.setAuthor(rs.getString(3));
                blog.setDate(rs.getString(4));
                blog.setTime(rs.getString(5));
                blog.setSummary(rs.getString(6));
                blog.setUrl(rs.getString(7));
                blog.setStatus(rs.getInt(8));
                // 将该用户信息插入列表
                list.add(blog);
            }
        } catch (SQLException e) {
            System.out.println(allPageCount+" "+currentPage+" ");
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    @Override
    public ArrayList<Blog> getBlogByNumber(int number) {
        ArrayList<Blog> list = new ArrayList<Blog>();
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,title,author,date,time,summary,url,status from blog order by id desc limit ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, number);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt(1));
                blog.setTitle(rs.getString(2));
                blog.setAuthor(rs.getString(3));
                blog.setDate(rs.getString(4));
                blog.setTime(rs.getString(5));
                blog.setSummary(rs.getString(6));
                blog.setUrl(rs.getString(7));
                blog.setStatus(rs.getInt(8));
                list.add(blog);
            }
            return list;
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
