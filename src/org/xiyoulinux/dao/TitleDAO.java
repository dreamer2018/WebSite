package org.xiyoulinux.dao;

import org.xiyoulinux.idao.Ititle;
import org.xiyoulinux.model.Title;
import org.xiyoulinux.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhoupan on 1/31/17.
 */
public class TitleDAO implements Ititle {

    @Override
    public boolean update(Title title) {
        boolean rtu = false;
        if (title == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update title set title = ?,subtitle = ?,url=? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title.getTitle());
            ps.setString(2, title.getSubtitle());
            ps.setString(3, title.getUrl());
            ps.setInt(4, title.getId());
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
    public Title getTitleById(int title_id) {
        if (title_id <= 0) {
            return null;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,title,subtitle,url from title where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, title_id);
            ResultSet rs = ps.executeQuery();
            //计算查询出的行数
            rs.last();
            if (rs.getRow() == 0) {
                return null;
            } else {
                rs.beforeFirst();
                Title title = new Title();
                while (rs.next()) {
                    title.setId(rs.getInt(1));
                    title.setTitle(rs.getString(2));
                    title.setSubtitle(rs.getString(3));
                    title.setUrl(rs.getString(4));
                }
                return title;
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
