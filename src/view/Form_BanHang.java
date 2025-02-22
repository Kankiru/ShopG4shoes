package view;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.NhanVien;
import model.SanPhamChiTiet;
import model.Voucher;
import repository.Authu;
import repository.MsgBox;
import repository.Validated;
import service.HoaDonChiTietService;
import service.HoaDonService;
import service.KhachHangService;
import service.NhanVienService;
import service.SanPhamChiTietService;
import service.VoucherService;

public class Form_BanHang extends javax.swing.JPanel {

    private final SanPhamChiTietService service = new SanPhamChiTietService();
    private final HoaDonService hdService = new HoaDonService();
    private final HoaDonChiTietService hoaDonCTService = new HoaDonChiTietService();
    private final NhanVienService nvService = new NhanVienService();
    private final KhachHangService khService = new KhachHangService();
    private final VoucherService vcService = new VoucherService();

    private int row = -1;
    private int rowSP = -1;
    private int rowCart = -1;
    private int pages = 1;
    private final int limit = 4;
    private int numberOfPages = 0;
    private int check;
    private int canExecute = 0;
    private volatile boolean isRunning = true;

    public Form_BanHang() {
        initComponents();
        this.fillTableSP();
        this.fillTableHD();
        this.loadSearch();

        String maNV = Authu.user.getMa();
        NhanVien nv = nvService.selectByMa(maNV);
        lblTenNV.setText(nv.getTen());

        if (txtSDT.getText() == null
                || txtSDT.getText().trim().isEmpty()) {
            lblTenKH.setText("Khác hàng chưa tồn tại");
        }
        this.loadTenKhachHang();
        //this.loadTienThua();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        lblPages = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTienKhachTra = new javax.swing.JTextField();
        lblMaHD = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        lblNgayTao = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtVoucher = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Giá bán", "Số lượng", "Size", "Màu sắc", "Chất liệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel1.setText("Search");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFirst.setText("<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        lblPages.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPages.setText("1/2");

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnFirst)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPrev)
                                .addGap(30, 30, 30)
                                .addComponent(lblPages)
                                .addGap(26, 26, 26)
                                .addComponent(btnNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLast))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFirst)
                        .addComponent(btnPrev))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPages, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext)
                        .addComponent(btnLast)))
                .addGap(24, 24, 24))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Size", "Màu  sắc", "Chất liệu", "Giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnSua)))
        );

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Ngày Tạo", "Tên NV", "Trang Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Mã HĐ");

        jLabel5.setText("SĐT");

        jLabel6.setText("Ngày mua");

        jLabel7.setText("Tên NV");

        jLabel8.setText("Tổng tiền");

        jLabel9.setText("Tiền khách trả ");

        jLabel10.setText("Tiền thừa");

        txtTienKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachTraKeyReleased(evt);
            }
        });

        lblMaHD.setText(" ");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        lblNgayTao.setText(" ");

        lblTenNV.setText(" ");

        lblThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblThanhTien.setText(" ");

        lblTienThua.setText(" ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Tên KH");

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel19.setText("Thanh toán:");

        lblThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        lblThanhToan.setForeground(new java.awt.Color(51, 0, 255));

        jLabel17.setText("Giảm giá:");

        lblGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        lblGiamGia.setForeground(new java.awt.Color(51, 0, 255));

        jLabel16.setText("Voucher:");

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setText("Tạo hoá đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienKhachTra)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtVoucher)
                            .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addGap(0, 9, Short.MAX_VALUE))
                            .addComponent(lblThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblNgayTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTenNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("BÁN HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

        if (evt.getClickCount() == 2) {

            String input = JOptionPane.showInputDialog(this, "Nhập số lượng:");
            if (input == null || input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nhập số lượng cần mua");
                return;
            }
            Integer soLuong = Integer.parseInt(input);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải > 0");
                return;
            }

            this.rowSP = tblSanPham.getSelectedRow();
            Integer slsp = (Integer) tblSanPham.getValueAt(rowSP, 4);
            if (soLuong > slsp) {
                JOptionPane.showMessageDialog(this, "Sản phẩm chỉ còn lại " + slsp);
                soLuong = slsp;
            }

            //Thêm sp vào giỏ hàng
            Integer soLuongSp = 0;
            this.row = tblHoaDon.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thêm sản phẩm!");
                return;
            }
            String maHD = (String) tblHoaDon.getValueAt(row, 1);
            HoaDon hoaDon = hdService.selectByMa(maHD);
            List<HoaDonChiTiet> list = hoaDonCTService.selectByMaHD(hoaDon.getMa());

            Integer idSP = (Integer) tblSanPham.getValueAt(rowSP, 0);

            for (HoaDonChiTiet hoaDonChiTiet : list) {
                if (Objects.equals(idSP, hoaDonChiTiet.getIdSP())) {
                    soLuongSp = hoaDonChiTiet.getSoLuong() + soLuong;
                    this.updateCart(soLuongSp);
                    break;
                }
            }

            if (soLuongSp == 0) {
                soLuongSp = soLuong;

                HoaDonChiTiet hdct = this.getDataCart(soLuongSp);
                this.insertCart(hdct);
            }
            //Load table giỏ hàng
            this.fillTableGioHang(hoaDon);
            this.setDataHoaDon(hoaDon);

            //update lại số lượng sản phẩm
            SanPhamChiTiet spctUpdate = this.updateSoLuongSP(soLuong);
            this.updateDataProducts(spctUpdate);
        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> tbs = new TableRowSorter<>(dtm);
        tblSanPham.setRowSorter(tbs);
        tbs.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtTienKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachTraKeyReleased
        // my code
//        String tongTien = lblThanhTien.getText();
//        String tienKhachTra = txtTienKhachTra.getText();
//        double tongTienDouble = Double.parseDouble(tongTien);
//        double tienKhachTraDouble = Double.parseDouble(tienKhachTra);
//        double tienThua = tienKhachTraDouble - tongTienDouble;
//        lblTienThua.setText(String.valueOf(tienThua));
//        if (txtTienKhachTra.getText().isEmpty()) {
//            lblTienThua.setText(" ");
//        }
    }//GEN-LAST:event_txtTienKhachTraKeyReleased

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed

        this.insertBill();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            // Lấy dòng được chọn
            int selectedRow = tblGioHang.getSelectedRow();

            // Hiển thị hộp thoại xác nhận xoá
            boolean option = MsgBox.confirm(this, "Bạn có chắc muốn xoá hàng này?");

            if (option == true) {
                // Nếu người dùng ấn OK, xoá hàng khỏi bảng
                DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
                model.removeRow(selectedRow);
            }
        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        thanhToan();

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        this.row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 1);
        HoaDon hoaDon = hdService.selectByMa(maHD);
        this.fillTableGioHang(hoaDon);
        this.loadTienThanhToan();
        this.loadTienThua();
        this.setDataHoaDon(hoaDon);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void tblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMouseEntered

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        this.rowCart = tblGioHang.getSelectedRow();
        if (rowCart < 0) {
            MsgBox.alert(this, "Vui lòng chọn một sản phẩm trong giỏ hàng!");
            return;
        }
        Integer idHDCT = (Integer) tblGioHang.getValueAt(rowCart, 0);
        HoaDonChiTiet hdctBanDau = hoaDonCTService.selectById(idHDCT);
        SanPhamChiTiet spctUpdate = service.selectById(hdctBanDau.getIdSP());

        SanPhamChiTiet spct = new SanPhamChiTiet();
        try {
            hoaDonCTService.delete(idHDCT);
            this.row = tblHoaDon.getSelectedRow();
            String maHD = (String) tblHoaDon.getValueAt(row, 1);
            HoaDon hoaDon = hdService.selectByMa(maHD);
            this.fillTableGioHang(hoaDon);

            Integer slMoi = spctUpdate.getSoLuong() + hdctBanDau.getSoLuong();
            spct.setSoLuong(slMoi);

            String status = "Đang bán";
            Boolean trangThai = status.equals("Đang bán");
            spct.setTrangThai(trangThai);
            spct.setId(spctUpdate.getId());
            this.updateDataProducts(spct);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        this.rowCart = tblGioHang.getSelectedRow();
        if (rowCart < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong giỏ hàng để sửa!");
            return;
        }
        String input = JOptionPane.showInputDialog(this, "Nhập số lượng:");
        if (input == null || input.isEmpty()) {
            return;
        }
        Integer soLuong = Integer.parseInt(input);
        if (soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải > 0");
            return;
        }

        Integer idHDCT = (Integer) tblGioHang.getValueAt(rowCart, 0);
        HoaDonChiTiet hdctBanDau = hoaDonCTService.selectById(idHDCT);
        SanPhamChiTiet spctUpdate = service.selectById(hdctBanDau.getIdSP());

        Integer slspGioHang = (Integer) tblGioHang.getValueAt(rowCart, 7);

        if (soLuong > spctUpdate.getSoLuong()) {
            JOptionPane.showMessageDialog(this, "Sản phẩm chỉ còn lại " + spctUpdate.getSoLuong());
            soLuong = spctUpdate.getSoLuong() + slspGioHang;
        }
        this.updateCartAndProducr(soLuong);
        this.row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 1);
        HoaDon hoaDon = hdService.selectByMa(maHD);
        this.setDataHoaDon(hoaDon);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            KhachHangJDialog khachHangDialog = new KhachHangJDialog(frame, true, this.lblTenKH, this.txtSDT);
            khachHangDialog.setVisible(true);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần hủy!");
            return;
        }
        check = JOptionPane.showConfirmDialog(this, "Bạn thực sự muốn hủy hóa đơn ?");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        int rowCount = tblGioHang.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Bảng giỏ hàng đang trống!");
            return;
        }
        
        try {
            for (int i = rowCount - 1; i >= 0; i--) {
                Integer idHDCT = (Integer) tblGioHang.getValueAt(i, 0);

                // Lấy thông tin sản phẩm trước khi xóa
                HoaDonChiTiet hdctBanDau = hoaDonCTService.selectById(idHDCT);
                SanPhamChiTiet spctUpdate = service.selectById(hdctBanDau.getIdSP());

                // Xóa sản phẩm trong giỏ hàng
                hoaDonCTService.delete(idHDCT);

                // Cập nhật lại bảng giỏ hàng
                String maHD = (String) tblHoaDon.getValueAt(row, 1);
                HoaDon hoaDon = hdService.selectByMa(maHD);
                this.fillTableGioHang(hoaDon);

                // Cập nhật lại số lượng sản phẩm
                SanPhamChiTiet spct = new SanPhamChiTiet();
                Integer slMoi = spctUpdate.getSoLuong() + hdctBanDau.getSoLuong();
                spct.setSoLuong(slMoi);
                
                String tt = "Đang bán";
                Boolean trangThai = tt.equals("Đang bán");
                spct.setTrangThai(trangThai);
                spct.setId(spctUpdate.getId());
                
                this.updateDataProducts(spct);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }

        //đổi trạng thái đã hủy
        HoaDon hd = new HoaDon();
        
        this.row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 1);
        HoaDon hoaDon = hdService.selectByMa(maHD);
        
        String sdt = txtSDT.getText();
        KhachHang kh = khService.selectBySDT(sdt);
        hd.setIdKH(kh.getId());
        
        hd.setId(hoaDon.getId());
        
        boolean voucherTonTai = false;
        List<Voucher> list = vcService.selectAll();
        for (Voucher vcct : list) {
            if (txtVoucher.getText() != null && !txtVoucher.getText().trim().isEmpty() && txtVoucher.getText().trim().equals(vcct.getMa())) {
                voucherTonTai = true;
                hd.setIdVC(vcct.getId());
                
                break;
            }
        }
        
        if (!voucherTonTai) {
            hd.setIdVC(null);
        }
        
        hd.setTongTien(Double.parseDouble(lblThanhTien.getText()));
        
        Integer trangThai = 3;
        hd.setTrangThai(trangThai);
        
        try {
            hdService.update(hd);
            this.fillTableHD();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đổi trạng thái hóa đơn thất bại!");
        }
    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblPages;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTienKhachTra;
    private javax.swing.JTextField txtVoucher;
    // End of variables declaration//GEN-END:variables

    private void fillTableSP() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        try {
            String keyWord = txtSearch.getText();

            List<SanPhamChiTiet> listPage = service.selectPage(keyWord);
            this.getPages(listPage);

            List<SanPhamChiTiet> list = service.selectStatus(keyWord, pages, limit);
            for (SanPhamChiTiet spct : list) {
                model.addRow(new Object[]{
                    spct.getId(),
                    spct.getMaSP(),
                    spct.getSanPham().getTen(),
                    spct.getGia(),
                    spct.getSoLuong(),
                    spct.getSize().getTen(),
                    spct.getMauSac().getTen(),
                    spct.getChatLieu().getTen()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void fillTableHD() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);

        try {
            List<HoaDon> list = hdService.selectByStatus();
            for (int i = 0; i < list.size(); i++) {
                HoaDon hd = list.get(i);
                model.addRow(new Object[]{
                    i + 1,
                    hd.getMa(),
                    hd.getNgayTao(),
                    hd.getNv().getTen(),
                    hd.loadTrangThaiHD()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void fillTableGioHang(HoaDon hoaDon) {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);

        try {
            List<HoaDonChiTiet> list = hoaDonCTService.selectByMaHD(hoaDon.getMa());
            for (HoaDonChiTiet hdct : list) {
                model.addRow(new Object[]{
                    hdct.getId(),
                    hdct.getSpct().getSanPham().getMa(),
                    hdct.getSpct().getSanPham().getTen(),
                    hdct.getSpct().getSize().getTen(),
                    hdct.getSpct().getMauSac().getTen(),
                    hdct.getSpct().getChatLieu().getTen(),
                    hdct.getGia(),
                    hdct.getSoLuong(),
                    hdct.getTongTien()
                });
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    // tạo 1 hđ trống
    private HoaDon getDataBill() {
        HoaDon hd = new HoaDon();

        Date date = new Date();
        hd.setNgayTao(new java.sql.Date(date.getTime()));
        String maNV = Authu.user.getMa();
        NhanVien nv = nvService.selectByMa(maNV);
        hd.setIdNV(nv.getId());
        hd.setTongTien(null);
        hd.setIdKH(null);
        hd.setIdVC(null);

        Integer trangThai = 2;//
        hd.setTrangThai(trangThai);

        return hd;
    }

    private void insertBill() {
        boolean check = MsgBox.confirm(this, "Bạn muốn tạo hóa đơn mới?");
        if (check != true) {
            return;
        }
        HoaDon hoaDon = this.getDataBill();

        try {
            hdService.insert(hoaDon);
            this.fillTableHD();
            MsgBox.alert(this, "Tạo hóa đơn thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Tạo hóa đơn thất bại!");
        }
    }

    // lấy dât từ sp dưa lên giỏ hàng
    private HoaDonChiTiet getDataCart(Integer soLuong) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();

        this.row = tblSanPham.getSelectedRow();
        int idSP = (int) tblSanPham.getValueAt(rowSP, 0);
        SanPhamChiTiet spct = service.selectById(idSP);

        this.row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 1);
        HoaDon hoaDon = hdService.selectByMa(maHD);

        Double gia = spct.getGia();
        hdct.setGia(gia);
        hdct.setSoLuong(soLuong);
        hdct.setTongTien(gia * soLuong);
        hdct.setIdSP(spct.getId());
        hdct.setIdHD(hoaDon.getId());

        return hdct;
    }

    private void insertCart(HoaDonChiTiet hdct) {
        try {
            hoaDonCTService.insert(hdct);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Thêm vào giỏ hàng thất bại!");
        }
    }

    private void updateCart(Integer soLuong) {
        this.rowSP = tblSanPham.getSelectedRow();
        Integer idSP = (Integer) tblSanPham.getValueAt(rowSP, 0);

        this.row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 1);
        HoaDon hoaDon = hdService.selectByMa(maHD);
        List<HoaDonChiTiet> list = hoaDonCTService.selectByMaHD(hoaDon.getMa());

        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setSoLuong(soLuong);

        for (HoaDonChiTiet hoaDonChiTiet : list) {
            if (Objects.equals(idSP, hoaDonChiTiet.getIdSP())) {
                hdct.setId(hoaDonChiTiet.getId());
                break;
            }
        }

        try {
            hoaDonCTService.update(hdct);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm vào giỏ hàng thất bại!");
        }
    }

    private void updateDataProducts(SanPhamChiTiet spct) {
        try {
            service.updateSoLuong(spct);
            this.fillTableSP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Thanh toán 
    private void thanhToan() {
        if (lblMaHD.getText() == null || lblMaHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thanh toán!");
            return;
        }

        if (lblThanhTien.getText() == null || lblThanhTien.getText().trim().isEmpty() || Double.parseDouble(lblThanhTien.getText()) == 0.0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để thanh toán!");
            return;
        }

        boolean khachHangTonTai = false;
        List<KhachHang> list = khService.selectAll();
        for (KhachHang khachHang : list) {
            if (txtSDT.getText() != null && !txtSDT.getText().trim().isEmpty() && txtSDT.getText().trim().equals(khachHang.getSdt())) {
                khachHangTonTai = true;
                break;
            }
        }

        if (!khachHangTonTai) {
            JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại");
            return;
        }

        if (txtSDT.getText() == null || txtSDT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại");
            return;
        }

        if (txtTienKhachTra.getText() == null || txtTienKhachTra.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào tiền trả!");
            return;
        }

        if (Double.parseDouble(lblThanhToan.getText()) > Double.parseDouble(txtTienKhachTra.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng trả đủ tiền để thanh toán!");
            return;
        }

        this.updateBill();

        lblMaHD.setText("");
        txtSDT.setText("");
        lblNgayTao.setText("");
        lblThanhTien.setText("");
        txtTienKhachTra.setText("");
        lblTienThua.setText("");
        lblGiamGia.setText("");
        lblThanhToan.setText("");
        txtVoucher.setText("");
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
    }

    private void loadSearch() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fillTableSP();
                first();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fillTableSP();
                first();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fillTableSP();
                first();
            }

        });
    }

    private void getPages(List<SanPhamChiTiet> list) {
        if (list.size() % limit == 0) {
            numberOfPages = list.size() / limit;
        } else {
            numberOfPages = (list.size() / limit) + 1;
        }

        lblPages.setText("1");
    }

    private void updateCartAndProducr(Integer newQuantity) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();

        this.rowCart = tblGioHang.getSelectedRow();
        Integer idHdct = (Integer) tblGioHang.getValueAt(rowCart, 0);
        HoaDonChiTiet hdctBanDau = hoaDonCTService.selectById(idHdct);

        SanPhamChiTiet spct = new SanPhamChiTiet();
        SanPhamChiTiet spctUpdate = service.selectById(hdctBanDau.getIdSP());

        Integer checkSL = newQuantity;
        try {
            if (newQuantity >= hdctBanDau.getSoLuong()) {
                if (spctUpdate.getSoLuong() == 0) {
                    checkSL = hdctBanDau.getSoLuong();
                }
            } else {
                checkSL = newQuantity;
            }
            hdct.setSoLuong(checkSL);
            hdct.setId(hdctBanDau.getId());
            if (newQuantity == 0) {
                hoaDonCTService.delete(idHdct);
            } else {
                hoaDonCTService.update(hdct);
            }

            this.row = tblHoaDon.getSelectedRow();
            String maHD = (String) tblHoaDon.getValueAt(row, 1);
            HoaDon hoaDon = hdService.selectByMa(maHD);
            this.fillTableGioHang(hoaDon);

            Integer slThayDoi = checkSL - hdctBanDau.getSoLuong();
            Integer slMoi = spctUpdate.getSoLuong() - slThayDoi;

            String status;
            if (slMoi > 0) {
                status = "Đang bán";
            } else {
                status = "Ngừng bán";
            }

            Boolean trangThai = status.equals("Đang bán");
            spct.setTrangThai(trangThai);
            spct.setSoLuong(slMoi);
            spct.setId(spctUpdate.getId());
            this.updateDataProducts(spct);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Update số lượng cart thất bại!");
        }
    }
    //thanh toán 

    private void fillTenKhachHang() {
        String sdt = txtSDT.getText().trim();
        if (sdt == null || sdt.isEmpty()) {
            lblTenKH.setText("Không tồn tại");
            return;
        }
        ArrayList<KhachHang> list = (ArrayList<KhachHang>) khService.selectAll();
        boolean timKiem = false;
        for (KhachHang khachHang : list) {
            if (sdt.equals(khachHang.getSdt())) {
                lblTenKH.setText(khachHang.getTen());
                timKiem = true;
                break;
            }
        }
        if (!timKiem) {
            lblTenKH.setText("Khách hàng chưa tồn tại");
        }
    }

    private void loadTenKhachHang() {
        txtSDT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fillTenKhachHang();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fillTenKhachHang();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fillTenKhachHang();
            }
        });
    }

    public void fillTienThua() {
        if (!Validated.isNumericDouble(txtTienKhachTra.getText())) {
            return;
        }
        if (lblThanhToan.getText().trim().isEmpty() || lblThanhToan == null) {
            return;
        }
        Double tienTra = Double.parseDouble(txtTienKhachTra.getText());
        Double thanhToan = Double.parseDouble(lblThanhToan.getText());
        Double tienThua = thanhToan - tienTra;
        if (tienTra <= thanhToan) {
            tienThua = 0.0;
        } else {
            tienThua = - tienThua;
        }
        lblTienThua.setText(String.valueOf(tienThua));
    }

    private void loadTienThua() {
        txtTienKhachTra.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fillTienThua();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fillTienThua();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fillTienThua();
            }
        });
    }

    private void updateBill() {
        HoaDon hd = new HoaDon();

        this.row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 1);
        HoaDon hoaDon = hdService.selectByMa(maHD);

        String sdt = txtSDT.getText();
        KhachHang kh = khService.selectBySDT(this.txtSDT.getText());
        hd.setIdKH(kh.getId());
        hd.setKh(kh);
        hd.setId(hoaDon.getId());
        
        boolean voucherTonTai = false;
        List<Voucher> list = vcService.selectAll();
        for (Voucher vcct : list) {
            if (txtVoucher.getText() != null && !txtVoucher.getText().trim().isEmpty() && txtVoucher.getText().trim().equals(vcct.getMa())) {
                voucherTonTai = true;
                hd.setIdVC(vcct.getId());

                break;
            }
        }

        if (!voucherTonTai) {
            hd.setIdVC(null);
        }

        hd.setTongTien(Double.parseDouble(lblThanhToan.getText()));

        Integer trangThai = 1;
        hd.setTrangThai(trangThai);

        try {
            hdService.update(hd);
            this.fillTableHD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đổi trạng thái hóa đơn thất bại!");
        }
    }

    //phân trang
    private void first() {
        pages = 1;
        this.fillTableSP();
        lblPages.setText("1");
    }

    private void prev() {
        if (pages > 1) {
            pages--;
            this.fillTableSP();
            lblPages.setText("" + pages);
        }
    }

    private void last() {
        pages = numberOfPages;

        this.fillTableSP();

        lblPages.setText("" + pages);
    }

    private void next() {
        if (pages < numberOfPages) {
            pages++;
            this.fillTableSP();
            lblPages.setText("" + pages);
        }
    }

    private SanPhamChiTiet updateSoLuongSP(Integer soLuong) {
        SanPhamChiTiet spct = new SanPhamChiTiet();

        this.rowSP = tblSanPham.getSelectedRow(); // Lấy dòng được chọn
        Integer idSP = (Integer) tblSanPham.getValueAt(rowSP, 0); // lấy id
        SanPhamChiTiet spctUpdate = service.selectById(idSP);
        //Số lượng còn lại của sp sau khi đã trừ đi
        Integer slMoi = spctUpdate.getSoLuong() - soLuong;

        String status;
        if (slMoi == 0) {
            status = "Ngừng bán";
        } else {
            status = "Đang bán";
        }
        Boolean trangThai = status.equals("Đang bán");
        spct.setTrangThai(trangThai);
        spct.setSoLuong(slMoi);

        spct.setId(spctUpdate.getId());

        return spct;
    }

    private void setDataHoaDon(HoaDon hd) {
        lblMaHD.setText(hd.getMa());
        lblNgayTao.setText(String.valueOf(hd.getNgayTao()));
        this.row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 1);
        HoaDon hoaDon = hdService.selectByMa(maHD);
        List<HoaDonChiTiet> list = hoaDonCTService.selectByMaHD(hoaDon.getMa());
        Double tongTien = 0.0;
        for (int i = 0; i < list.size(); i++) {
            double giaTri = list.get(i).getTongTien();
            tongTien += giaTri;
        }
        lblThanhTien.setText(String.valueOf(tongTien));
        boolean voucherTonTai = false;
        Double tienGiam = 0.0;

        List<Voucher> listVC = vcService.selectAll();
        for (Voucher vc : listVC) {
            if (txtVoucher.getText() != null && !txtVoucher.getText().trim().isEmpty() && txtVoucher.getText().trim().equalsIgnoreCase(vc.getMa())) {
                voucherTonTai = true;
                if (!vc.getTrangThai()) {
                    JOptionPane.showMessageDialog(this, "Chương trình khuyến mãi đã ngừng hoạt động!");
                    return;
                }
                if (!vc.getKieuGiam()) {
                    if (tongTien >= 500) {
                        tienGiam = vc.getGiaTri();
                        lblGiamGia.setText(String.valueOf(tienGiam) + " VNĐ");
                    } else {
                        JOptionPane.showMessageDialog(this, "Mã giảm giá này chỉ áp dụng cho hóa đơn từ 500VNĐ trở lên!");
                        lblGiamGia.setText(String.valueOf(tienGiam));
                    }
                } else {
                    tienGiam = (vc.getGiaTri() * tongTien) / 100;
                    lblGiamGia.setText(String.valueOf(vc.getGiaTri()) + " %");
                }
                break;
            }
        }

        if (!voucherTonTai) {
            tienGiam = 0.0;
            lblGiamGia.setText(String.valueOf(tienGiam));
        }

        Double thanhToan = tongTien - tienGiam;
        lblThanhToan.setText(String.valueOf(thanhToan));

        if (txtTienKhachTra.getText().trim().isEmpty()) {
            return;
        }
        Double tienTra = Double.parseDouble(txtTienKhachTra.getText());
        Double tienThua = thanhToan - tienTra;
        if (tienTra <= thanhToan) {
            tienThua = 0.0;
        } else {
            tienThua = -tienThua;
        }
        lblTienThua.setText(String.valueOf(tienThua));
    }

    private void fillTienThanhToan() {
        boolean voucherTonTai = false;
        Double tienGiam = 0.0;

        // Kiểm tra rỗng cho lblTongTien.getText()
        if (!lblThanhTien.getText().trim().isEmpty()) {
            Double tongTien = Double.parseDouble(lblThanhTien.getText());

            List<Voucher> list = vcService.selectAll();
            for (Voucher vcct : list) {
                // Kiểm tra rỗng cho txtVoucher.getText()
                if (txtVoucher.getText() != null && !txtVoucher.getText().trim().isEmpty() && txtVoucher.getText().trim().equals(vcct.getMa())) {
                    voucherTonTai = true;
                    if (!vcct.getTrangThai()) {
                        JOptionPane.showMessageDialog(this, "Chương trình khuyến mãi đã ngừng hoạt động!");
                        return;
                    }

                    if (vcct.getSoLuong() == 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng khuyến mãi đã hết!");
                        return;
                    }

                    if (!vcct.getKieuGiam()) {
                        if (tongTien >= 500) {
                            tienGiam = vcct.getGiaTri();
                            lblGiamGia.setText(String.valueOf(tienGiam) + " VNĐ");
                        } else {
                            JOptionPane.showMessageDialog(this, "Mã giảm giá này chỉ áp dụng cho hóa đơn từ 500VNĐ trở lên!");
                            lblGiamGia.setText(String.valueOf(tienGiam));
                        }
                    } else {
                        tienGiam = (vcct.getGiaTri() * tongTien) / 100;
                        lblGiamGia.setText(String.valueOf(vcct.getGiaTri()) + " %");
                    }

                    break;
                }
            }

            if (!voucherTonTai) {
                tienGiam = 0.0;
                lblGiamGia.setText(String.valueOf(tienGiam));
            }

            Double thanhToan = tongTien - tienGiam;
            lblThanhToan.setText(String.valueOf(thanhToan));
        } else {
            return;
        }
    }
        private void loadTienThanhToan() {
        txtVoucher.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fillTienThanhToan();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                fillTienThanhToan();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                fillTienThanhToan();
            }
        });
    }
}
