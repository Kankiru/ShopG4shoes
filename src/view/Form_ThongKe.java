package view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.DoanhThuNam;
import model.DoanhThuNgay;
import model.DoanhThuThang;
import model.SanPhamBanChay;
import model.ThongKe;
import model.TongHoaDon;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import repository.MsgBox;
import service.DoanhThuNamService;
import service.DoanhThuNgayService;
import service.DoanhThuThangService;
import service.SanPhamBanChayService;
import service.ThongKeService;
import service.TongHoaDonService;

public class Form_ThongKe extends javax.swing.JPanel {

    DoanhThuNgayService doanhThuNgayService = new DoanhThuNgayService();
    DoanhThuThangService doanhThuThangService = new DoanhThuThangService();
    DoanhThuNamService doanhThuNamService = new DoanhThuNamService();
    TongHoaDonService tongHoaDonService = new TongHoaDonService();
    SanPhamBanChayService sanPhamBanChayService = new SanPhamBanChayService();
    ThongKeService thongKeService = new ThongKeService();
    DoanhThuNgay doanhThuNgay = new DoanhThuNgay();
    DoanhThuThang doanhThuThang = new DoanhThuThang();
    DoanhThuNam doanhThuNam = new DoanhThuNam();
    TongHoaDon tongHoaDon = new TongHoaDon();
    SanPhamBanChay sanPhamBanChay = new SanPhamBanChay();
    DefaultTableModel dtm = new DefaultTableModel();

    public Form_ThongKe() {
        initComponents();
        dtm = (DefaultTableModel) tbSanPham.getModel();
        List<DoanhThuThang> dataListThang = null;
        List<DoanhThuNgay> dataListNgay = null;
        List<DoanhThuNam> dataListNam = null;
        List<TongHoaDon> dataListDonHang = null;

        List<DoanhThuNam> timNam = null;
        List<TongHoaDon> timHD = null;
        try {
            dataListThang = doanhThuThangService.showDataDoanhThuThang();
            dataListNgay = doanhThuNgayService.showDataDoanhThuNgay();
            dataListNam = doanhThuNamService.showDataDoanhThuNam();
            dataListDonHang = tongHoaDonService.showDataTongDonHang();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            showData(sanPhamBanChayService.showDataSanPhamBanChay());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tongDonHang(dataListDonHang);
        doanhThuNgay(dataListNgay);
        doanhThuThang(dataListThang);
        doanhThuNam(dataListNam);
    }

    private void guiBaoCao() throws SQLException {
        List<DoanhThuThang> dataListThang = null;
        List<DoanhThuNgay> dataListNgay = null;
        List<DoanhThuNam> dataListNam = null;
        List<TongHoaDon> dataListDonHang = null;
        dataListThang = doanhThuThangService.showDataDoanhThuThang();
        dataListNgay = doanhThuNgayService.showDataDoanhThuNgay();
        dataListNam = doanhThuNamService.showDataDoanhThuNam();
        dataListDonHang = tongHoaDonService.showDataTongDonHang();
        baoCao1(dataListNgay, dataListThang, dataListNam, dataListDonHang);
    }

    private void sendPin(String toEmail, String subject, String body) {

        final String fromEmail = "g4shoessrote1@gmail.com";
        final String password = "land frgh etav opry";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

            Transport.send(mimeMessage);
            System.out.println("Đã gửi email thành công.");
        } catch (MessagingException e) {
             e.printStackTrace();
             System.out.println("Lỗi khi gửi email. Vui lòng thử lại.");
        }
    }

