
package model;


public class SanPhamBanChay {
    private int idsp;
    private String masp;
    private int tongtien;
    private String tensp;
    private int slban;

    public SanPhamBanChay(int idsp, String masp, int tongtien, String tensp, int slban) {
        this.idsp = idsp;
        this.masp = masp;
        this.tongtien = tongtien;
        this.tensp = tensp;
        this.slban = slban;
    }

    public SanPhamBanChay() {
    }
    
    

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSlban() {
        return slban;
    }

    public void setSlban(int slban) {
        this.slban = slban;
    }

    
    
}
