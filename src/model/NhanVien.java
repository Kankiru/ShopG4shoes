package model;

import java.util.Date;

public class NhanVien {

    private int id;
    private String ma;
    private String pass;
    private String ten;
    private String sdt;
    private String email;
    private boolean chucVu;
    private double luong;
    private boolean trangThai;
    private Date ngaySinh;
    private String diaChi;

    public NhanVien() {
    }

    public NhanVien(int id, String ma, String pass, String ten, String sdt, String email, boolean chucVu, double luong, boolean trangThai, Date ngaySinh, String diaChi) {
        this.id = id;
        this.ma = ma;
        this.pass = pass;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.chucVu = chucVu;
        this.luong = luong;
        this.trangThai = trangThai;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public NhanVien(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return this.ten;
    }


}
