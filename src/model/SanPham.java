package model;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class SanPham {

    private Integer id;
    private String ma;
    private String ten;
    private Date ngayThem;
    private Integer id_nv;
    private Integer id_th;
    private Integer id_dm;
    private ThuongHieu thuongHieu;
    private DanhMuc danhMuc;
    private NhanVien nhanVien;

    public SanPham() {
    }

    public SanPham(Integer id, String ma, String ten, Date ngayThem, Integer id_nv, Integer id_th, Integer id_dm, ThuongHieu thuongHieu, DanhMuc danhMuc, NhanVien nhanVien) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.ngayThem = ngayThem;
        this.id_nv = id_nv;
        this.id_th = id_th;
        this.id_dm = id_dm;
        this.thuongHieu = thuongHieu;
        this.danhMuc = danhMuc;
        this.nhanVien = nhanVien;
    }

    public SanPham(ThuongHieu thuongHieu, DanhMuc danhMuc, NhanVien nhanVien) {
        this.thuongHieu = thuongHieu;
        this.danhMuc = danhMuc;
        this.nhanVien = nhanVien;
    }

    public SanPham(String ten, ThuongHieu thuongHieu, DanhMuc danhMuc, NhanVien nhanVien) {
        this.ten = ten;
        this.thuongHieu = thuongHieu;
        this.danhMuc = danhMuc;
        this.nhanVien = nhanVien;
    }

    public SanPham(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }

    public Integer getId_nv() {
        return id_nv;
    }

    public void setId_nv(Integer id_nv) {
        this.id_nv = id_nv;
    }

    public Integer getId_th() {
        return id_th;
    }

    public void setId_th(Integer id_th) {
        this.id_th = id_th;
    }

    public Integer getId_dm() {
        return id_dm;
    }

    public void setId_dm(Integer id_dm) {
        this.id_dm = id_dm;
    }

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

}
