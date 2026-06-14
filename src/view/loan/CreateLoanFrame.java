package view.loan;

import facade.VayFacade;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateLoanFrame extends JFrame {

    private JTextField txtMaKhoanVay;
    private JTextField txtMaTaiKhoan;
    private JTextField txtSoTienVay;
    private JTextField txtHanTraNo;

    private VayFacade facade;

    public CreateLoanFrame(VayFacade facade) {

        this.facade = facade;

        setTitle("QUẢN LÝ KHOẢN VAY");
        setSize(450, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panel.add(new JLabel("Mã khoản vay:"));
        txtMaKhoanVay = new JTextField();
        panel.add(txtMaKhoanVay);

        panel.add(new JLabel("Mã tài khoản:"));
        txtMaTaiKhoan = new JTextField();
        panel.add(txtMaTaiKhoan);

        panel.add(new JLabel("Số tiền vay:"));
        txtSoTienVay = new JTextField();
        panel.add(txtSoTienVay);

        panel.add(new JLabel("Hạn trả (yyyy-mm-dd):"));
        txtHanTraNo = new JTextField();
        panel.add(txtHanTraNo);

        // =========================
        // MESSAGE (ĐƯA LÊN TRÊN BUTTON)
        // =========================
        JLabel result = new JLabel(" ");
        result.setFont(new Font("Arial", Font.BOLD, 12));
        result.setHorizontalAlignment(SwingConstants.CENTER);

        // =========================
        // BUTTON
        // =========================
        JButton btnCreate = new JButton("TẠO KHOẢN VAY");
        btnCreate.setBackground(new Color(0, 153, 76));
        btnCreate.setForeground(Color.WHITE);
        btnCreate.setFocusPainted(false);

        btnCreate.addActionListener(e -> {

            try {
                if (txtMaKhoanVay.getText().trim().isEmpty()
                        || txtMaTaiKhoan.getText().trim().isEmpty()
                        || txtSoTienVay.getText().trim().isEmpty()
                        || txtHanTraNo.getText().trim().isEmpty()) {

                    showError(result, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                BigDecimal soTien;
                try {
                    soTien = new BigDecimal(txtSoTienVay.getText().trim());
                } catch (Exception ex) {
                    showError(result, "Số tiền không hợp lệ");
                    return;
                }

                LocalDate hanTra;
                try {
                    hanTra = LocalDate.parse(txtHanTraNo.getText().trim());
                } catch (Exception ex) {
                    showError(result, "Ngày không đúng định dạng yyyy-mm-dd");
                    return;
                }

                LocalDate ngayVay = LocalDate.now();

                facade.createVay(
                        txtMaKhoanVay.getText().trim(),
                        txtMaTaiKhoan.getText().trim(),
                        soTien,
                        ngayVay,
                        hanTra
                );

                showSuccess(result, "Tạo khoản vay thành công");
                btnCreate.setEnabled(false);

// reset form (tuỳ chọn nhưng nên có)
                txtMaKhoanVay.setText("");
                txtMaTaiKhoan.setText("");
                txtSoTienVay.setText("");
                txtHanTraNo.setText("");

// tự ẩn message + bật lại nút sau 2 giây
                new javax.swing.Timer(2000, ev -> {
                    result.setText(" ");
                    btnCreate.setEnabled(true);
                }).start();

            } catch (Exception ex) {
                showError(result, ex.getMessage());
            }
        });

        // =========================
        // LAYOUT BUTTON + MESSAGE
        // =========================
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        bottom.add(result);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(btnCreate);

        add(panel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    // =========================
    // HELPER UI METHODS
    // =========================
    private void showError(JLabel label, String msg) {
        label.setForeground(Color.RED);
        label.setText("✖ " + msg);
    }

    private void showSuccess(JLabel label, String msg) {
        label.setForeground(new Color(0, 150, 0));
        label.setText("✔ " + msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new CreateLoanFrame(new VayFacade()).setVisible(true)
        );
    }
}