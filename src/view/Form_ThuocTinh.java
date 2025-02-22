package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.DanhMuc;
import model.MauSac;
import model.Size;
import model.ThuongHieu;
import repository.MsgBox;
import static repository.Validated.isValidName;
import service.ChatLieuService;
import service.DanhMucService;
import service.MauSacService;
import service.SizeService;
import service.ThuongHieuService;

public class Form_ThuocTinh extends javax.swing.JPanel {

    public Form_ThuocTinh() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rdoSize = new javax.swing.JRadioButton();
        rdoMau = new javax.swing.JRadioButton();
        rdoCL = new javax.swing.JRadioButton();
        rdoDM = new javax.swing.JRadioButton();
        rdoTH = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Thuộc tính:");

        buttonGroup1.add(rdoSize);
        rdoSize.setForeground(new java.awt.Color(0, 0, 0));
        rdoSize.setSelected(true);
        rdoSize.setText("Size");
        rdoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSizeActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMau);
        rdoMau.setForeground(new java.awt.Color(0, 0, 0));
        rdoMau.setText("Màu sắc");
        rdoMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCL);
        rdoCL.setForeground(new java.awt.Color(0, 0, 0));
        rdoCL.setText("Chất liệu");

        buttonGroup1.add(rdoDM);
        rdoDM.setForeground(new java.awt.Color(0, 0, 0));
        rdoDM.setText("Danh mục");

        buttonGroup1.add(rdoTH);
        rdoTH.setForeground(new java.awt.Color(0, 0, 0));
        rdoTH.setText("Thương hiệu");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("QUẢN LÝ THUỘC TÍNH");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tên:");

        txtTen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tblThuocTinh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuocTinh);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoMau)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoCL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoDM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoTH))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSua)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNew)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtTen))))
                        .addGap(0, 462, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rdoSize)
                    .addComponent(rdoMau)
                    .addComponent(rdoCL)
                    .addComponent(rdoDM)
                    .addComponent(rdoTH))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnSua)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void rdoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSizeActionPerformed

    }//GEN-LAST:event_rdoSizeActionPerformed

    private void rdoMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauActionPerformed

    }//GEN-LAST:event_rdoMauActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked

        int row = tblThuocTinh.getSelectedRow();
        if (row < 0) {
            return;
        } else {
            txtTen.setText(tblThuocTinh.getValueAt(row, 1).toString());
        }

    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO addData your handling code here:
        addData();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoCL;
    private javax.swing.JRadioButton rdoDM;
    private javax.swing.JRadioButton rdoMau;
    private javax.swing.JRadioButton rdoSize;
    private javax.swing.JRadioButton rdoTH;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    DefaultTableModel dtm = new DefaultTableModel();
    SizeService sizeService = new SizeService();
    MauSacService mauService = new MauSacService();
    ChatLieuService chatLieuService = new ChatLieuService();
    DanhMucService danhMucService = new DanhMucService();
    ThuongHieuService thuongHieuService = new ThuongHieuService();

    private void init() {
        dtm = (DefaultTableModel) tblThuocTinh.getModel();
        loadSize();
        loadData();
    }

    private void loadData() {

        ActionListener[] size_Listeners = rdoSize.getActionListeners();
        for (ActionListener listener : size_Listeners) {
            rdoSize.removeActionListener(listener);
        }
        rdoSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
                clearForm();
            }

        });

        ActionListener[] mau_Listeners = rdoMau.getActionListeners();
        for (ActionListener listener : mau_Listeners) {
            rdoMau.removeActionListener(listener);
        }

        rdoMau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
                clearForm();
            }
        });

        ActionListener[] cl_Listeners = rdoCL.getActionListeners();
        for (ActionListener listener : cl_Listeners) {
            rdoCL.removeActionListener(listener);
        }
        rdoCL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
                clearForm();
            }
        });

        ActionListener[] dm_Listeners = rdoDM.getActionListeners();
        for (ActionListener listener : dm_Listeners) {
            rdoDM.removeActionListener(listener);
        }
        rdoDM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
                clearForm();
            }
        });

        ActionListener[] th_Listeners = rdoTH.getActionListeners();
        for (ActionListener listener : th_Listeners) {
            rdoTH.removeActionListener(listener);
        }

        rdoTH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
                clearForm();
            }
        });
    }

    //load data size
    private void loadSize() {
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (Size o : sizeService.selectAll()) {
            dtm.addRow(new Object[]{
                o.getId(),
                o.getTen()
            });
        }
    }

    //load data màu sắc
    private void loadmau() {
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (MauSac o : mauService.selectAll()) {
            dtm.addRow(new Object[]{
                o.getId(),
                o.getTen()
            });
        }
    }

    //load data chất liệu
    private void loadChatLieu() {
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (ChatLieu o : chatLieuService.selectAll()) {
            dtm.addRow(new Object[]{
                o.getId(),
                o.getTen()
            });
        }
    }

    //load data danh mục
    private void loadDanhMuc() {
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (DanhMuc o : danhMucService.selectAll()) {
            dtm.addRow(new Object[]{
                o.getId(),
                o.getTen()
            });
        }
    }

    //load data thương hiệu
    private void loadThươngHieu() {
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (ThuongHieu o : thuongHieuService.selectAll()) {
            dtm.addRow(new Object[]{
                o.getId(),
                o.getTen()
            });
        }
    }

    //rdo được chon data tương ứng được fill
    public void load() {
        if (rdoSize.isSelected()) {
            loadSize();

        }
        if (rdoMau.isSelected()) {
            loadmau();
        }
        if (rdoCL.isSelected()) {
            loadChatLieu();

        }
        if (rdoDM.isSelected()) {
            loadDanhMuc();
        }
        if (rdoTH.isSelected()) {
            loadThươngHieu();
        }

    }

    // btn add
    private void addData() {
        ActionListener[] add_Listeners = btnAdd.getActionListeners();
        for (ActionListener listener : add_Listeners) {
            btnAdd.removeActionListener(listener);
        }
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataTable();
            }

        });
    }

    // btn update
    private void updateData() {
        ActionListener[] add_Listeners = btnSua.getActionListeners();
        for (ActionListener listener : add_Listeners) {
            btnSua.removeActionListener(listener);
        }
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataTable();
            }

        });
    }

    private void addDataTable() {
        if (rdoSize.isSelected()) {
            addDataSize();

        }
        if (rdoMau.isSelected()) {
            addDataMau();
        }
        if (rdoCL.isSelected()) {
            addDataCL();

        }
        if (rdoDM.isSelected()) {
            addDataDM();
        }
        if (rdoTH.isSelected()) {
            addDataTH();
        }
    }

    //rdo chọn update
    private void updateDataTable() {
        if (rdoSize.isSelected()) {
            updateDataSize();

        }
        if (rdoMau.isSelected()) {
            updateDataMau();
        }
        if (rdoCL.isSelected()) {
            updateDataCL();

        }
        if (rdoDM.isSelected()) {
            updateDataDM();
        }
        if (rdoTH.isSelected()) {
            updateDataTH();
        }
    }

    // FULL hàm getdata
    // get data size
    public Size getFormDataSize() {
        String name = txtTen.getText().trim();

        if (name.isEmpty()) {
            MsgBox.alert(this, "Trống tên!");
            return null;
        }
        Integer sizeInteger;
        try {
            sizeInteger = Integer.valueOf(name);
            if (sizeInteger < 32 || sizeInteger > 50) {
                MsgBox.alert(this, "Size nằm trong khoảng từ 32 - 50!");
                return null;
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Vui lòng nhập số!");
            return null;
        }

        Size size = new Size(String.valueOf(sizeInteger));
        return size;
    }

    // get data màu sắc
    public MauSac getFormDataMau() {
        String name = txtTen.getText().trim();

        if (name.isEmpty()) {
            MsgBox.alert(this, "Trống tên!");
            return null;
        }
        if (!isValidName(name)) {
            MsgBox.alert(this, "Vui lòng chỉ nhập chữ!");
            return null;
        }

        MauSac m = new MauSac(name);
        return m;
    }

    // get data chat lieu
    public ChatLieu getFormDataChatLieu() {
        String name = txtTen.getText().trim();
        if (name.isEmpty()) {
            MsgBox.alert(this, "Trống tên!");
            return null;
        }
        if (!isValidName(name)) {
             MsgBox.alert(this, "Vui lòng chỉ nhập chữ");
            return null;
        }
        ChatLieu cl = new ChatLieu(name);
        return cl;
    }

    // get data danh mục
    public DanhMuc getFormDataDM() {
        String name = txtTen.getText().trim();
        if (name.isEmpty()) {
            MsgBox.alert(this, "Trống tên!");
            return null;
        }
          if (!isValidName(name)) {
             MsgBox.alert(this, "Vui lòng chỉ nhập chữ");
            return null;
        }
        DanhMuc dm = new DanhMuc(name);
        return dm;
    }

    // get data thương hiệu
    public ThuongHieu getFormDataTH() {
        String name = txtTen.getText().trim();
        if (name.isEmpty()) {
            MsgBox.alert(this, "Trống tên!");
            return null;
        }
          if (!isValidName(name)) {
             MsgBox.alert(this, "Vui lòng chỉ nhập chữ");
            return null;
        }
        ThuongHieu th = new ThuongHieu(name);
        return th;
    }

    //add data
    // size
    private void addDataSize() {
        Size s = getFormDataSize();
        if (s != null) {
            sizeService.insert(s);

            MsgBox.alert(this, "Thêm thành công!");
            loadSize();
            clearForm();
        } else {
            return;

        }
    }

    //màu
    private void addDataMau() {
        MauSac s = getFormDataMau();
        if (s != null) {
            mauService.insert(s);

            MsgBox.alert(this, "Thêm thành công!");
            loadmau();
            clearForm();
        } else {
            return;
        }
    }

    // chất liệu
    private void addDataCL() {
        ChatLieu s = getFormDataChatLieu();
        if (s != null) {
            chatLieuService.insert(s);

            MsgBox.alert(this, "Thêm thành công!");
            loadChatLieu();
            clearForm();
        } else {
            return;
        }
    }

    // danh mục
    private void addDataDM() {
        DanhMuc s = getFormDataDM();
        if (s != null) {
            danhMucService.insert(s);

            MsgBox.alert(this, "Thêm thành công!");
            loadDanhMuc();
            clearForm();
        } else {
            return;
        }
    }

    // thương hiệu
    private void addDataTH() {
        ThuongHieu s = getFormDataTH();
        if (s != null) {
            thuongHieuService.insert(s);

            MsgBox.alert(this, "Thêm thành công!");
            loadThươngHieu();
            clearForm();
        } else {
            return;
        }
    }

    //  UPDATE DATA
    //size
    private void updateDataSize() {
        int row = tblThuocTinh.getSelectedRow();
        if (row < 0) {
            MsgBox.alert(this, "Vui lòng chọn size cần sửa!");
            return;
        }
        Size s = getFormDataSize();
        s.setId(Integer.parseInt(tblThuocTinh.getValueAt(row, 0).toString()));
        if (s != null) {
            sizeService.update(s);
            MsgBox.alert(this, "Sửa thành công!");
            loadSize();
            clearForm();
        } else {
            return;
        }
    }

    //màu
    private void updateDataMau() {
        int row = tblThuocTinh.getSelectedRow();
        if (row < 0) {
            MsgBox.alert(this, "Vui lòng chọn màu cần sửa!");
            return;
        }
        MauSac m = getFormDataMau();
        m.setId(Integer.parseInt(tblThuocTinh.getValueAt(row, 0).toString()));
        if (m != null) {
            mauService.update(m);
            MsgBox.alert(this, "Sửa thành công!");
            loadmau();
            clearForm();
        } else {
            return;
        }
    }

    //chất liệu
    private void updateDataCL() {
        int row = tblThuocTinh.getSelectedRow();
        if (row < 0) {
            MsgBox.alert(this, "Vui lòng chọn chất liệu cần sửa!");
            return;
        }
        ChatLieu cl = getFormDataChatLieu();
        cl.setId(Integer.parseInt(tblThuocTinh.getValueAt(row, 0).toString()));
        if (cl != null) {
            chatLieuService.update(cl);
            MsgBox.alert(this, "Sửa thành công!");
            loadChatLieu();
            clearForm();
        } else {
            return;
        }
    }

    //danh mục
    private void updateDataDM() {
        int row = tblThuocTinh.getSelectedRow();
        if (row < 0) {
            MsgBox.alert(this, "Vui lòng chọn danh mục cần sửa!");
            return;
        }
        DanhMuc dm = getFormDataDM();
        dm.setId(Integer.parseInt(tblThuocTinh.getValueAt(row, 0).toString()));
        if (dm != null) {
            danhMucService.update(dm);
            MsgBox.alert(this, "Sửa thành công!");
            loadDanhMuc();
            clearForm();
        } else {
            return;
        }
    }

    // thương hiệu
    private void updateDataTH() {
        int row = tblThuocTinh.getSelectedRow();
        if (row < 0) {
            MsgBox.alert(this, "Vui lòng chọn thương hiệu cần sửa!");
            return;
        }
        ThuongHieu th = getFormDataTH();
        th.setId(Integer.parseInt(tblThuocTinh.getValueAt(row, 0).toString()));
        if (th != null) {
            thuongHieuService.update(th);
            MsgBox.alert(this, "Sửa thành công!");
            loadThươngHieu();
            clearForm();
        } else {
            return;
        }
    }

    private void clearForm() {
        txtTen.setText("");
    }
}
