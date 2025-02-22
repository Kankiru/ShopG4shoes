package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.DanhMuc;
import model.MauSac;
import model.NhanVien;
import model.SanPham;
import model.SanPhamChiTiet;
import model.Size;
import model.ThuongHieu;
import repository.JdbcHelper;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamChiTietService extends SellingApplicationImpl<SanPhamChiTiet, Integer> {

    @Override
    public void insert(SanPhamChiTiet entity) {
        String sql = """
                    INSERT INTO [dbo].[SanPhamChiTiet]
                                ([Gia]
                                ,[SoLuong]
                                ,[MaSP]
                                ,[TrangThai]
                                ,[ID_SP]
                                ,[ID_Size]
                                ,[ID_MauSac]
                                ,[ID_ChatLieu])
                          VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                     """;

        JdbcHelper.update(sql,
                entity.getGia(),
                entity.getSoLuong(),
                entity.getMaSP(),
                entity.isTrangThai(),
                entity.getId_sanPham(),
                entity.getId_size(),
                entity.getId_mauSac(),
                entity.getId_chatLieu());
    }

    @Override
    public void update(SanPhamChiTiet entity) {
        String sql = """
                     UPDATE [dbo].[SanPhamChiTiet]
                        SET [Gia] = ?
                           ,[SoLuong] = ?
                           ,[MaSP] = ?
                           ,[TrangThai] = ?
                           ,[ID_SP] = ?
                           ,[ID_Size] = ?
                           ,[ID_MauSac] = ?
                           ,[ID_ChatLieu] = ?
                      WHERE ID = ?
                     """;

        JdbcHelper.update(sql,
                entity.getGia(),
                entity.getSoLuong(),
                entity.getMaSP(),
                entity.isTrangThai(),
                entity.getId_sanPham(),
                entity.getId_size(),
                entity.getId_mauSac(),
                entity.getId_chatLieu(),
                entity.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = """
                     DELETE FROM [dbo].[SanPhamChiTiet]
                           WHERE ID = ?
                     """;

        JdbcHelper.update(sql, id);
    }

    public void updateSoLuong(SanPhamChiTiet entity) {
        String sql = """
                     UPDATE [dbo].[SanPhamChiTiet]
                        SET SoLuong = ?
                     ,[TrangThai] = ?
                      WHERE ID = ?
                     """;

        JdbcHelper.update(sql,
                entity.getSoLuong(),
                entity.isTrangThai(),
                entity.getId());
    }

    @Override
    public SanPhamChiTiet selectById(Integer id) {

        String sql = """
                    SELECT 
                            spct.ID,  
                            spct.MaSP, 
                            nv.Ma AS MaNV,
                            nv.Ten AS TenNV,
                            sp.Ten AS TenSP, 
                            spct.Gia, 
                            spct.SoLuong, 
                            sz.Ten AS Size, 
                            ms.Ten AS MauSac, 
                            cl.Ten AS ChatLieu, 
                            dm.Ten AS DanhMuc, 
                            th.Ten AS ThuongHieu, 
                            spct.TrangThai
                        FROM 
                            dbo.SanPhamChiTiet spct
                        JOIN 
                            dbo.SanPham sp ON spct.ID_SP = sp.ID
                        JOIN 
                            dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                        JOIN 
                            dbo.Size sz ON spct.ID_Size = sz.ID
                        JOIN 
                            dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                        JOIN 
                            dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                        JOIN 
                            dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                        JOIN 
                            dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                     WHERE spct.ID = ?
                     """;
        List<SanPhamChiTiet> list = this.selectBySql(sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public SanPhamChiTiet selectByMa(String ma) {
        String sql = """
                    SELECT 
                            spct.ID,  
                            spct.MaSP, 
                            nv.Ma AS MaNV,
                            nv.Ten AS TenNV,
                            sp.Ten AS TenSP, 
                            spct.Gia, 
                            spct.SoLuong, 
                            sz.Ten AS Size, 
                            ms.Ten AS MauSac, 
                            cl.Ten AS ChatLieu, 
                            dm.Ten AS DanhMuc, 
                            th.Ten AS ThuongHieu, 
                            spct.TrangThai
                        FROM 
                            dbo.SanPhamChiTiet spct
                        JOIN 
                            dbo.SanPham sp ON spct.ID_SP = sp.ID
                        JOIN 
                            dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                        JOIN 
                            dbo.Size sz ON spct.ID_Size = sz.ID
                        JOIN 
                            dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                        JOIN 
                            dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                        JOIN 
                            dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                        JOIN 
                            dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                     WHERE spct.MaSP LIKE ?
                     """;
        List<SanPhamChiTiet> list = this.selectBySql(sql, "%" + ma + "%");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPhamChiTiet> selectAll() { 
        String sql = """
                    SELECT 
                        spct.ID,  
                        spct.MaSP, 
                        nv.Ma AS MaNV,
                        nv.Ten AS TenNV,
                        sp.Ten AS TenSP, 
                        spct.Gia, 
                        spct.SoLuong, 
                        sz.Ten AS Size, 
                        ms.Ten AS MauSac, 
                        cl.Ten AS ChatLieu, 
                        dm.Ten AS DanhMuc, 
                        th.Ten AS ThuongHieu, 
                        spct.TrangThai
                    FROM 
                        dbo.SanPhamChiTiet spct
                    JOIN 
                        dbo.SanPham sp ON spct.ID_SP = sp.ID
                    JOIN 
                        dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                    JOIN 
                        dbo.Size sz ON spct.ID_Size = sz.ID
                    JOIN 
                        dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                    JOIN 
                        dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                    JOIN 
                        dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                    JOIN 
                        dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                    WHERE SPCT.TrangThai = 0 ;
                     """;
        return this.selectBySql(sql);
    }

    @Override
    protected List<SanPhamChiTiet> selectBySql(String sql, Object... args) {
        List<SanPhamChiTiet> list = new ArrayList<>();

        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt("ID"));
                spct.setGia(rs.getDouble("Gia"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setMaSP(rs.getString("MaSP"));
                spct.setTrangThai(rs.getBoolean("TrangThai"));
                spct.setSanPham(new SanPham(rs.getString("TenSP"),
                        new ThuongHieu(rs.getString("ThuongHieu")),
                        new DanhMuc(rs.getString("DanhMuc")),
                        new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"))));
                spct.setSize(new Size(rs.getString("Size")));
                spct.setMauSac(new MauSac(rs.getString("MauSac")));
                spct.setChatLieu(new ChatLieu(rs.getString("ChatLieu")));
                list.add(spct);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<SanPhamChiTiet> selectTotal(String keyword) {
        String sql = """
                        SELECT 
                            spct.ID,  
                            spct.MaSP, 
                            nv.Ma AS MaNV,
                            nv.Ten AS TenNV,
                            sp.Ten AS TenSP, 
                            spct.Gia, 
                            spct.SoLuong, 
                            sz.Ten AS Size, 
                            ms.Ten AS MauSac, 
                            cl.Ten AS ChatLieu, 
                            dm.Ten AS DanhMuc, 
                            th.Ten AS ThuongHieu, 
                            spct.TrangThai
                        FROM 
                            dbo.SanPhamChiTiet spct
                        JOIN 
                            dbo.SanPham sp ON spct.ID_SP = sp.ID
                        JOIN 
                            dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                        JOIN 
                            dbo.Size sz ON spct.ID_Size = sz.ID
                        JOIN 
                            dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                        JOIN 
                            dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                        JOIN 
                            dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                        JOIN 
                            dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                        WHERE sp.Ten LIKE ? AND spct.TrangThai = CAST(1 AS bit)
                     """;
        return this.selectBySql(sql, "%" + keyword + "%%");
    }

    public List<SanPhamChiTiet> searchKeyWord(String keyWord, int pages, int limit) {
        String sql = """
                     SELECT * 
                        FROM 
                        (
                            SELECT 
                                spct.ID,  
                                spct.MaSP, 
                                nv.Ma AS MaNV,
                                nv.Ten AS TenNV,
                                sp.Ten AS TenSP, 
                                spct.Gia, 
                                spct.SoLuong, 
                                sz.Ten AS Size, 
                                ms.Ten AS MauSac, 
                                cl.Ten AS ChatLieu, 
                                dm.Ten AS DanhMuc, 
                                th.Ten AS ThuongHieu, 
                                spct.TrangThai
                            FROM 
                                dbo.SanPhamChiTiet spct
                            JOIN 
                                dbo.SanPham sp ON spct.ID_SP = sp.ID
                            JOIN 
                                dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                            JOIN 
                                dbo.Size sz ON spct.ID_Size = sz.ID
                            JOIN 
                                dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                            JOIN 
                                dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                            JOIN 
                                dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                            JOIN 
                                dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                            WHERE 
                                (sp.Ten LIKE ? OR dm.Ten LIKE ? OR th.Ten LIKE ?)
                                AND spct.TrangThai = 1
                        ) AS FilteredResults
                        ORDER BY ID
                        OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                     """;
        return this.selectBySql(sql,
                "%" + keyWord + "%%",
                "%" + keyWord + "%%",
                "%" + keyWord + "%%",
                (pages - 1) * limit, limit); //tìm theo danh mục , tên ,thương hiệu

    }

    public List<SanPhamChiTiet> selectByKeyWord(String keyword) {
        String sql = """
                            SELECT 
                                spct.ID,  
                                spct.MaSP, 
                                nv.Ma AS MaNV,
                                nv.Ten AS TenNV,
                                sp.Ten AS TenSP, 
                                spct.Gia, 
                                spct.SoLuong, 
                                sz.Ten AS Size, 
                                ms.Ten AS MauSac, 
                                cl.Ten AS ChatLieu, 
                                dm.Ten AS DanhMuc, 
                                th.Ten AS ThuongHieu, 
                                spct.TrangThai
                            FROM 
                                dbo.SanPhamChiTiet spct
                            JOIN 
                                dbo.SanPham sp ON spct.ID_SP = sp.ID
                            JOIN 
                                dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                            JOIN 
                                dbo.Size sz ON spct.ID_Size = sz.ID
                            JOIN 
                                dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                            JOIN 
                                dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                            JOIN 
                                dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                            JOIN 
                                dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                     WHERE sp.Ten LIKE ? OR dm.Ten LIKE ? OR th.Ten LIKE ? 
                     """;
        return this.selectBySql(sql, "%" + keyword + "%%",
                "%" + keyword + "%%",
                "%" + keyword + "%%");
    }

    // lấy số lượng của sản phẩm 
    public int soLuongSPCT(String maSP) {
        int soLuong = 0;
        try {
            String sql = "select SoLuong from SanPhamChiTiet where MaSP = ?";
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSP); 
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamChiTietService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soLuong;
    }

    //phân trang
    public List<SanPhamChiTiet> paging(int page, int limit) {
        String sql = """
                       SELECT 
                                spct.ID,  
                                spct.MaSP, 
                                nv.Ma AS MaNV,
                                nv.Ten AS TenNV,
                                sp.Ten AS TenSP, 
                                spct.Gia, 
                                spct.SoLuong, 
                                sz.Ten AS Size, 
                                ms.Ten AS MauSac, 
                                cl.Ten AS ChatLieu, 
                                dm.Ten AS DanhMuc, 
                                th.Ten AS ThuongHieu, 
                                spct.TrangThai
                            FROM 
                                dbo.SanPhamChiTiet spct
                            JOIN 
                                dbo.SanPham sp ON spct.ID_SP = sp.ID
                            JOIN 
                                dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                            JOIN 
                                dbo.Size sz ON spct.ID_Size = sz.ID
                            JOIN 
                                dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                            JOIN 
                                dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                            JOIN 
                                dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                            JOIN 
                                dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                            WHERE 
                        		spct.TrangThai = CAST(1 AS bit)
                        	ORDER BY
                                spct.ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                     
                     """;
        return this.selectBySql(sql, (page - 1) * limit, limit);
    }

    public void updateStatus(String id) {
        String sql = """
                     UPDATE 
                     	SanPhamChiTiet 
                     SET TrangThai = 1 
                     WHERE ID = ?
                     """;
        JdbcHelper.update(sql, id);
    }
    public List<SanPhamChiTiet> selectPage(String keyword) {
        String sql = """
                            SELECT 
                                spct.ID,  
                                spct.MaSP, 
                                nv.Ma AS MaNV,
                                nv.Ten AS TenNV,
                                sp.Ten AS TenSP, 
                                spct.Gia, 
                                spct.SoLuong, 
                                sz.Ten AS Size, 
                                ms.Ten AS MauSac, 
                                cl.Ten AS ChatLieu, 
                                dm.Ten AS DanhMuc, 
                                th.Ten AS ThuongHieu, 
                                spct.TrangThai
                            FROM 
                                dbo.SanPhamChiTiet spct
                            JOIN 
                                dbo.SanPham sp ON spct.ID_SP = sp.ID
                            JOIN 
                                dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                            JOIN 
                                dbo.Size sz ON spct.ID_Size = sz.ID
                            JOIN 
                                dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                            JOIN 
                                dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                            JOIN 
                                dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                            JOIN 
                                dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                        WHERE (sp.Ten LIKE ? OR spct.MaSP LIKE ?) 
                            AND spct.TrangThai = CAST(1 AS bit)
                     """;
        return this.selectBySql(sql,
                "%" + keyword + "%%",
                "%" + keyword + "%%");
    }
    public List<SanPhamChiTiet> selectStatus(String keyword, int pages, int limit) {
        String sql = """
                        SELECT * 
                            FROM 
                            (
                                SELECT 
                                    spct.ID,  
                                    spct.MaSP, 
                                    nv.Ma AS MaNV,
                                    nv.Ten AS TenNV,
                                    sp.Ten AS TenSP, 
                                    spct.Gia, 
                                    spct.SoLuong, 
                                    sz.Ten AS Size, 
                                    ms.Ten AS MauSac, 
                                    cl.Ten AS ChatLieu, 
                                    dm.Ten AS DanhMuc, 
                                    th.Ten AS ThuongHieu, 
                                    spct.TrangThai
                                FROM 
                                    dbo.SanPhamChiTiet spct
                                JOIN 
                                    dbo.SanPham sp ON spct.ID_SP = sp.ID
                                JOIN 
                                    dbo.MauSac ms ON spct.ID_MauSac = ms.ID
                                JOIN 
                                    dbo.Size sz ON spct.ID_Size = sz.ID
                                JOIN 
                                    dbo.ChatLieu cl ON spct.ID_ChatLieu = cl.ID
                                JOIN 
                                    dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                                JOIN 
                                    dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                                JOIN 
                                    dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                            WHERE (sp.Ten LIKE ? OR spct.MaSP LIKE ?) 
                                    AND spct.TrangThai = CAST(1 AS bit) 
                            ) AS FilteredResults
                            ORDER BY ID
                            OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                     """;
        return this.selectBySql(sql,
                "%" + keyword + "%%",
                "%" + keyword + "%%",
                (pages - 1) * limit, limit);
    }
}
