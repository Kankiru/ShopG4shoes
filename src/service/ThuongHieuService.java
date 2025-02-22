/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ThuongHieu;
import repository.JdbcHelper;

/**
 *
 * @author DELL
 */
public class ThuongHieuService extends SellingApplicationImpl<ThuongHieu, Integer> {

    String insert_sql = """
                        INSERT INTO [dbo].[ThuongHieu]
                            ([Ten])
                            VALUES (?)
                        """;
    String update_sql = """
                        UPDATE [dbo].[ThuongHieu]
                            SET [Ten] = ?
                            WHERE ID = ?
                        """;
    String delete_sql = """
                        DELETE FROM [dbo].[ThuongHieu]
                            WHERE ID = ?
                        """;

    String select_all = """
                        SELECT * FROM ThuongHieu
                        """;

    String selectById = """
                        SELECT * FROM ThuongHieu WHERE ID = ?
                        """;

    @Override
    public void insert(ThuongHieu entity) {
        JdbcHelper.update(insert_sql,
                entity.getTen());
    }

    @Override
    public void update(ThuongHieu entity) {
        JdbcHelper.update(update_sql,
                entity.getTen(),
                entity.getId());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public ThuongHieu selectById(Integer id) {
        List<ThuongHieu> list = this.selectBySql(selectById, id);
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ThuongHieu> selectAll() {
        return this.selectBySql(select_all);
    }

    @Override
    protected List<ThuongHieu> selectBySql(String sql, Object... args) {
        List<ThuongHieu> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getInt("ID"));
                th.setTen(rs.getString("Ten"));
                list.add(th);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ThuongHieu> searchPages(int page, int limit) {
        String sql = """
                       SELECT * 
                       FROM 
                       (
                           SELECT * 
                           FROM ThuongHieu
                       ) AS FilteredResults
                       ORDER BY ID
                       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                       """;
        return this.selectBySql(sql, (page - 1) * limit, limit);
    }
}
