 package view;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.DanhMuc;
import model.MauSac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.Size;
import model.ThuongHieu;
import repository.Authu;
import repository.MsgBox;
import service.ChatLieuService;
import service.DanhMucService;
import service.MauSacService;
import service.SanPhamChiTietService;
import service.SanPhamService;
import service.SizeService;
import service.ThuongHieuService;

public class Form_SanPham extends javax.swing.JPanel {

    private final SanPhamChiTietService service = new SanPhamChiTietService();
    private final SanPhamService spService = new SanPhamService();
    private final SizeService sizeService = new SizeService();
    private final MauSacService msService = new MauSacService();
    private final ChatLieuService clService = new ChatLieuService();
    private final DanhMucService dmService = new DanhMucService();
    private final ThuongHieuService thService = new ThuongHieuService();
    private int row = -1;
    private int pages = 1;
    private final int limit = 7;
    private int numberOfPages;
    private boolean check;

    private final List<Size> listkt = sizeService.selectAll();
    private final List<DanhMuc> listdm = dmService.selectAll();
    private final List<MauSac> listms = msService.selectAll();
    private final List<ChatLieu> listcl = clService.selectAll();
    private final List<ThuongHieu> listth = thService.selectAll();

    public Form_SanPham() {
        initComponents();
        this.fillTable();
        this.loadSearch();
        this.row = -1;
        this.updateStatus();
        this.fillCbbTT();
        this.fillCbbSize();
        this.fillCbbMauSac();
        this.fillCbbChatLieu();
        this.fillCbbDanhMuc();
        this.fillCbbThuongHieu();
        this.loadMa();
    }

    private void getPages(List<SanPhamChiTiet> list) {
        if (list.size() % limit == 0) {
            numberOfPages = list.size() / limit;
        } else {
            numberOfPages = (list.size() / limit) + 1;
        }

        lblPages.setText("1");
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
        model.setRowCount(0);

        try {
            String keyword = txtTimKiem.getText();
            List<SanPhamChiTiet> listPage = service.selectByKeyWord(keyword);
            this.getPages(listPage);

            List<SanPhamChiTiet> list = service.searchKeyWord(keyword, pages, limit);
            for (SanPhamChiTiet spct : list) {
                model.addRow(new Object[]{
                    spct.getId(),
                    spct.getMaSP(),
                    spct.getSanPham().getNhanVien().getMa(),
                    spct.getSanPham().getTen(),
                    spct.getGia(),
                    spct.getSoLuong(),
                    spct.getSize().getTen(),
                    spct.getMauSac().getTen(),
                    spct.getChatLieu().getTen(),
                    spct.getSanPham().getDanhMuc().getTen(),
                    spct.getSanPham().getThuongHieu().getTen(),
                    spct.isTrangThai() ? "Đang bán" : "Ngừng bán"
                });
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void loadSearch() {
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fillTable();
                firstPage();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fillTable();
                firstPage();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fillTable();
                firstPage();
            }
        });
    }

    private void loadMa() {
        txtMaSP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                List<SanPham> list = spService.selectAll();
                for (SanPham sanPham : list) {
                    if (txtMaSP.getText().equalsIgnoreCase(sanPham.getMa())) {
                        txtTenSP.setText(sanPham.getTen());
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                List<SanPham> list = spService.selectAll();
                for (SanPham sanPham : list) {
                    if (txtMaSP.getText().equalsIgnoreCase(sanPham.getMa())) {
                        txtTenSP.setText(sanPham.getTen());
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                List<SanPham> list = spService.selectAll();
                for (SanPham sanPham : list) {
                    if (txtMaSP.getText().equalsIgnoreCase(sanPham.getMa())) {
                        txtTenSP.setText(sanPham.getTen());
                    }
                }
            }
        });
    }

    private void fillCbbTT() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTrangThai.getModel();
        model.removeAllElements();
        model.addElement("Đang bán");
        model.addElement("Ngừng bán");
//        List<SanPhamChiTiet> listCbb = service.selectAll();
//        Set<String> liSet = new HashSet<>();
//
//        for (SanPhamChiTiet spct : listCbb) {
//            liSet.add(spct.isTrangThai() ? "Đang bán" : "Ngừng bán");
//        }
//
//        for (String status : liSet) {
//            model.addElement(status);
//        }
    }

    private void fillCbbSize() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbSize.getModel();
        model.removeAllElements();

