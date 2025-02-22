
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SanPhamBanChay;
import repository.JdbcHelper;

public class SanPhamBanChayService {

    public List<SanPhamBanChay> showDataSanPhamBanChay() {
        List<SanPhamBanChay> list = new ArrayList<>();
        try {
            
            Connection conn = JdbcHelper.getConnection();
            String sql = "SELECT * FROM SanPhamBanChay";
            Statement St = conn.createStatement();
            ResultSet rs = St.executeQuery(sql);
            while (rs.next()) {
                SanPhamBanChay sanPhamBanChay = new SanPhamBanChay();
                sanPhamBanChay.setIdsp(rs.getInt("IDSanPham"));
                sanPhamBanChay.setMasp(rs.getString("MASANPHAM"));
                sanPhamBanChay.setTensp(rs.getString("TENSANPHAM"));
                sanPhamBanChay.setTongtien(rs.getInt("TongDoanhThu"));
                sanPhamBanChay.setSlban(rs.getInt("SLBAN"));
                list.add(sanPhamBanChay);
            }
            rs.close();
            conn.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamBanChayService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println(new SanPhamBanChayService().showDataSanPhamBanChay());
    }
    
}
