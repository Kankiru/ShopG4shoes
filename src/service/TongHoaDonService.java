
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.TongHoaDon;
import repository.JdbcHelper;

public class TongHoaDonService {
  

    public List<TongHoaDon> showDataTongDonHang() throws SQLException{
        List<TongHoaDon> list = new ArrayList<>();
        Connection conn = JdbcHelper.getConnection();
        String sql = "SELECT * FROM TongDonHang";
        Statement St = conn.createStatement();
        ResultSet rs = St.executeQuery(sql);
        while (rs.next()) {
            TongHoaDon tongHoaDon = new TongHoaDon();
               tongHoaDon.setTonghoadon(rs.getInt("TongHoaDon"));
                list.add(tongHoaDon);
        }
        rs.close();
        conn.close();
        return list;
    }
}
