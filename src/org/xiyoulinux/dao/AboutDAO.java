package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Iabout;
import org.xiyoulinux.model.About;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xiaozan on 2016/11/7.
 */

public class AboutDAO implements Iabout{
    @Override
    public boolean insert(About about) {
        boolean rtu=false;
        if(about==null){
            return rtu;
        }
        //获取Connection
        Connection conn= ConnectionManager.getInstance().getConnection();
        PreparedStatement ps=null;
        try{
            String sql="insert into about(title,content,picture) VALUES (?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,about.getTitle());
            ps.setString(2,about.getContent());
            ps.setString(3,about.getPicture_url());
            ps.executeUpdate();
            rtu=true;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionManager.close(null,ps,conn);
        }
        return rtu;
    }

    @Override
    public boolean delete(int about_id) {
        boolean rtu=false;
        if(about_id==0){
            return rtu;
        }
        //获取Connection
        Connection conn=ConnectionManager.getInstance().getConnection();
        PreparedStatement ps=null;
        try {
            String sql="delect from about where id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,about_id);
            ps.executeUpdate();
            rtu=true;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionManager.close(null,ps,conn);
        }
        return rtu;
    }

    @Override
    public boolean update(About about) {
        boolean rtu=false;
        if(about==null){
            return rtu;
        }
        //获取Connection
        Connection conn=ConnectionManager.getInstance().getConnection();
        PreparedStatement ps=null;
        try {
            String sql="update about set title=?,content=? ,picture=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,about.getTitle());
            ps.setString(2,about.getContent());
            ps.setString(3,about.getPicture_url());
            rtu=true;

        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionManager.close(null,ps,conn);
        }
        return rtu;
    }

    @Override
    public About getAboutByID(int about_id) {
        About rtu=null;
        if(about_id<=0){
            return rtu;
        }
        Connection conn=ConnectionManager.getInstance().getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql = "select id,title,content,picture from about where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,about_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu=new About();
                rtu.setId(rs.getInt("id"));
                rtu.setTitle(rs.getString("title"));
                rtu.setContent(rs.getString("content"));
                rtu.setPicture_url(rs.getString("picture"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
            if(rs.getRow() == 0){
                return null;
            }else {
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
}