    public void baoCao1(List<DoanhThuNgay> listNgay, List<DoanhThuThang> doanhThuThangs, List<DoanhThuNam> doanhThuNams, List<TongHoaDon> tongHoaDons) {
        StringBuilder noiDungBaoCao = new StringBuilder();

        for (TongHoaDon tongHoaDon1 : tongHoaDons) {
            noiDungBaoCao.append("Tổng Hóa Đơn: ").append(tongHoaDon1.getTonghoadon()).append("\n");
        }

        for (DoanhThuNgay doanhThuNgay : listNgay) {
            double tien = doanhThuNgay.getDoanhthungay();
            NumberFormat dinhDangSo = NumberFormat.getInstance();
            String soDaDinhDang = dinhDangSo.format(tien);
            noiDungBaoCao.append("Doanh thu ngày là: ").append(soDaDinhDang).append(" VND\n");
        }

        for (DoanhThuThang doanhThuThang : doanhThuThangs) {
            double tien = doanhThuThang.getDoanhthuthang();
            NumberFormat dinhDangSo = NumberFormat.getInstance();
            String soDaDinhDang = dinhDangSo.format(tien);
            noiDungBaoCao.append("Doanh thu tháng là: ").append(soDaDinhDang).append(" VND\n");
        }

        for (DoanhThuNam doanhThuNam : doanhThuNams) {
            double tien = doanhThuNam.getDoanhthunam();
            NumberFormat dinhDangSo = NumberFormat.getInstance();
            String soDaDinhDang = dinhDangSo.format(tien);
            noiDungBaoCao.append("Doanh thu năm là: ").append(soDaDinhDang).append(" VND\n");
        }

        // Gọi hàm sendPin để gửi email
        sendPin("minhdac2911@gmail.com", "Báo cáo doanh thu", noiDungBaoCao.toString());
    }

    public void doanhThuNgay(List<DoanhThuNgay> listData) {
        for (DoanhThuNgay doanhThuNgay1 : listData) {
            double tien = doanhThuNgay1.getDoanhthungay();
            NumberFormat numberFormat = NumberFormat.getInstance();
            String soDaDinhDang = numberFormat.format(tien);
            txtDoanhThuNgay.setText(soDaDinhDang + " VND");
        }
    }

    public void timDoanhThuNgay(List<DoanhThuNgay> listData) {
        for (DoanhThuNgay doanhThuNgay1 : listData) {
            double tien = doanhThuNgay1.getDoanhthungay();
            doanhThuNgay1.getHoadon();
            NumberFormat numberFormat = NumberFormat.getInstance();
            String soDaDinhDang = numberFormat.format(tien);
            MsgBox.alert(this, "Tổng Doanh Thu Ngày: " + soDaDinhDang + " VND" + "\n" + "Số hoá đơn: " + doanhThuNgay1.getHoadon() + " hoá đơn");
        }
    }

    public void doanhThuThang(List<DoanhThuThang> doanhThuThangs) {
        for (DoanhThuThang doanhThuThang1 : doanhThuThangs) {
            double tien = doanhThuThang1.getDoanhthuthang();
            NumberFormat numberFormat = NumberFormat.getInstance();
            String soDaDinhDang = numberFormat.format(tien);
            txtDoanhThuThang.setText(soDaDinhDang + " VND");
        }
    }

    public void timDoanhThuThang(List<ThongKe> thongKes) {
        for (ThongKe thongKe : thongKes) {
            double tien = thongKe.getTongtien();
            NumberFormat numberFormat = NumberFormat.getInstance();
            String soDaDinhDang = numberFormat.format(tien);
            MsgBox.alert(this, "Tổng Doanh Thu Là: " + soDaDinhDang + " VND" + " Và Có " + thongKe.getTonghoadon() + " Hóa Ðon");

        }
    }

    public void doanhThuNam(List<DoanhThuNam> doanhThuNams) {
        for (DoanhThuNam doanhThuNam1 : doanhThuNams) {
            double tien = doanhThuNam1.getDoanhthunam();
            NumberFormat numberFormat = NumberFormat.getInstance();
            String soDaDinhDang = numberFormat.format(tien);
            txtDoanhThuNam.setText(soDaDinhDang + " VND");
        }
    }

    public void tongDonHang(List<TongHoaDon> tongHoaDons) {
        for (TongHoaDon tongHoaDon1 : tongHoaDons) {
            txtTongDon.setText(tongHoaDon1.getTonghoadon() + " Đơn Hàng");
        }

    }

