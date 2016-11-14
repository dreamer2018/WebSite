package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Icommunity;
import org.xiyoulinux.idao.Idonate;
import org.xiyoulinux.model.Community;
import org.xiyoulinux.model.Donate;
import org.xiyoulinux.model.Signup;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by hutingting on 10/25/16.
 */

public class DonateDAO implements Idonate {


    @Override
    public boolean insert(Donate donate) {
        boolean rtu = false;
        if (donate == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into donate(wei_name,weixin,alipay_namep,alipay values(?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, donate.getWei_name());
            ps.setString(2, donate.getWeixin());
            ps.setString(3, donate.getAlipay_namep());
            ps.setString(4, donate.getAlipay());
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
            String sql = "delete from donate where id = ?";
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
    public boolean update(Donate donate) {
        boolean rtu = false;
        if (donate == null) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update donate set wei_name=?,weixin=?,alipay_namep=?,alipay= ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, donate.getWei_name());
            ps.setString(2, donate.getWeixin());
            ps.setString(3, donate.getAlipay_namep());
            ps.setString(4, donate.getAlipay());
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public Donate getDonateByID(int donate_id) {
        Donate rtu = null;
        if (donate_id <=0 ) {
            return rtu;
        }
        ResultSet rs = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select  set wei_name,weixin,alipay_namep,alipay, from community where id = ?";
            System.out.println(sql + donate_id);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, donate_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Donate();
                rtu.setWei_name(rs.getString("wei_name"));
                rtu.setWeixin(rs.getString("weixin"));
                rtu.setAlipay_namep(rs.getString("setAlipay_namep"));
                rtu.setAlipay(rs.getString("setAlipay"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }


       // @Override
//    public boolean check( String wei_name, String weixin,String alipay_namep,String alipay) {
//        if (wei_name == null || wei_name.equals("") || weixin == null || weixin.equals("")|| alipay_namep == null || alipay_namep.equals("")|| alipay == null || alipay.equals("")) {
//            return false;
//        }
//        DonateDAO donateDAO = new DonateDAO();
//        Donate donate = donateDAO.getDonateByID();
//        return null != donate&& weixin.getDonateByID().equals(donate);
//    }
}
