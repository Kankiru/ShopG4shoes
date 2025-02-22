package service;

import java.util.ArrayList;
import java.util.List;
import model.Voucher;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.NhanVien;
import repository.JdbcHelper;

/**
 *
 * @author DELL
 */
public class VoucherService extends SellingApplicationImpl<Voucher, Integer> {

    @Override
    public void insert(Voucher entity) {
        String sql_insert = """
                            INSERT INTO [dbo].[Voucher]
                                       ([Ma]
                                       ,[Ten]
                                       ,[NgayTao]
                                       ,[ID_NhanVien]
                                       ,[NgayBatDau]
                                       ,[NgayHetHan]
                                       ,[SoLuong]
                                       ,[KieuGiam]
                                       ,[GiaTri]
                                       ,[TrangThai])
                                 VALUES
                                       (?,?,?,?,?,?,?,?,?,?)
                            GO
                            """;
        JdbcHelper.update(sql_insert,
                entity.getMa(),
                entity.getTen(),
                entity.getNgayTao(),
                entity.getId_NV(),
                entity.getNgayBatDau(),
                entity.getNgayHetHan(),
                entity.getSoLuong(),
                entity.getKieuGiam(),
                entity.getTrangThai()
        );
    }

    @Override
    public void update(Voucher entity) {
        String sql_update = """
                            UPDATE [dbo].[Voucher]
                               SET [Ma] = ?
                                  ,[Ten] = ?
                                  ,[NgayTao] = ?
                                  ,[ID_NhanVien] = ?
                                  ,[NgayBatDau] = ?
                                  ,[NgayHetHan] = ?
                                  ,[SoLuong] = ?
                                  ,[KieuGiam] = ?
                                  ,[GiaTri] = ?
                                  ,[TrangThai] = ?
                             WHERE ID = ?
                            """;
        JdbcHelper.update(sql_update,
                entity.getMa(),
                entity.getTen(),
                entity.getNgayTao(),
                entity.getId_NV(),
                entity.getNgayBatDau(),
                entity.getNgayHetHan(),
                entity.getSoLuong(),
                entity.getKieuGiam(),
                entity.getTrangThai(),
                entity.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        String sql_delete = """
                            DELETE FROM [dbo].[Voucher]
                                  WHERE ID = ?
                            """;
    }

    @Override
    public Voucher selectById(Integer id) {
        String sql = """
                     SELECT dbo.Voucher.[ID]
                           ,dbo.Voucher.[Ma]
                           ,dbo.Voucher.[Ten]
                           ,[dbo].NhanVien.Ma AS MaNV
                           ,[dbo].NhanVien.Ten AS TenNV
                           ,dbo.Voucher.[ngayTao]
                           ,dbo.Voucher.[NgayBatDau]
                           ,dbo.Voucher.[NgayHetHan]
                           ,dbo.Voucher.[SoLuong]
                           ,dbo.Voucher.[KieuGiam]
                           ,dbo.Voucher.[GiaTri]
                           ,dbo.Voucher.[TrangThai]
                       FROM dbo.NhanVien INNER JOIN dbo.Voucher
                            ON dbo.NhanVien.ID = dbo.Voucher.ID_NhanVien 
                       WHERE dbo.Voucher.ID = ?
                     """;
        List<Voucher> list = this.selectBySql(sql, id);
        if (list == null) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<Voucher> selectAll() {
        String sql = """
                     SELECT
                             dbo.Voucher.ID,
                             dbo.Voucher.Ma,
                             dbo.Voucher.Ten,
                             dbo.NhanVien.Ma AS MaNV,
                             dbo.NhanVien.Ten AS TenNV,
                             dbo.Voucher.NgayTao,
                             dbo.Voucher.NgayBatDau,
                             dbo.Voucher.NgayHetHan,
                             dbo.Voucher.SoLuong,
                             dbo.Voucher.KieuGiam,
                             dbo.Voucher.GiaTri,
                             dbo.Voucher.TrangThai
                         FROM
                             dbo.NhanVien
                         INNER JOIN
                             dbo.Voucher ON dbo.NhanVien.ID = dbo.Voucher.ID_NhanVien
                     """;

        return this.selectBySql(sql);
    }

    @Override
    protected List<Voucher> selectBySql(String sql, Object... args) {
        List<Voucher> list = new ArrayList<>();

        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setId(rs.getInt("ID"));
                vc.setMa(rs.getString("Ma"));
                vc.setTen(rs.getString("Ten"));
                vc.setNgayTao(rs.getDate("NgayTao"));
                vc.setNv(new NhanVien(rs.getString("MaNV"), rs.getString("TenNV")));
                vc.setNgayBatDau(rs.getDate("NgayBatDau"));
                vc.setNgayHetHan(rs.getDate("NgayHetHan"));
                vc.setSoLuong(rs.getInt("SoLuong"));
                vc.setKieuGiam(rs.getBoolean("KieuGiam"));
                vc.setGiaTri(rs.getDouble("GiaTri"));
                vc.setTrangThai(rs.getBoolean("TrangThai"));

                list.add(vc);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Voucher> selectByStatus(Boolean status, int soLuong, int nam, int id_voucher) {
        String selectByStatus = """
                                """;
        return this.selectBySql(selectByStatus, status);
    }

    public List<Voucher> selectByKeyWord(Integer id_voucher) {
        String sql = """
                        SELECT
                                                    dbo.Voucher.ID,
                                                    dbo.Voucher.Ma,
                                                    dbo.Voucher.Ten,
                                                    dbo.NhanVien.Ma AS MaNV,
                                                    dbo.NhanVien.Ten AS TenNV,
                                                    dbo.Voucher.NgayTao,
                                                    dbo.Voucher.NgayBatDau,
                                                    dbo.Voucher.NgayHetHan,
                                                    dbo.Voucher.SoLuong,
                                                    dbo.Voucher.KieuGiam,
                                                    dbo.Voucher.GiaTri,
                                                    dbo.Voucher.TrangThai
                                                FROM
                                                    dbo.NhanVien
                                                INNER JOIN
                                                    dbo.Voucher ON dbo.NhanVien.ID = dbo.Voucher.ID_NhanVien
                     """;
        return this.selectBySql(sql, id_voucher);
    }

    public List<Voucher> searchKeyWord(Integer id_voucher, int pages, int limit) {
        String sql = """
                     SELECT * 
                     FROM 
                     (
                     SELECT
                                                  dbo.Voucher.ID,
                                                  dbo.Voucher.Ma,
                                                  dbo.Voucher.Ten,
                                                  dbo.NhanVien.Ma AS MaNV,
                                                  dbo.NhanVien.Ten AS TenNV,
                                                  dbo.Voucher.NgayTao,
                                                  dbo.Voucher.NgayBatDau,
                                                  dbo.Voucher.NgayHetHan,
                                                  dbo.Voucher.SoLuong,
                                                  dbo.Voucher.KieuGiam,
                                                  dbo.Voucher.GiaTri,
                                                  dbo.Voucher.TrangThai
                                              FROM
                                                  dbo.NhanVien
                                              INNER JOIN
                                                  dbo.Voucher ON dbo.NhanVien.ID = dbo.Voucher.ID_NhanVien                     
                             
                        WHERE dbo.Voucher.ID = ?
                     ) AS FilteredResults
                     ORDER BY ID
                     OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                     """;
        return this.selectBySql(sql,
                id_voucher, (pages - 1) * limit, limit);
    }

    public List<Voucher> filterStatus(Boolean status, Integer soLuong, int nam, Integer id_voucher, int pages, int limit) {
        String sql = """
                     SELECT * 
                     FROM 
                     (
                        SELECT
                               dbo.Voucher.ID,
                               dbo.Voucher.Ma,
                               dbo.Voucher.Ten,
                               dbo.NhanVien.Ma AS MaNV,
                               dbo.NhanVien.Ten AS TenNV,
                               dbo.Voucher.NgayTao,
                               dbo.Voucher.NgayBatDau,
                               dbo.Voucher.NgayHetHan,
                               dbo.Voucher.SoLuong,
                               dbo.Voucher.KieuGiam,
                               dbo.Voucher.GiaTri,
                               dbo.Voucher.TrangThai
                           FROM
                               dbo.NhanVien
                           INNER JOIN
                               dbo.Voucher ON dbo.NhanVien.ID = dbo.Voucher.ID_NhanVien                     

                        WHERE  dbo.Voucher.TrangThai = ISNULL(?, dbo.Voucher.TrangThai)
                                AND dbo.Voucher.SoLuong = ISNULL(?, dbo.Voucher.SoLuong)
                                AND ? BETWEEN YEAR(dbo.Voucher.NgayBatDau) AND YEAR(dbo.Voucher.NgayHetHan)
                                AND dbo.Voucher.ID = ?
                     ) AS FilteredResults
                     ORDER BY ID
                     OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                     """;
        return this.selectBySql(sql,
                status, soLuong, nam, id_voucher, (pages - 1) * limit, limit);
    }

}
