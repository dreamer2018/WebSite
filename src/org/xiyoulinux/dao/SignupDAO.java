package org.xiyoulinux.dao;

import java.lang.String;
import java.sql.*;


import org.xiyoulinux.idao.Isignup;
import org.xiyoulinux.model.Signup;
import org.xiyoulinux.util.ConnectionManager;

/**
 * Created by yaolu on 2016/11/11 0011.
 */
public class SignupDAO implements Isignup {


    @Override
    public boolean insert(Signup signup) {
        boolean rtu = false;
        if (signup == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into signup (id,events_id,name,email) values(?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, signup.getId());
            ps.setInt(2, signup.getEvents_id());
           // ps.setString(3, signup.getName());
            ps.setString(4, signup.getEmail());
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
    public boolean delete(int sign_id) {
        boolean rtu = false;
        if (sign_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from signup where sign_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sign_id);
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
    public boolean update(Signup signup) {
        boolean rtu = false;
        if (signup == null) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update signup set events_id = ? ,name= ? , email = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, signup.getEvents_id());
            ps.setString(2, signup.getName());
            ps.setString(3, signup.getEmail());
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }


    @Override
    public Signup getSignupByID(int signup_id) {
        Signup rtu = null;
        if (signup_id <= 0) {
            return rtu;
        }
        ResultSet rs = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,events_id,name,email from user where signup_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, signup_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Signup();
                rtu.setId(rs.getInt("id"));
                rtu.setEvents_id(rs.getInt("events_id"));
                rtu.setName(rs.getString("name"));
                rtu.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public Signup getSignupByName(String name) {
        Signup rtu = null;
        if (name == null || name.equals("")) {
            return rtu;
        }
        ResultSet rs = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,evens_id,name,emil from user where name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Signup();
                rtu.setName(rs.getString("name"));
                rtu.setId(rs.getInt("id"));
                rtu.setEvents_id(rs.getInt("events_id"));

                rtu.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }

    }

    @Override
    public Signup getSignupByEventsID(int events_id) {
        Signup rtu = null;
        if (events_id <= 0) {
            return rtu;
        }
        ResultSet rs = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,events_id,name,email from user where events_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, events_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Signup();
                rtu.setEvents_id(rs.getInt("events_id"));
                rtu.setId(rs.getInt("id"));
                rtu.setName(rs.getString("name"));
                rtu.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    public static void main(String[] args) {
        SignupDAO a=new SignupDAO();
        Signup b=new Signup();
        b.setEmail("32335322");
        b.setEvents_id(1);
        b.setId(1);
        b.setName("xiaom");
        a.insert(b);
    }
}