    public void showData(List<SanPhamBanChay> list) {
        dtm.setRowCount(0);
        int stt = 1;
        for (SanPhamBanChay sanPhamBanChay1 : list) {
            Object data[] = {
                stt++,
                sanPhamBanChay1.getIdsp(),
                sanPhamBanChay1.getMasp(),
                sanPhamBanChay1.getTensp(),
                sanPhamBanChay1.getSlban(),
                sanPhamBanChay1.getTongtien()
            };
            dtm.addRow(data);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtDoanhThuNgay = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtDoanhThuThang = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtDoanhThuNam = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTongDon = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        btnBaoCao = new javax.swing.JButton();
        btnXuatBaoCao = new javax.swing.JButton();
        btnTimDate = new javax.swing.JButton();
        dateDen = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        DateTu = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1008, 620));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(74, 150, 226));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tổng Doanh Thu Ngày");

        txtDoanhThuNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDoanhThuNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuNgay.setText("100000");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtDoanhThuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 33, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDoanhThuNgay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(181, 96));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tổng Doanh Thu Tháng");

        txtDoanhThuThang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDoanhThuThang.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuThang.setText("10000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtDoanhThuThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDoanhThuThang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 206, 229));
        jPanel5.setPreferredSize(new java.awt.Dimension(181, 96));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tổng Doanh Thu Năm");

        txtDoanhThuNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDoanhThuNam.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuNam.setText("100000");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtDoanhThuNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDoanhThuNam)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(181, 96));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tổng Hóa Đơn");

        txtTongDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongDon.setForeground(new java.awt.Color(255, 255, 255));
        txtTongDon.setText("100000");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtTongDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongDon)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sản Phẩm"));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Sản Phẩm", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Bán", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbSanPham);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        btnBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/send_report.png"))); // NOI18N
        btnBaoCao.setText("Báo Cáo Doanh Thu");
        btnBaoCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBaoCaoMouseClicked(evt);
            }
        });
        btnBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoCaoActionPerformed(evt);
            }
        });

        btnXuatBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/export.png"))); // NOI18N
        btnXuatBaoCao.setText("Xuất báo cáo");
        btnXuatBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBaoCaoActionPerformed(evt);
            }
        });

        btnTimDate.setText("search");
        btnTimDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimDateActionPerformed(evt);
            }
        });

        dateDen.setBackground(new java.awt.Color(255, 255, 255));
        dateDen.setDateFormatString("yyyy-MM-dd");
        dateDen.setPreferredSize(new java.awt.Dimension(116, 26));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Đến ngày");

        DateTu.setBackground(new java.awt.Color(255, 255, 255));
        DateTu.setDateFormatString("yyyy-MM-dd");
        DateTu.setPreferredSize(new java.awt.Dimension(116, 26));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Từ ngày");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatBaoCao)
                .addGap(18, 18, 18)
                .addComponent(btnBaoCao)
                .addGap(73, 73, 73))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(DateTu, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(dateDen, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimDate))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(52, 52, 52)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateDen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimDate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
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

    private void btnBaoCaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBaoCaoMouseClicked
        try {
            // TODO add your handling code here:
            guiBaoCao();
            MsgBox.alert(this, "Báo Cáo Thành Công!");
        } catch (SQLException ex) {
            MsgBox.alert(this, "Lỗi");
            Logger.getLogger(Form_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBaoCaoMouseClicked

    private void btnBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoCaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBaoCaoActionPerformed

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed
        // TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Học Viên");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("ID Sản Phẩm");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã Sản Phẩm");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tên Sản Phẩm");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("SL bán");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tổng tiền");

            ArrayList<SanPhamBanChay> list = (ArrayList<SanPhamBanChay>) sanPhamBanChayService.showDataSanPhamBanChay();
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    SanPhamBanChay spbc = list.get(i);
                    row = sheet.createRow(4 + i);
                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i + 1);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(spbc.getIdsp());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(spbc.getMasp());

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(spbc.getTensp());

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(spbc.getSlban());

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(spbc.getTongtien());

                }
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn nơi lưu tệp Excel");

                fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try (FileOutputStream fos = new FileOutputStream(fileToSave.getAbsolutePath() + ".xlsx")) {
                        workbook.write(fos);
                        MsgBox.alert(this, "Xuất báo cáo thành công!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void btnTimDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimDateActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String start = sdf.format(DateTu.getDate());
        String end = sdf.format(dateDen.getDate());

        List<ThongKe> ListThongKe;
        ListThongKe = null;
        try {
            System.out.println(start);

            System.out.println(start);
            ListThongKe = thongKeService.searchDataMa(start, end);

            timDoanhThuThang(ListThongKe);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnTimDateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateTu;
    private javax.swing.JButton btnBaoCao;
    private javax.swing.JButton btnTimDate;
    private javax.swing.JButton btnXuatBaoCao;
    private com.toedter.calendar.JDateChooser dateDen;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JLabel txtDoanhThuNam;
    private javax.swing.JLabel txtDoanhThuNgay;
    private javax.swing.JLabel txtDoanhThuThang;
    private javax.swing.JLabel txtTongDon;
    // End of variables declaration//GEN-END:variables
}
