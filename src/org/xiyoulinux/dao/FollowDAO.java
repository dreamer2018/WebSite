package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Ifollow;
import org.xiyoulinux.model.Follow;
import org.xiyoulinux.model.Signup;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jiayiyang on 2016/11/11 0011.
 */
public class FollowDAO implements Ifollow {

    @Override
    public boolean insert(Follow follow) {
        boolean rtu = false;
        if (follow == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into follow (id,weixin) values(?,?)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, follow.getId());
            ps.setString(2, follow.getWenxin());
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
    public boolean delete(int about_id) {
        boolean rtu = false;
        if (about_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from follow where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, about_id);
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
    public boolean update(Follow follow) {
        boolean rtu = false;
        if (follow == null) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update follow set id= ? , weixin = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,follow.getId());
            ps.setString(2,follow.getWenxin());
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }


    @Override
    public Follow getFollowByID(int follow_id) {
        Follow rtu = null;
        if (follow_id <= 0) {
            return rtu;
        }
        ResultSet rs = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,weixin from follow where follow_id = ?";
            System.out.println(sql + follow_id);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,follow_id );
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Follow();
                rtu.setId(rs.getInt("id"));
                rtu.setWenxin(rs.getString("weixin"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }


}
