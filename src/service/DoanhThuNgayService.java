
package service;
import java.util.List;
import model.DoanhThuNgay;
import repository.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

public class DoanhThuNgayService {

    public List<DoanhThuNgay> showDataDoanhThuNgay() throws SQLException{
        List<DoanhThuNgay> list = new ArrayList<>();
       Connection conn = JdbcHelper.getConnection();
        String sql = "SELECT * FROM DoanhThuNgay";
        Statement St = conn.createStatement();
        
        ResultSet rs = St.executeQuery(sql);
        while (rs.next()) {
            DoanhThuNgay doanhThuNgay = new DoanhThuNgay();
               doanhThuNgay.setDoanhthungay(rs.getInt("DoanhThuNgay"));
                list.add(doanhThuNgay);
        }
        rs.close();
        conn.close();
        return list;
    }
    public List<DoanhThuNgay> timTheoNgay(int ngay) throws SQLException{
        List<DoanhThuNgay> list = new ArrayList<>();
       Connection conn = JdbcHelper.getConnection();
        String sql = "SELECT SUM(TongTien * 1) AS DoanhThuNgay, COUNT(ID) AS TongHoaDon FROM HoaDon WHERE DAY(NgayTao) = ?;";
        PreparedStatement preSt = conn.prepareCall(sql);
        preSt.setInt(1,ngay);
        ResultSet rs = preSt.executeQuery();
        while (rs.next()) {
            DoanhThuNgay doanhThuNgay = new DoanhThuNgay();
            doanhThuNgay.setDoanhthungay(rs.getInt("DoanhThuNgay"));
            doanhThuNgay.setHoadon(rs.getInt("TongHoaDon"));
                list.add(doanhThuNgay);
        }
        rs.close();
        preSt.close();
        conn.close();
        return list;
    }
}
