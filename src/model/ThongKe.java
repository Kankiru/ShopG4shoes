/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lapto
 */
public class ThongKe {
    private int tongtien;
    private int tonghoadon;

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getTonghoadon() {
        return tonghoadon;
    }

    public void setTonghoadon(int tonghoadon) {
        this.tonghoadon = tonghoadon;
    }

    public ThongKe(int tongtien, int tonghoadon) {
        this.tongtien = tongtien;
        this.tonghoadon = tonghoadon;
    }

    public ThongKe() {
    }
    
}
