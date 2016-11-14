package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Iaddress;
import org.xiyoulinux.model.Address;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xiaozan on 2016/11/13.
 */
public class AddressDAO implements Iaddress{
    @Override
    public boolean insert(Address address) {
        boolean rtu = false;
        if (address == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into address(address,postcode) values(?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, address.getAddress());
            ps.setString(2, address.getPostcode());
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
    public boolean delete(int address_id) {
        boolean rtu = false;
        if (address_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from address where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, address_id);
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
    public boolean update(Address address) {
        boolean rtu = false;
        if (address == null) {
            return rtu;
        }
        //获取connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update address set address= ? , postcode = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, address.getAddress());
            ps.setString(2, address.getPostcode());
            rtu = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public Address getAddressByID(int address_id) {
        Address rtu = null;
        if (address_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select id,name,passwd from user where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, address_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Address();
                rtu.setId(rs.getInt("id"));
                rtu.setAddress(rs.getString("address"));
                rtu.setPostcode(rs.getString("postcode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }
}
