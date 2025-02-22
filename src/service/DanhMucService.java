/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import repository.JdbcHelper;


public class DanhMucService extends SellingApplicationImpl<DanhMuc, Integer>{

    @Override
    public void insert(DanhMuc entity) {
          String sql = """
                        INSERT INTO [dbo].[DanhMuc]
                                   ([Ten])
                             VALUES (?)
                        """;

        JdbcHelper.update(sql,
                entity.getTen());
    }

    @Override
    public void update(DanhMuc entity) {
        String sql = """
                        UPDATE [dbo].[DanhMuc]
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
                        DELETE FROM [dbo].[DanhMuc]
                              WHERE ID = ?
                        """;

        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public DanhMuc selectById(Integer id) {
         String selectById = """
                        select * from DanhMuc where ID = ?
                        """;
        List<DanhMuc> list = this.selectBySql(selectById, id);
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DanhMuc> selectAll() {
         String selectAll = """
                       select * from DanhMuc
                       """;
        return this.selectBySql(selectAll);
    }

    @Override
    protected List<DanhMuc> selectBySql(String sql, Object... args) {
         List<DanhMuc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setId(rs.getInt("ID"));
                danhMuc.setTen(rs.getString("Ten"));
                list.add(danhMuc);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
         public List<DanhMuc> searchPages(int page, int limit) {
        String sql = """
                       SELECT * 
                       FROM 
                       (
                           SELECT * 
                           FROM DanhMuc
                       ) AS FilteredResults
                       ORDER BY ID
                       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                       """;
        return this.selectBySql(sql, (page - 1) * limit, limit);
    }
    
}
