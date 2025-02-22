package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.NhanVien;
import repository.JdbcHelper;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KhachHang;
import model.Voucher;

public class HoaDonService extends SellingApplicationImpl<HoaDon, Integer> {

    @Override
    public void insert(HoaDon entity) {
        String sql = """
                        INSERT INTO [dbo].[HoaDon]
                                   ([NgayTao]
                                   ,[TongTien]
                                   ,[TrangThai]
                                   ,[ID_NhanVien])
                             VALUES(?, ?, ?, ?)
                     """;
        JdbcHelper.update(sql,
                entity.getNgayTao(),
                entity.getTongTien(),
                entity.getTrangThai(),
                entity.getIdNV()
        );
    }

    @Override
    public void update(HoaDon hd) {
        String sql = """
                     UPDATE [dbo].[HoaDon]
                        SET [TongTien] = ?
                           ,[TrangThai] = ?
                           ,[ID_KhachHang] = ?
                      WHERE ID = ?
                     """;
        JdbcHelper.update(sql,
                hd.getTongTien(),
                hd.getTrangThai(),
                hd.getIdKH(),
                hd.getId()
        );
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public HoaDon selectById(Integer id) {
        String sql = """
                     SELECT 
                         hd.ID, 
                         hd.Ma, 
                         nv.Ma AS MaNV,
                         nv.Ten AS TenNV,
                         hd.NgayTao, 
                         hd.TongTien, 
                         hd.TrangThai
                     FROM 
                         dbo.HoaDon hd
                     JOIN 
                         dbo.NhanVien nv ON hd.ID_NhanVien = nv.ID
                     WHERE hd.ID = ?
                     """;
        List<HoaDon> list = this.selectBySql(sql, id);
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    public HoaDon selectByMa(String ma) {
        String sql = """
                     SELECT 
                         hd.ID, 
                         hd.Ma, 
                         nv.Ma AS MaNV,
                         nv.Ten AS TenNV,
                         hd.NgayTao, 
                         hd.TongTien, 
                         hd.TrangThai
                     FROM 
                         dbo.HoaDon hd
                     JOIN 
                         dbo.NhanVien nv ON hd.ID_NhanVien = nv.ID
                     WHERE hd.Ma LIKE ?
                     """;
        List<HoaDon> list = this.selectBySql(sql, "%" + ma + "%");
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDon> selectAll() {
    List<HoaDon> list = new ArrayList<>();

    try {
        String sql = """
                     SELECT 
                          hd.ID, 
                          hd.Ma,                             
                          nv.Ma AS MaNhanVien,
                          nv.Ten AS TenNhanVien,
                          hd.NgayTao, 
                          hd.TongTien, 
                          hd.TrangThai,
                          kh.ID AS IDKhachHang,
                          kh.SDT,
                          kh.Ten AS TenKhachHang
                      FROM 
                          dbo.HoaDon hd
                      JOIN 
                          dbo.NhanVien nv ON hd.ID_NhanVien = nv.ID
                      JOIN 
                          dbo.KhachHang kh ON kh.ID = hd.ID_KhachHang
                      WHERE 
                          hd.TrangThai = 1
                 """;

        Connection conn = JdbcHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();

            hd.setId(rs.getInt("ID"));
            hd.setMa(rs.getString("Ma"));
            hd.setNv(new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"))); 
            hd.setNgayTao(rs.getDate("NgayTao"));
            hd.setTongTien(rs.getDouble("TongTien"));
            hd.setTrangThai(rs.getInt("TrangThai"));
            hd.setKh(new KhachHang(rs.getInt("IDKhachHang"), rs.getString("SDT"), rs.getString("TenKhachHang"))); 

            list.add(hd);
        }

        // Đóng tài nguyên để tránh rò rỉ bộ nhớ
        rs.close();
        ps.close();
        conn.close();
    } catch (SQLException ex) {
        Logger.getLogger(HoaDonService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}


    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();

        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDon hd = new HoaDon();

                hd.setId(rs.getInt("ID"));
                hd.setMa(rs.getString("Ma"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                hd.setNv(new NhanVien(rs.getString("MaNV"), rs.getString("TenNV")));

                list.add(hd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

//    public List<HoaDon> selectByStatus() {
//        String sql = """
//                     SELECT 
//                         hd.ID, 
//                         hd.Ma, 
//                         nv.Ma AS MaNV,
//                         nv.Ten AS TenNV,
//                         hd.NgayTao, 
//                         hd.TongTien, 
//                         hd.TrangThai
//                     FROM 
//                         dbo.HoaDon hd
//                     JOIN 
//                         dbo.NhanVien nv ON hd.ID_NhanVien = nv.ID
//                     WHERE hd.TrangThai = CAST(0 AS bit)
//                     """;
//        return this.selectBySql(sql);
//    }
//
    public List<HoaDon> selectLSGD() {
        List<HoaDon> list = new ArrayList<>();
        String sql = """
                     SELECT HoaDon.Ma, NhanVien.Ten, NgayTao, TongTien, HOADON.TrangThai   
                     	FROM 
                            HoaDon 
                     	JOIN 
                            NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID
                     """;
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMa(rs.getString(1));
                hd.setNv(new NhanVien(rs.getString("Ma"), rs.getString("Ten")));
                hd.setNgayTao(rs.getDate(3));
                hd.setTongTien(rs.getDouble(4));
                hd.setTrangThai(rs.getInt(5));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //đếm số lượng hoá đơn 
    public int count() {
        int totalCount = 0;
        Connection conn = JdbcHelper.getConnection();
        try {
            String sql = " SELECT COUNT(*) AS SOLUONG FROM HOADON";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                totalCount = rs.getInt("SOLUONG");
            }
            conn.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCount;
    }

    //select phân trang
    public List<HoaDon> paging(int page, int limit) {
        List<HoaDon> list = new ArrayList<>();
        String sql = """
                         SELECT 
                             hd.ID, 
                             hd.Ma,                             
                             nv.Ten,
                             hd.NgayTao, 
                             hd.TongTien, 
                             hd.TrangThai,
                             kh.SDT,
                             vc.Ten as tenvc
                         FROM 
                             dbo.HoaDon hd
                         JOIN 
                             dbo.NhanVien nv ON hd.ID_NhanVien = nv.ID
                         JOIN 
                             dbo.KhachHang kh on kh.id = hd.ID_KhachHang
                         JOIN 
                         	dbo.Voucher vc on vc.ID = hd.ID_Voucher
                         WHERE 
                             hd.TrangThai IN (1)
                         ORDER BY
                             hd.ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                     """;
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, (page - 1) * limit);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();

                hd.setId(rs.getInt("ID"));
                hd.setMa(rs.getString("Ma"));
                hd.setNv(new NhanVien(rs.getString("Ma"), rs.getString("Ten")));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                hd.setKh(new KhachHang(rs.getInt("ID"), rs.getString("SDT")));
                hd.setVc(new Voucher(rs.getInt("ID"), rs.getString("tenvc")));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //tìm theo khoảng ngày
    public List<HoaDon> searchDate(String startDate, String endDate) {
        List<HoaDon> list = new ArrayList<>();
        try {
            String sql = """
                     SELECT 
                       hd.ID, 
                       hd.Ma,                             
                       nv.Ten,
                       hd.NgayTao, 
                       hd.TongTien, 
                       hd.TrangThai,
                       kh.SDT
                   FROM 
                       dbo.HoaDon hd
                   JOIN 
                       dbo.NhanVien nv ON hd.ID_NhanVien = nv.ID
                   JOIN 
                       dbo.KhachHang kh on kh.id = hd.ID_KhachHang
                     WHERE ( hd.TrangThai = 1) and hd.NgayTao BETWEEN ? AND ?
                  
                        
                                     """;
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("ID"));
                hd.setMa(rs.getString("Ma"));
                hd.setNv(new NhanVien(rs.getString("Ma"), rs.getString("Ten")));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                hd.setKh(new KhachHang(rs.getInt("ID"), rs.getString("SDT")));

                list.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    //tìm theo mã hoá đơn và theo tên nhanvien
    public List<HoaDon> searchHD(String keyWord) {
        List<HoaDon> list = new ArrayList<>();

        try {
            String sql = """
                         SELECT 
                              hd.ID, 
                              hd.Ma,                             
                              nv.Ten,
                              hd.NgayTao, 
                              hd.TongTien, 
                              hd.TrangThai,
                              kh.SDT
                          FROM 
                              dbo.HoaDon hd
                          JOIN 
                              dbo.NhanVien nv ON hd.ID_NhanVien = nv.ID
                          JOIN 
                              dbo.KhachHang kh on kh.id = hd.ID_KhachHang
                          WHERE 
                              ( hd.TrangThai = 1) and (hd.Ma LIKE ? OR nv.Ten LIKE ? OR kh.SDT LIKE ?)
                        
                     """;
//            String sql = """
//                         SELECT
//                             HoaDon.Ma, NhanVien.Ten, NgayTao, TongTien, HOADON.TrangThai
//                             FROM
//                                 HoaDon
//                             JOIN
//                                 NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID
//                            WHERE (HoaDon.Ma LIKE ? OR NhanVien.Ten LIKE ?) and (HoaDon.TrangThai = 1)
//                         """;
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyWord + "%");
            ps.setString(2, "%" + keyWord + "%");
            ps.setString(3, "%" + keyWord + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();

                hd.setId(rs.getInt(1));
                hd.setMa(rs.getString(2));
                hd.setNv(new NhanVien(rs.getString("Ma"), rs.getString("Ten")));
                hd.setNgayTao(rs.getDate(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setTrangThai(rs.getInt(6));
                hd.setKh(new KhachHang(rs.getInt("ID"), rs.getString("SDT")));

                list.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<HoaDon> selectByStatus() {
        String sql = """
                     SELECT 
                          hd.ID, 
                          hd.Ma, 
                          nv.Ma AS MaNV,
                          nv.Ten AS TenNV,
                          hd.NgayTao, 
                          hd.TongTien, 
                          hd.TrangThai
                      FROM 
                          dbo.HoaDon hd
                      JOIN 
                          dbo.NhanVien nv ON hd.ID_NhanVien = nv.ID
                      WHERE hd.TrangThai = 2
                     
                     """;
        return this.selectBySql(sql);
    }
}
