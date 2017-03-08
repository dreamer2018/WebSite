package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Icommunity;
import org.xiyoulinux.model.Community;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by hutingting on 10/25/16.
 */

public class CommunityDAO implements Icommunity {


    @Override
    public boolean insert(Community community) {
        boolean rtu = false;
        if (community == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into community(id,name,url) values(?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, community.getName());
            ps.setString(2, community.getUrl());
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
    public boolean delete(int community_id) {
        boolean rtu = false;
        if (community_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from community where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, community_id);
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
    public boolean update(Community community) {
        boolean rtu = false;
        if (community == null) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update community set name= ? , passwd = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, community.getName());
            ps.setString(2, community.getUrl());
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public Community getCommunityByName(String community_name) {
        Community rtu = null;
        if (community_name == null || community_name.equals("")) {
            return rtu;
        }
        ResultSet rs = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,name, from community where name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, community_name);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Community();
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

    @Override
    public Community getCommunityByID(int community_id) {
        Community rtu = null;
        if (community_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select id,name,url from community where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, community_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Community();
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
}