        List<Size> listCbb = sizeService.selectAll();
        for (Size size : listCbb) {
            model.addElement(size);
        }
    }

    private void fillCbbMauSac() {
        DefaultComboBoxModel modelms = (DefaultComboBoxModel) cbbMauSac.getModel();
        modelms.removeAllElements();

        List<MauSac> listCbb = msService.selectAll();
        for (MauSac mauSac : listCbb) {
            modelms.addElement(mauSac);
        }
    }

    private void fillCbbChatLieu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbChatLieu.getModel();
        model.removeAllElements();

        List<ChatLieu> listCbb = clService.selectAll();
        for (ChatLieu chatLieu : listCbb) {
            model.addElement(chatLieu);
        }
    }

    private void fillCbbDanhMuc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbDanhMuc.getModel();
        model.removeAllElements();

        List<DanhMuc> listCbb = dmService.selectAll();
        for (DanhMuc danhMuc : listCbb) {
            model.addElement(danhMuc);
        }
    }

    private void fillCbbThuongHieu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbThuongHieu.getModel();
        model.removeAllElements();

        List<ThuongHieu> listCbb = thService.selectAll();
        for (ThuongHieu th : listCbb) {
            model.addElement(th);
        }
    }

    private void updateStatus() {
        Boolean edit = this.row >= 0;

        btnAdd.setEnabled(!edit);
        btnUpdate.setEnabled(edit);
    }

    private void setDataForm(SanPhamChiTiet spct) {

        txtMaSP.setText(spct.getMaSP());

        txtTenSP.setText(spct.getSanPham().getTen());
        txtGia.setText(String.valueOf(spct.getGia()));
        txtSoLuong.setText(String.valueOf(spct.getSoLuong()));

        cbbSize.setSelectedItem(spct.getSize().getTen());
        cbbMauSac.setSelectedItem(spct.getMauSac().getTen());
        cbbChatLieu.setSelectedItem(spct.getChatLieu().getTen());
        cbbDanhMuc.setSelectedItem(spct.getSanPham().getDanhMuc().getTen());
        cbbThuongHieu.setSelectedItem(spct.getSanPham().getThuongHieu().getTen());

        cbbTrangThai.setSelectedItem(spct.isTrangThai() ? "Đang bán" : "Ngừng bán");
    }

    private void editForm() {
        Integer id = (Integer) tblQLSP.getValueAt(row, 0);
        SanPhamChiTiet spct = service.selectById(id);
        this.setDataForm(spct);
        this.updateStatus();
    }

    private void firstPage() {
        pages = 1;
        this.fillTable();
        lblPages.setText("1");
    }

    private void prevPage() {
        if (pages > 1) {
            pages--;
            this.fillTable();
            lblPages.setText("" + pages);
        }
    }

    private void nextPage() {
        if (pages < numberOfPages) {
            pages++;
            this.fillTable();
            lblPages.setText("" + pages);
        }
    }

    private void lastPage() {
        pages = numberOfPages;
        this.fillTable();
        lblPages.setText("" + pages);
    }

    private SanPhamChiTiet getDataForm_spct(List<SanPham> list) {
        SanPhamChiTiet spct = new SanPhamChiTiet();

        spct.setMaSP(txtMaSP.getText());
        for (SanPham sanPham : list) {
            if (txtMaSP.getText().equalsIgnoreCase(sanPham.getMa())) {
                spct.setId_sanPham(sanPham.getId());
            }
        }
        spct.setGia(Double.valueOf(txtGia.getText()));
        spct.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        String status = (String) cbbTrangThai.getSelectedItem();
        Boolean trangThai = status.equals("Đang bán");
        spct.setTrangThai(trangThai);
        ChatLieu cl = (ChatLieu) cbbChatLieu.getSelectedItem();
        spct.setId_chatLieu(cl.getId());
        Size size = (Size) cbbSize.getSelectedItem();
        spct.setId_size(size.getId());
        MauSac ms = (MauSac) cbbMauSac.getSelectedItem();
        spct.setId_mauSac(ms.getId());
        return spct;
    }

    private SanPham getData_SP() {
        SanPham sp = new SanPham();

        sp.setMa(txtMaSP.getText());
        sp.setTen(txtTenSP.getText());
        sp.setId_nv(Authu.user.getId());
        Date currentDate = new Date();
        sp.setNgayThem(new java.sql.Date(currentDate.getTime()));
        ThuongHieu th = (ThuongHieu) cbbThuongHieu.getSelectedItem();
        sp.setId_th(th.getId());
        DanhMuc dm = (DanhMuc) cbbDanhMuc.getSelectedItem();
        sp.setId_dm(dm.getId());
        return sp;
    }

    private void insert_sp_spct() {
        check = MsgBox.confirm(this, "Xác nhận thêm dữ liệu?");
        if (check != true) {
            return;
        }
        List<SanPham> list = spService.selectAll();
        for (SanPham sanPham : list) {
            if (txtMaSP.getText().trim().equalsIgnoreCase(sanPham.getMa())) {
                List<SanPham> listSP = spService.selectAll();
                SanPhamChiTiet spct = this.getDataForm_spct(listSP);
                try {
                    service.insert(spct);
                    this.fillTable();
                    MsgBox.alert(this, "Thêm dữ liệu thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
                }
                return;
            }
        }

        try {
            SanPham sp = this.getData_SP();
            spService.insert(sp);
            List<SanPham> listSP = spService.selectAll();
            SanPhamChiTiet spct = this.getDataForm_spct(listSP);
            service.insert(spct);
            this.fillTable();
            MsgBox.alert(this, "Thêm SP thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu sp!");
        }
    }

    private void update() {
        //thông baos
        check = MsgBox.confirm(this, "Xác nhận sửa dữ liệu?");
        if (check != true) {
            return;
        }
        List<SanPham> listSP = spService.selectAll();
        SanPhamChiTiet spct = this.getDataForm_spct(listSP);
        Integer id = (Integer) tblQLSP.getValueAt(row, 0);
        spct.setId(id);
        try {
            service.update(spct);
            this.fillTable();
            MsgBox.alert(this, "Sửa dữ liệu thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    private void clean() {
        txtGia.setText("");
        txtMaSP.setText("");
        txtSoLuong.setText("");
        txtTenSP.setText("");

        this.row = -1;
        this.updateStatus();
    }

    public Size getBySize(String ten) {
        for (Size o : listkt) {
            if (o.getTen().equalsIgnoreCase(ten)) {
                return new Size(o.getId(), o.getTen());
            }
        }
        return null;
    }

    public ChatLieu getByChatLieu(String ten) {
        for (ChatLieu o : listcl) {
            if (o.getTen().equalsIgnoreCase(ten)) {
                return new ChatLieu(o.getId(), o.getTen());
            }
        }
        return null;
    }

    public DanhMuc getByDanhMuc(String ten) {
        for (DanhMuc o : listdm) {
            if (o.getTen().equalsIgnoreCase(ten)) {
                return new DanhMuc(o.getId(), o.getTen());
            }
        }
        return null;
    }

    public MauSac getByMauSac(String ten) {
        for (MauSac o : listms) {
            if (o.getTen().equalsIgnoreCase(ten)) {
                return new MauSac(o.getId(), o.getTen());
            }
        }
        return null;
    }

    public ThuongHieu getByThuongHieu(String ten) {
        for (ThuongHieu o : listth) {
            if (o.getTen().equalsIgnoreCase(ten)) {
                return new ThuongHieu(o.getId(), o.getTen());
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        cbbChatLieu = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        btnAddSize = new javax.swing.JButton();
        btnAddMauSac = new javax.swing.JButton();
        btnAddChatLieu = new javax.swing.JButton();
        btnAddDanhMuc = new javax.swing.JButton();
        btnAddThuongHieu = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        btnPrev = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblPages = new javax.swing.JLabel();
        btnSPNB = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1044, 544));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 14)); // NOI18N
        jLabel1.setText("Quản lý sản phẩm");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nhập thông tin");

        jLabel3.setText("Mã SP: ");

        jLabel4.setText("Tên SP:");

        jLabel5.setText("Giá:");

        jLabel6.setText("Số lượng:");

        jLabel7.setText("Trạng thái:");

        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        jLabel8.setText("Size:");

        jLabel9.setText("Màu sắc:");

        jLabel10.setText("Chất liệu:");

        jLabel11.setText("Danh mục:");

        jLabel12.setText("Thương hiệu:");

        btnAddSize.setText("+");
        btnAddSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSizeActionPerformed(evt);
            }
        });

        btnAddMauSac.setText("+");
        btnAddMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMauSacActionPerformed(evt);
            }
        });

        btnAddChatLieu.setText("+");
        btnAddChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChatLieuActionPerformed(evt);
            }
        });

        btnAddDanhMuc.setText("+");
        btnAddDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDanhMucActionPerformed(evt);
            }
        });

        btnAddThuongHieu.setText("+");
        btnAddThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddThuongHieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbTrangThai, 0, 308, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSP)
                            .addComponent(txtTenSP)
                            .addComponent(txtGia)
                            .addComponent(txtSoLuong))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbbThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbDanhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAddSize, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddMauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAddChatLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnAddDanhMuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddSize)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddMauSac)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddChatLieu)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddDanhMuc)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddThuongHieu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tìm kiếm:");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Update.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Clean.png"))); // NOI18N
        btnNew.setText("Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        tblQLSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "Mã NV", "Tên SP", "Giá", "Số lượng", "Size", "Màu sắc", "Chất liệu", "Danh mục", "Thương hiệu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLSP);

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnFirst.setText("|<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">>|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        lblPages.setText("1");

        btnSPNB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eye1.png"))); // NOI18N
        btnSPNB.setText("Sản phẩm ngừng bán");
        btnSPNB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPNBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(btnFirst)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrev)
                        .addGap(28, 28, 28)
                        .addComponent(lblPages)
                        .addGap(26, 26, 26)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(btnLast)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSPNB))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew)
                        .addGap(125, 125, 125)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnNew))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(lblPages)
                    .addComponent(btnSPNB))
                .addContainerGap(7, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        insert_sp_spct();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clean();
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblQLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPMouseClicked
        // TODO add your handling code here:
        this.row = tblQLSP.getSelectedRow();
        this.editForm();

        DefaultComboBoxModel modelkt = (DefaultComboBoxModel) cbbSize.getModel();
        modelkt.removeAllElements();
        List<Size> listCbbkt = sizeService.selectAll();
        for (Size size : listkt) {
            modelkt.addElement(size);
        }
        modelkt.setSelectedItem(getBySize(tblQLSP.getValueAt(row, 6).toString()));

        //mau sac
        DefaultComboBoxModel modelms = (DefaultComboBoxModel) cbbMauSac.getModel();
        modelms.removeAllElements();
        for (MauSac ms : listms) {
            modelms.addElement(ms);
        }
        modelms.setSelectedItem(getByMauSac(tblQLSP.getValueAt(row, 7).toString()));

        //chat lieu
        DefaultComboBoxModel modelcl = (DefaultComboBoxModel) cbbChatLieu.getModel();
        modelcl.removeAllElements();
        for (ChatLieu cl : listcl) {
            modelcl.addElement(cl);
        }
        modelcl.setSelectedItem(getByChatLieu(tblQLSP.getValueAt(row, 8).toString()));

        //danh muc
        DefaultComboBoxModel modeldm = (DefaultComboBoxModel) cbbDanhMuc.getModel();
        modeldm.removeAllElements();
        for (DanhMuc dm : listdm) {
            modeldm.addElement(dm);
        }
        modeldm.setSelectedItem(getByDanhMuc(tblQLSP.getValueAt(row, 9).toString()));

        //thuong hieu
        DefaultComboBoxModel modelth = (DefaultComboBoxModel) cbbThuongHieu.getModel();
        modelth.removeAllElements();
        for (ThuongHieu th : listth) {
            modelth.addElement(th);
        }
        modelth.setSelectedItem(getByThuongHieu(tblQLSP.getValueAt(row, 10).toString()));

    }//GEN-LAST:event_tblQLSPMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        firstPage();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prevPage();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        nextPage();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        lastPage();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnAddSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSizeActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            new SizeJDialog(frame, true).setVisible(true);
            this.fillCbbSize();
            List<Size> lstCbb = sizeService.selectAll();
            cbbSize.setSelectedIndex(lstCbb.size() - 1);
        }
    }//GEN-LAST:event_btnAddSizeActionPerformed

    private void btnAddMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMauSacActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            new MauSacJDialog(frame, true).setVisible(true);
            this.fillCbbMauSac();
            List<MauSac> lstCbb = msService.selectAll();
            cbbMauSac.setSelectedIndex(lstCbb.size() - 1);
        }
    }//GEN-LAST:event_btnAddMauSacActionPerformed

    private void btnAddChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChatLieuActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            new ChatLieuJDialog(frame, true).setVisible(true);
            this.fillCbbChatLieu();
            List<ChatLieu> lstCbb = clService.selectAll();
            cbbChatLieu.setSelectedIndex(lstCbb.size() - 1);
        }
    }//GEN-LAST:event_btnAddChatLieuActionPerformed

    private void btnAddDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDanhMucActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            new DanhMucJDialog(frame, true).setVisible(true);
            this.fillCbbDanhMuc();
            List<DanhMuc> lstCbb = dmService.selectAll();
            cbbDanhMuc.setSelectedIndex(lstCbb.size() - 1);
        }
    }//GEN-LAST:event_btnAddDanhMucActionPerformed

    private void btnAddThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddThuongHieuActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            new ThuongHieuJDialog(frame, true).setVisible(true);
            this.fillCbbThuongHieu();
            List<ThuongHieu> lstCbb = thService.selectAll();
            cbbThuongHieu.setSelectedIndex(lstCbb.size() - 1);
        }
    }//GEN-LAST:event_btnAddThuongHieuActionPerformed

    private void btnSPNBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPNBActionPerformed
        // TODO add your handling code here:
        JFrame loginFrame = new JFrame();
        JDialog dia = new SanPhamNgungBanJDialog(loginFrame, true);
        dia.setVisible(true);
    }//GEN-LAST:event_btnSPNBActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddChatLieu;
    private javax.swing.JButton btnAddDanhMuc;
    private javax.swing.JButton btnAddMauSac;
    private javax.swing.JButton btnAddSize;
    private javax.swing.JButton btnAddThuongHieu;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSPNB;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPages;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
