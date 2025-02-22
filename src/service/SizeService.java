
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import model.Size;
import model.ThuongHieu;
import repository.JdbcHelper;

public class SizeService extends SellingApplicationImpl<Size, Integer>{

    @Override
    public void insert(Size entity) {
        String sql = """
                        INSERT INTO [dbo].[Size]
                                   ([Ten])
                             VALUES (?)
                        """;

        JdbcHelper.update(sql,entity.getTen());
    }

    @Override
    public void update(Size entity) {
         String sql = """
                        UPDATE [dbo].[Size]
                          SET [Ten] = ?
                         WHERE ID = ?
                        """;

        JdbcHelper.update(sql,
                entity.getTen(),
                entity.getId());
    }

    @Override
    public void delete(Integer id) {
        String delete_sql = """
                        DELETE FROM [dbo].[Size]
                              WHERE ID = ?
                        """;

        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public Size selectById(Integer id) {
         String selectById = """
                        select * from Size where ID = ?
                        """;
        List<Size> list = this.selectBySql(selectById, id);
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Size> selectAll() {
        String selectAll = """
                       select * from size
                       """;
        return this.selectBySql(selectAll);
    }

    @Override
    protected List<Size> selectBySql(String sql, Object... args) {
    List<Size> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Size kichThuoc = new Size();
                kichThuoc.setId(rs.getInt("ID"));
                kichThuoc.setTen(rs.getString("Ten"));
                list.add(kichThuoc);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Size> searchPages(int page, int limit) {
        String sql = """
                       SELECT * 
                       FROM 
                       (
                           SELECT * 
                           FROM Size
                       ) AS FilteredResults
                       ORDER BY ID
                       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                       """;
        return this.selectBySql(sql, (page - 1) * limit, limit);
    }

}
