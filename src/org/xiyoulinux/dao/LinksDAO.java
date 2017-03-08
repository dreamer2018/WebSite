package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Ilinks;
import org.xiyoulinux.model.Links;
import org.xiyoulinux.model.User;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jiayiyang on 2016/11/11 0011.
 */
public class LinksDAO implements Ilinks {

    @Override
    public boolean insert(Links links) {
        boolean rtu = false;
        if (links == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into links(id,name,url) values(?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, links.getId());
            ps.setString(2, links.getName());
            ps.setString(3,links.getUrl());
            ps.executeUpdate();
            rtu = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return rtu;
        }
    }

    @Override
    public boolean update(Links links) {
        boolean rtu = false;
        if (links == null) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update limks set id= ? , name = ? ,url = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,links.getId());
            ps.setString(2, links.getName());
            ps.setString(3, links.getUrl());
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public boolean delete(int link_id) {
        boolean rtu = false;
        if (link_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from links where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, link_id);
            ps.executeUpdate();
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return rtu;
        }
    }


    public Links getLinksByID(int link_id){
        Links rtu = null;
        if (link_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select id,name,uurl from links where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, link_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Links();
                rtu.setId(rs.getInt("id"));
                rtu.setName(rs.getString("name"));
                rtu.setUrl(rs.getString("url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    public Links getLinksByName(String name){
        Links rtu = null;
        if (name == null || name.equals("")) {
            return rtu;
        }
        ResultSet rs = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,name,url from links where name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Links();
                rtu.setName(rs.getString("name"));
                rtu.setId(rs.getInt("id"));
                rtu.setUrl(rs.getString("url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

}
