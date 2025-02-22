/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import repository.MsgBox;
import repository.Validated;
import service.NhanVienService;

public class Form_NhanVien extends javax.swing.JPanel {

    DefaultTableModel dtm = new DefaultTableModel();
    NhanVienService nhanVienService = new NhanVienService();
    List<NhanVien> list = new ArrayList<>();

    /**
     * Creates new form Form_NhanVien
     */
    public Form_NhanVien() {
        initComponents();
        dtm = (DefaultTableModel) tbNhanVien.getModel();
        try {
            showData(nhanVienService.selectAll());
        } catch (Exception e) {
        }
    }

    public void showData(List<NhanVien> list) {
        dtm.setRowCount(0);
        for (NhanVien nhanVien : list) {
            Object data[] = {
                nhanVien.getId(),
                nhanVien.getMa(),
                nhanVien.getPass(),
                nhanVien.getTen(),
                nhanVien.getNgaySinh(),
                nhanVien.getSdt(),
                nhanVien.getEmail(),
                nhanVien.isChucVu() ? "Quản Lý" : "Nhân Viên",
                nhanVien.getLuong(),
                nhanVien.isTrangThai() ? "Đang Đi Làm" : "Nghỉ Việc"
            };
            dtm.addRow(data);
        }
    }

    public void hienThi(NhanVien nhanVien) {
        txtID.setText(nhanVien.getId() + "");
        txtMa.setText(nhanVien.getMa());
        txtTen.setText(nhanVien.getTen());
        txtEmail.setText(nhanVien.getEmail());
        txtMatKhau.setText(nhanVien.getPass());
        txtDiaChi.setText(nhanVien.getDiaChi());
        txtLuong.setText(nhanVien.getLuong() + "");
        txtSDT.setText(nhanVien.getSdt());
        if (nhanVien.isChucVu() == true) {
            rdoQL.setSelected(true);
            rdoNV.setSelected(false);
        } else {
            rdoQL.setSelected(true);
            rdoNV.setSelected(false);
        }
        if (nhanVien.isTrangThai() == true) {
            rdoDLAM.setSelected(true);
        } else {
            rdoNghi.setSelected(true);
        }
        jdcNgaySinh.setDate(nhanVien.getNgaySinh());
    }

    public void lamMoi() {
        txtID.setText("");
        txtMa.setText("");
        txtTen.setText("");
        txtMatKhau.setText("");
        txtDiaChi.setText("");
        txtLuong.setText("");
        txtSDT.setText("");
        rdoQL.setSelected(true);
        rdoDLAM.setSelected(true);
        jdcNgaySinh.setDate(new Date());
        txtEmail.setText("");
    }

    public NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setId(Integer.parseInt(txtID.getText()));
        nv.setMa(txtMa.getText());
        nv.setPass(txtMatKhau.getText());
        nv.setTen(txtTen.getText());
        nv.setNgaySinh(jdcNgaySinh.getDate());
        nv.setSdt(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setLuong(Double.parseDouble(txtLuong.getText()));
        nv.setDiaChi(txtDiaChi.getText());
        if (rdoNV.isSelected()) {
            nv.setChucVu(false);
        } else {
            nv.setChucVu(true);
        }
        if (rdoDLAM.isSelected()) {
            nv.setTrangThai(true);
        } else {
            nv.setTrangThai(false);
        }
        return nv;
    }




    public void them() {
        NhanVien nhanVien = getForm();
        ArrayList<NhanVien> list = (ArrayList<NhanVien>) nhanVienService.selectAll();
        for (NhanVien nv : list) {
            if (nv.getMa().equals(nhanVien.getMa())) {
                MsgBox.alert(this, "Mã nhân viên đã tồn tại!");
                txtMa.requestFocus();
                return;
            }
        }
        try {

            nhanVienService.insert(nhanVien);
            showData(nhanVienService.selectAll());
            MsgBox.alert(this, "Thêm thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm thất bại!");
            e.printStackTrace();
        }
    }

