package view.risk;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class RiskAlertFrame extends JFrame {

    private JTable table;

    public RiskAlertFrame() {
        AppUi.setupFrame(this, "Quan ly rui ro va canh bao", 1050, 620);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Rui ro va canh bao", "Phat hien rui ro, tao canh bao va theo doi xu ly canh bao.");
        JPanel card = AppUi.card();

        JPanel toolbar = AppUi.toolbar();
        JButton detect = AppUi.button("Phat hien rui ro");
        JButton detail = AppUi.secondaryButton("Chi tiet");
        JButton resolve = AppUi.button("Danh dau da xu ly", AppUi.SUCCESS, Color.WHITE);
        toolbar.add(detect);
        toolbar.add(detail);
        toolbar.add(resolve);

        String[] columns = {"Ma canh bao", "Ten rui ro", "Nguon", "Loai rui ro", "Noi dung", "Trang thai"};
        Object[][] rows = {
                {"CB001", "Dang nhap bat thuong", "LoginService", "Rui ro bao mat", "Dang nhap sai nhieu lan", "Moi"},
                {"CB002", "Xoa tai khoan", "EmployeeService", "Rui ro ho so", "Yeu cau xoa nhan vien co quyen cao", "Dang xu ly"},
                {"CB003", "Khoan vay lon", "LoanService", "Rui ro tin dung", "Khoan vay vuot nguong can theo doi", "Da xu ly"}
        };
        table = AppUi.table(columns, rows);

        detect.addActionListener(e -> JOptionPane.showMessageDialog(this, "Da quet xong. He thong tao canh bao mau neu phat hien rui ro.", "Phat hien rui ro", JOptionPane.INFORMATION_MESSAGE));
        detail.addActionListener(e -> {
            int row = selectedRow();
            if (row >= 0) {
                new RiskDetailFrame(rowData(row));
            }
        });
        resolve.addActionListener(e -> {
            int row = selectedRow();
            if (row >= 0) {
                table.setValueAt("Da xu ly", row, 5);
                AppUi.success(this, "Canh bao da duoc cap nhat trang thai xu ly.");
            }
        });

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private int selectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon mot canh bao.", "Chua chon du lieu", JOptionPane.WARNING_MESSAGE);
        }
        return row;
    }

    private String[] rowData(int row) {
        String[] data = new String[table.getColumnCount()];
        for (int i = 0; i < data.length; i++) {
            data[i] = String.valueOf(table.getValueAt(row, i));
        }
        return data;
    }
}
