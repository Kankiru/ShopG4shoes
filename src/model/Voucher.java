
package model;

import java.sql.Date;

public class Voucher {

    private Integer id;
    private Integer id_NV;
    private String ma;
    private String ten;
    private Date ngayTao;
    private Integer soLuong;
    private Boolean kieuGiam;
    private Double giaTri;
    private Boolean trangThai;
    private NhanVien nv;
    private Date ngayBatDau;
    private Date ngayHetHan;
    public Voucher() {
    }

    
    public Voucher(Integer id, Integer id_NV, String ma, String ten, Date ngayTao, Integer soLuong, Boolean kieuGiam, Double giaTri, Boolean trangThai, NhanVien nv) {
        this.id = id;
        this.id_NV = id_NV;
        this.ma = ma;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.soLuong = soLuong;
        this.kieuGiam = kieuGiam;
        this.giaTri = giaTri;
        this.trangThai = trangThai;
        this.nv = nv;
    }

    public Voucher(Integer id, Integer id_NV, String ma, String ten, Date ngayTao, Integer soLuong, Boolean kieuGiam, Double giaTri, Boolean trangThai, NhanVien nv, Date ngayBatDau, Date ngayHetHan) {
        this.id = id;
        this.id_NV = id_NV;
        this.ma = ma;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.soLuong = soLuong;
        this.kieuGiam = kieuGiam;
        this.giaTri = giaTri;
        this.trangThai = trangThai;
        this.nv = nv;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
    }

    public Voucher(Integer id, String ten) {
        this.id = id;
        this.ten = ten;
    }

  
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_NV() {
        return id_NV;
    }

    public void setId_NV(Integer id_NV) {
        this.id_NV = id_NV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Boolean getKieuGiam() {
        return kieuGiam;
    }

    public void setKieuGiam(Boolean kieuGiam) {
        this.kieuGiam = kieuGiam;
    }

    public Double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Double giaTri) {
        this.giaTri = giaTri;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    @Override
    public String toString() {
        return ten;
    }   
}