    public void sua() {
        try {
            NhanVien nhanVien = getForm();
            nhanVienService.update(nhanVien);
            showData(nhanVienService.selectAll());

            MsgBox.alert(this, "Sửa thành công!");

        } catch (Exception e) {
            MsgBox.alert(this, "Sửa thất bại!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgChucVu = new javax.swing.ButtonGroup();
        btgTrangThai = new javax.swing.ButtonGroup();
        btn = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboChucVu = new javax.swing.JComboBox<>();
        jdcNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        rdoQL = new javax.swing.JRadioButton();
        rdoNV = new javax.swing.JRadioButton();
        rdoDLAM = new javax.swing.JRadioButton();
        rdoNghi = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();

        btn.setBackground(new java.awt.Color(204, 204, 204));
        btn.setForeground(new java.awt.Color(51, 204, 255));

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã Nhân Viên", "Mật Khẩu", "Tên Nhân Viên", "Ngày Sinh", "Số Điện Thoại", "Email", "chức Vụ", "Lương", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNhanVien.setToolTipText("");
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel11.setText("Quản Lý Nhân Viên");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel11)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("ID Nhân Viên");

        txtID.setBackground(new java.awt.Color(208, 206, 206));
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Mã Nhân Viên");

        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Mật Khẩu");

        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Tên Nhân Viên");

        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Ngày Sinh");

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Lọc Trạng Thái");

        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Lọc Chúc Vụ");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Đang Làm Việc", "Nghỉ Việc" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Quản Lý", "Nhân Viên" }));
        cboChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jdcNgaySinh.setDateFormatString("yyyy-MM-dd");

        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Số điện Thoại");

        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Email");

        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Lương");

        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Chức Vụ");

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Địa Chỉ");

        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Trạng Thái");

        btgChucVu.add(rdoQL);
        rdoQL.setForeground(new java.awt.Color(51, 51, 51));
        rdoQL.setText("Quản Lý");

        btgChucVu.add(rdoNV);
        rdoNV.setForeground(new java.awt.Color(51, 51, 51));
        rdoNV.setSelected(true);
        rdoNV.setText("Nhân Viên");

        btgTrangThai.add(rdoDLAM);
        rdoDLAM.setForeground(new java.awt.Color(51, 51, 51));
        rdoDLAM.setSelected(true);
        rdoDLAM.setText("Đang Làm Việc");

        btgTrangThai.add(rdoNghi);
        rdoNghi.setForeground(new java.awt.Color(51, 51, 51));
        rdoNghi.setText("Nghỉ Việc");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        txtTim.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtTimAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimMouseClicked(evt);
            }
        });
        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTim)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnLayout = new javax.swing.GroupLayout(btn);
        btn.setLayout(btnLayout);
        btnLayout.setHorizontalGroup(
            btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNV)
                            .addComponent(rdoNghi))
                        .addGap(116, 116, 116))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, btnLayout.createSequentialGroup()
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btnLayout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(252, 252, 252)))
                        .addContainerGap(20, Short.MAX_VALUE))))
            .addGroup(btnLayout.createSequentialGroup()
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btnLayout.createSequentialGroup()
                                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel5))
                                .addGap(32, 32, 32)
                                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(btnLayout.createSequentialGroup()
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(69, 69, 69))
                                    .addComponent(txtMa)
                                    .addComponent(txtMatKhau)
                                    .addComponent(txtTen)
                                    .addComponent(jdcNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                    .addComponent(txtDiaChi)))
                            .addComponent(jLabel15)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(32, 32, 32)
                        .addComponent(txtSDT))
                    .addGroup(btnLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(76, 76, 76)
                        .addComponent(txtEmail))
                    .addGroup(btnLayout.createSequentialGroup()
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGap(50, 50, 50)
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLuong)
                            .addGroup(btnLayout.createSequentialGroup()
                                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoDLAM)
                                    .addComponent(rdoQL))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54))
        );
        btnLayout.setVerticalGroup(
            btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLayout.createSequentialGroup()
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btnLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnLayout.createSequentialGroup()
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel21)
                                .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(rdoQL)
                        .addComponent(rdoNV)))
                .addGap(25, 25, 25)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel23)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoDLAM)
                    .addComponent(rdoNghi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:
        try {
            int index = tbNhanVien.getSelectedRow();
            NhanVien nhanVien = nhanVienService.selectAll().get(index);
            hienThi(nhanVien);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        DefaultTableModel searchDefaultTableModel = new DefaultTableModel();
        searchDefaultTableModel.setRowCount(0);
        String trangThai = (String) cboTrangThai.getSelectedItem();
        System.out.println(trangThai);
        String tt = null;
        if (trangThai.endsWith("Đang Làm Việc")) {
            tt = "1";
        } else if (trangThai.endsWith("Nghỉ Việc")) {
            tt = "0";
        }
        if (tt == null) {
            try {
                showData(nhanVienService.selectAll());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                cboChucVu.setSelectedIndex(0);
                // showData(nhanVienService.searchDataTrangThai(tt));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuActionPerformed
        // TODO add your handling code here:
        DefaultTableModel searchDefaultTableModel = new DefaultTableModel();
        searchDefaultTableModel.setRowCount(0);
        String chucvu = (String) cboChucVu.getSelectedItem();
        System.out.println(chucvu);
        String tt = null;
        if (chucvu.endsWith("Quản Lý")) {
            tt = "1";
        } else if (chucvu.endsWith("Nhân Viên")) {
            tt = "0";
        }
        if (tt == null) {
            try {
                showData(nhanVienService.selectAll());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                cboTrangThai.setSelectedIndex(0);
                // showData(nhanVienService.searchDataChucVu(tt));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_cboChucVuActionPerformed

    private void txtTimAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtTimAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimAncestorAdded

    private void txtTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseClicked

    }//GEN-LAST:event_txtTimMouseClicked

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed

    }//GEN-LAST:event_txtTimActionPerformed

    private void txtTimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyPressed

    }//GEN-LAST:event_txtTimKeyPressed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased

    }//GEN-LAST:event_txtTimKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            them();
        }


    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        sua();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgChucVu;
    private javax.swing.ButtonGroup btgTrangThai;
    private javax.swing.JPanel btn;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcNgaySinh;
    private javax.swing.JRadioButton rdoDLAM;
    private javax.swing.JRadioButton rdoNV;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdoQL;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables

    public boolean validateForm() {
        String maString = txtMa.getText().trim();
        String mkString = txtMatKhau.getText().trim();
        String tenString = txtTen.getText().trim();
        String diaChiString = txtDiaChi.getText().trim();
        String sdtString = txtSDT.getText().trim();
        String emailString = txtEmail.getText().trim();
        String luongString = txtLuong.getText().trim();

        if (maString.isEmpty()) {
            MsgBox.alert(this, "Trống mã nhân viên!");
            txtMa.requestFocus();
            return false;
        }

        if (mkString.isEmpty()) {
            MsgBox.alert(this, "Trống mật khẩu!");
            txtMatKhau.requestFocus();
            return false;
        }

        if (tenString.isEmpty()) {
            MsgBox.alert(this, "Trống tên nhân viên!");
            txtTen.requestFocus();
            return false;
        }

        if (diaChiString.isEmpty()) {
            MsgBox.alert(this, "Trống địa chỉ nhân viên!");
            txtDiaChi.requestFocus();
            return false;

        }
        String EMAIL_PATTERN
                = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (emailString.isEmpty()) {
            MsgBox.alert(this, "Trống email nhân viên!");
            txtEmail.requestFocus();
            return false;
        } else if (!emailString.matches(EMAIL_PATTERN)) {
            MsgBox.alert(this, "Sai định dạng!");
            txtEmail.requestFocus();
            return false;
        }

        if (sdtString.isEmpty()) {
            MsgBox.alert(this, "Trống số điện thoại nhân viên!");
            txtSDT.requestFocus();
            return false;
        } else {
            try {
                String regex = "^0\\d{9}$";
                if (!sdtString.matches(regex)) {
                    MsgBox.alert(this, "Số điện thoại bắt đầu bằng 0 và có 10 chữ số");
                    txtSDT.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Vui lòng nhập số!");
                txtSDT.requestFocus();
                return false;
            }
        }
        double luong;
        if (luongString.isEmpty()) {
            MsgBox.alert(this, "Trống lương nhân viên!");
            txtLuong.requestFocus();
            return false;
        } else {
            try {
                luong = Double.parseDouble(luongString);
                if (luong <= 0) {
                    MsgBox.alert(this, "Lương nhân viên cần lớn hơn 0");
                    return false;
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Vui lòng nhập số!");
                return false;
            }
        }
        return true;
    }
}
