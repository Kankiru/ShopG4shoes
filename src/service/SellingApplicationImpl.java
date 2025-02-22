/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author ledin
 */
public abstract class SellingApplicationImpl<E, K> {

    public abstract void insert(E entity);

    public abstract void update(E entity);

    public abstract void delete(K id);

    public abstract E selectById(K id);

    public abstract List<E> selectAll();

    protected abstract List<E> selectBySql(String sql, Object... args);
}
