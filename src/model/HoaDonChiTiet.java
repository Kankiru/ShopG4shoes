
package model;


public class HoaDonChiTiet {

    private Integer id;
    private Double gia;
    private Integer soLuong;
    private Double tongTien;
    private String SDT_KH;
    private Integer idSP;
    private Integer idHD;
    private Integer idVC;
    private SanPhamChiTiet spct;
    private HoaDon hd;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(Integer id, Double gia, Integer soLuong, Double tongTien, String SDT_KH, Integer idSP, Integer idHD, Integer idVC, SanPhamChiTiet spct, HoaDon hd) {
        this.id = id;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.SDT_KH = SDT_KH;
        this.idSP = idSP;
        this.idHD = idHD;
        this.idVC = idVC;
        this.spct = spct;
        this.hd = hd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public String getSDT_KH() {
        return SDT_KH;
    }

    public void setSDT_KH(String SDT_KH) {
        this.SDT_KH = SDT_KH;
    }

    public Integer getIdSP() {
        return idSP;
    }

    public void setIdSP(Integer idSP) {
        this.idSP = idSP;
    }

    public Integer getIdHD() {
        return idHD;
    }

    public void setIdHD(Integer idHD) {
        this.idHD = idHD;
    }

    public Integer getIdVC() {
        return idVC;
    }

    public void setIdVC(Integer idVC) {
        this.idVC = idVC;
    }

    public SanPhamChiTiet getSpct() {
        return spct;
    }

    public void setSpct(SanPhamChiTiet spct) {
        this.spct = spct;
    }

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }

    public Double tongTien() {
        return this.soLuong * this.gia;
    }
}
