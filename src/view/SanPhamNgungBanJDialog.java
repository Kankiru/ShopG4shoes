package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.SanPham;
import model.SanPhamChiTiet;
import repository.MsgBox;
import repository.XImage;
import service.SanPhamChiTietService;
import service.SanPhamService;

/**
 *
 * @author admin
 */
public class SanPhamNgungBanJDialog extends javax.swing.JDialog {

    /**
     * Creates new form SanPhamNgungBanJDialog
     */
    private final SanPhamChiTietService spctService = new SanPhamChiTietService();
    private final SanPhamService spService = new SanPhamService();
    private boolean check;

    public SanPhamNgungBanJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        fillTable();
        setTitle("Sản phẩm ngưng bán");
        setIconImage(XImage.getAppIcon2()); //logo ứng dụng
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblSP.getModel();
        model.setRowCount(0);

        try {
            ArrayList<SanPhamChiTiet> list = (ArrayList<SanPhamChiTiet>) spctService.selectAll();
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

    private void update() {
        DefaultTableModel model = (DefaultTableModel) tblSP.getModel();
        int row = tblSP.getSelectedRow();
        check = MsgBox.confirm(this, "Bạn có muốn hiển thị lại sản phẩm này không?");
        if (row < 0) {
            MsgBox.alert(this, "Vui lòng chọn sản phẩm cần hiển thị!");
            return;
        }
        try {
            if (check == true) {
                String idSP = tblSP.getValueAt(row, 0).toString();
                spctService.updateStatus(idSP);
                MsgBox.alert(this, "Đã hiển thị!");
                fillTable();
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Không thành công!");
            return ;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHienThiLai = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnHienThiLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eye2.png"))); // NOI18N
        btnHienThiLai.setText("HIển thị lại");
        btnHienThiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiLaiActionPerformed(evt);
            }
        });

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "Mã NV", "Tên SP", "Giá", "Số lượng", "Size", "Màu sắc", "Chất liệu", "Danh mục", "Thương hiệu", "Trạng thái"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("SẢN PHẨM NGỪNG BÁN ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnHienThiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHienThiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblSPMouseClicked

    private void btnHienThiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiLaiActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnHienThiLaiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SanPhamNgungBanJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamNgungBanJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamNgungBanJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamNgungBanJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SanPhamNgungBanJDialog dialog = new SanPhamNgungBanJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHienThiLai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSP;
    // End of variables declaration//GEN-END:variables
}
