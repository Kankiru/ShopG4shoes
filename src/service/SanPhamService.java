package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import model.SanPham;
import model.NhanVien;
import model.ThuongHieu;
import repository.JdbcHelper;

public class SanPhamService extends SellingApplicationImpl<SanPham, Integer> {

    @Override
    public void insert(SanPham entity) {
        String sql = """
                        INSERT INTO [dbo].[SanPham]
                                   ([Ma]
                                   ,[Ten]
                                   ,[NgayThem]
                                   ,[ID_ThuongHieu]
                                   ,[ID_DanhMuc]
                                   ,[ID_NhanVien])
                             VALUES (?, ?, ?, ?, ?, ?)
                        """;

        JdbcHelper.update(sql,
                entity.getMa(),
                entity.getTen(),
                entity.getNgayThem(),
                entity.getId_th(),
                entity.getId_dm(),
                entity.getId_nv());
    }

    @Override
    public void update(SanPham entity) {
        String sql = """
                        UPDATE [dbo].[SanPham]
                                 SET [Ma] = ?
                                    ,[Ten] = ?
                                    ,[NgayThem] = ?
                                    ,[ID_ThuongHieu] = ?
                                    ,[ID_DanhMuc] = ?
                                    ,[ID_NhanVien] = ?
                         WHERE ID = ?
                        """;

        JdbcHelper.update(sql,
                entity.getMa(),
                entity.getTen(),
                entity.getNgayThem(),
                entity.getId_th(),
                entity.getId_dm(),
                entity.getId_nv(),
                entity.getId());
    }

    @Override
    public void delete(Integer id) {
        String delete_sql = """
                        DELETE FROM [dbo].[SanPham]
                              WHERE ID = ?
                        """;

        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public SanPham selectById(Integer id) {
        String selectById = """
                        select * from SanPham where ID = ?
                        """;
        List<SanPham> list = this.selectBySql(selectById, id);
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectAll() {
        String selectAll = """
                         SELECT  
                            sp.ID,
                            sp.Ma,
                            nv.Ma AS MaNV,
                            nv.Ten AS TenNV,
                            sp.Ten, 
                            sp.NgayThem,
                            th.Ten AS ThuongHieu,
                            dm.Ten AS DanhMuc
                        FROM    
                            dbo.SanPham sp
                            INNER JOIN dbo.NhanVien nv ON sp.ID_NhanVien = nv.ID
                            INNER JOIN dbo.ThuongHieu th ON sp.ID_ThuongHieu = th.ID
                            INNER JOIN dbo.DanhMuc dm ON sp.ID_DanhMuc = dm.ID
                       """;
        return this.selectBySql(selectAll);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("ID"));
                sp.setMa(rs.getString("Ma"));
                sp.setTen(rs.getString("Ten"));
                sp.setNgayThem(rs.getDate("NgayThem"));
                sp.setNhanVien(new NhanVien(rs.getString("MaNV"), rs.getString("TenNV")));
                sp.setThuongHieu(new ThuongHieu(rs.getString("ThuongHieu")));
                sp.setDanhMuc(new DanhMuc(rs.getString("DanhMuc")));
                list.add(sp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    

}
