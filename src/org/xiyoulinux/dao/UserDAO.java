package org.xiyoulinux.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.xiyoulinux.idao.Iuser;
import org.xiyoulinux.model.User;
import org.xiyoulinux.util.ConnectionManager;

/**
 * Created by zhoupan on 10/25/16.
 */

public class UserDAO implements Iuser {


    @Override
    public boolean insert(User user) {
        boolean rtu = false;
        if (user == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into user(name,passwd) values(?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPasswd());
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
    public boolean delete(int user_id) {
        boolean rtu = false;
        if (user_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
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
    public boolean update(User user) {
        boolean rtu = false;
        if (user == null) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update user set name= ? , passwd = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPasswd());
            ps.setInt(3, user.getId());
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public User getUserByName(String user_name) {
        User rtu = null;
        if (user_name == null || user_name.equals("")) {
            return rtu;
        }
        ResultSet rs = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,name,passwd from user where name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user_name);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new User();
                rtu.setId(rs.getInt("id"));
                rtu.setName(rs.getString("name"));
                rtu.setPasswd(rs.getString("passwd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public User getUserByID(int user_id) {
        User rtu = null;
        if (user_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select id,name,passwd from user where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new User();
                rtu.setId(rs.getInt("id"));
                rtu.setName(rs.getString("name"));
                rtu.setPasswd(rs.getString("passwd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public boolean check(String name, String passwd) {
        if (name == null || name.equals("") || passwd == null || passwd.equals("")) {
            return false;
        }
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByName(name);
        return null != user && user.getPasswd().equals(passwd);
    }
}
