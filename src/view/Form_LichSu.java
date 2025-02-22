package view;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import repository.MsgBox;
import service.HoaDonChiTietService;
import service.HoaDonService;

public class Form_LichSu extends javax.swing.JPanel {

    HoaDonService hds = new HoaDonService();
    HoaDonChiTietService hdctService = new HoaDonChiTietService();
    DefaultTableModel model = new DefaultTableModel();
    private int currentPage = 1;
    private int itemsPage = 10;
    private int totalPages;

    public Form_LichSu() {
        initComponents();

        fillTableHD();
        model = (DefaultTableModel) tblHoaDon.getModel();
        totalPages = tinhTrang();
        updatePage();
        JTextFieldDateEditor dateStart = (JTextFieldDateEditor) jdcStart.getDateEditor();
        dateStart.setEditable(false);
        dateStart.setBackground(new Color(204, 204, 204));
        JTextFieldDateEditor dateEnd = (JTextFieldDateEditor) jdcEnd.getDateEditor();
        dateEnd.setEditable(false);
        dateEnd.setBackground(new Color(204, 204, 204));
        loadTitleText();
    }

    private void fillTableHD() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        int stt = 1;
        try {
            List<HoaDon> list = hds.selectAll();
            for (int i = 0; i < list.size(); i++) {
                HoaDon hd = list.get(i);
                model.addRow(new Object[]{
                    stt++,
                    hd.getMa(),
                    hd.getNgayTao(),
                    hd.getNv().getTen(),
                    hd.getKh().getSdt(),
                    hd.getKh().getTen(),
                    hd.getTongTien(),
                    hd.trangThaiString(),
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
//            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private int tinhTrang() {
        int totalItems = hds.count();
        int totalPages = totalItems / itemsPage;
        if (totalItems % itemsPage != 0) {
            totalPages++;
        }
        return totalPages;
    }

    private void updatePage() {
        lblNumberPage.setText(currentPage + "/" + totalPages);
    }

    private void fillTableHDCT(HoaDon hoaDon) {
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();
        model.setRowCount(0);

        try {
            List<HoaDonChiTiet> list = hdctService.selectByMaHD(hoaDon.getMa());
            int i = 1;
            for (HoaDonChiTiet hdct : list) {
                model.addRow(new Object[]{
                    i++,
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        lblNumberPage = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTTHD = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jdcStart = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdcEnd = new com.toedter.calendar.JDateChooser();
        search = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Lịch Sử Giao Dịch");

        jLabel2.setText("Search");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Ngày tạo", "Người tạo", "Tên khách hàng", "SĐT", "Thành tiền", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(tblHoaDon);

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

        lblNumberPage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNumberPage.setText("1/2");

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

        tblTTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID", "Mã SP", "Tên SP", "Size", "Màu sắc", "Chất liệu", "Giá", "Số lượng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblTTHD);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Hoá đơn");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Thông tin hoá đơn");

        jdcStart.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Từ ngày");

        jLabel8.setText("Đến ngày");

        jdcEnd.setBackground(new java.awt.Color(255, 255, 255));

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jdcStart, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(search))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFirst)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPrev)
                                .addGap(30, 30, 30)
                                .addComponent(lblNumberPage)
                                .addGap(26, 26, 26)
                                .addComponent(btnNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLast))
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jdcStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdcEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFirst)
                        .addComponent(btnPrev))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumberPage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext)
                        .addComponent(btnLast)))
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
        updatePage();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
        updatePage();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
        updatePage();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
        updatePage();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 1);
        HoaDon hoaDon = hds.selectByMa(maHD);
        this.fillTableHDCT(hoaDon);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        if (jdcStart.getDate() == null || jdcEnd.getDate() == null) {
            MsgBox.alert(this, "Vui lòng chọn ngày bắt dấu và ngày kết thúc!");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStart = jdcStart.getDate();
        Date dateEnd = jdcEnd.getDate();

        if (dateStart.after(dateEnd)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không thể lớn hơn ngày kết thúc!");
            txtSearch.setText(" ");
            return;
        }
        String d1 = sdf.format(jdcStart.getDate());
        String d2 = sdf.format(jdcEnd.getDate());
        txtSearch.setText("");
        showData(d1, d2);
    }//GEN-LAST:event_searchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

        search();
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcEnd;
    private com.toedter.calendar.JDateChooser jdcStart;
    private javax.swing.JLabel lblNumberPage;
    private javax.swing.JButton search;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblTTHD;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void first() {
        currentPage = 1;
        fillTableHD();
        updatePage();
    }

    private void prev() {
        if (currentPage > 1) {
            currentPage--;
            fillTableHD();
        }
    }

    private void next() {
        if (currentPage < totalPages) {
            currentPage++;
            fillTableHD();
        }
    }

    private void last() {
        currentPage = totalPages;
        fillTableHD();
    }

    private void showData(String d1, String d2) {
        ArrayList<HoaDon> list = (ArrayList<HoaDon>) hds.searchDate(d1, d2);
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        if (list.isEmpty()) {
            MsgBox.alert(this, "Không có dữ liệu");
            jdcEnd.setDate(null);
            jdcStart.setDate(null);
            return;
        }
        model.setRowCount(0);
        try {
            int stt = 1;
            for (int i = 0; i < list.size(); i++) {
                HoaDon hd = list.get(i);
                
                model.addRow(new Object[]{
                    stt++,
                    hd.getMa(),
                    hd.getNgayTao(),
                    hd.getNv(),
                    hd.getKh(),
                    hd.getTongTien(),
                    hd.trangThaiString()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void search() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            String key = txtSearch.getText();
                            int stt = 1;
            List<HoaDon> list = hds.searchHD(key);
            for (int i = 0; i < list.size(); i++) {
                HoaDon hd = list.get(i);

                model.addRow(new Object[]{
                    stt++,
                    hd.getMa(),
                    hd.getNgayTao(),
                    hd.getNv().getTen(),
                    hd.getKh().getSdt(),
                    hd.getTongTien(),
                   hd.trangThaiString()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void loadTitleText() {

        // txtSearch.setText("Mã HD, người tạo, SDT");
        txtSearch.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Mã HD, Người tạo, SDT")) {
                    txtSearch.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText("Mã HD, Người tạo, SDT");
                }
            }
        });
    }
}
