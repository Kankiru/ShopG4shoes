
package model;

import java.sql.Date;


public class KhachHang {
    private Integer id;
    private String ma;
    private String ten;
    private Date ngaySinh;
    private Boolean gioiTinh;
    private String sdt;

    public KhachHang() {
    }

    public KhachHang(Integer id, String ma, String ten, Date ngaySinh, Boolean gioiTinh, String sdt) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
    }

    public KhachHang(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public KhachHang(Integer id, String sdt) {
        this.id = id;
        this.sdt = sdt;
    }

    public KhachHang(Integer id, String ten, String sdt) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", sdt=" + sdt + '}';
    }

  

}
