package view.employee;

import view.common.AppUi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeeListFrame extends JFrame {

    private JTable table;

    public EmployeeListFrame() {
        AppUi.setupFrame(this, "Quan ly nhan vien", 1000, 620);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Quan ly nhan vien", "Tao tai khoan, cap nhat thong tin va khoa/mo khoa tai khoan nhan vien.");
        JPanel card = AppUi.card();

        JPanel toolbar = AppUi.toolbar();
        JButton btnCreate = AppUi.button("Tao tai khoan");
        JButton btnUpdate = AppUi.secondaryButton("Sua");
        JButton btnDetail = AppUi.secondaryButton("Chi tiet");
        JButton btnLock = AppUi.secondaryButton("Khoa/Mo khoa");
        JButton btnDelete = AppUi.dangerButton("Xoa");

        toolbar.add(btnCreate);
        toolbar.add(btnUpdate);
        toolbar.add(btnDetail);
        toolbar.add(btnLock);
        toolbar.add(btnDelete);

        String[] columns = {"Ma NV", "Ho ten", "Email", "Phong ban", "Chuc vu", "Vai tro", "Trang thai"};
        Object[][] rows = {
                {"NV001", "Nguyen Van An", "an.nv@bank.com", "Van hanh", "Giao dich vien", "EMPLOYEE", "Hoat dong"},
                {"NV002", "Tran Thi Binh", "binh.tt@bank.com", "Kiem toan", "Kiem toan vien", "AUDITOR", "Hoat dong"},
                {"NV003", "Le Minh Quan", "quan.lm@bank.com", "Quan tri", "Quan tri vien", "ADMIN", "Hoat dong"},
                {"NV004", "Pham Hoai Nam", "nam.ph@bank.com", "Tin dung", "Quan ly", "MANAGER", "Bi khoa"}
        };
        table = AppUi.table(columns, rows);

        btnCreate.addActionListener(e -> new CreateEmployeeFrame());
        btnUpdate.addActionListener(e -> openSelectedUpdate());
        btnDetail.addActionListener(e -> openSelectedDetail());
        btnLock.addActionListener(e -> toggleLock());
        btnDelete.addActionListener(e -> deleteSelected());

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private int selectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon mot nhan vien.", "Chua chon du lieu", JOptionPane.WARNING_MESSAGE);
        }
        return row;
    }

    private void openSelectedUpdate() {
        int row = selectedRow();
        if (row >= 0) {
            new UpdateEmployeeFrame(rowData(row));
        }
    }

    private void openSelectedDetail() {
        int row = selectedRow();
        if (row >= 0) {
            new EmployeeDetailFrame(rowData(row));
        }
    }

    private String[] rowData(int row) {
        String[] data = new String[table.getColumnCount()];
        for (int i = 0; i < data.length; i++) {
            data[i] = String.valueOf(table.getValueAt(row, i));
        }
        return data;
    }

    private void toggleLock() {
        int row = selectedRow();
        if (row < 0) {
            return;
        }
        String current = String.valueOf(table.getValueAt(row, 6));
        String next = current.equals("Hoat dong") ? "Bi khoa" : "Hoat dong";
        table.setValueAt(next, row, 6);
        AppUi.success(this, "Cap nhat trang thai tai khoan thanh cong. Audit Log da duoc ghi nhan.");
    }

    private void deleteSelected() {
        int row = selectedRow();
        if (row < 0) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Thao tac xoa tai khoan co rui ro trung binh. Ban co muon tiep tuc?", "Xac nhan xoa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ((DefaultTableModel) table.getModel()).removeRow(row);
            AppUi.success(this, "Xoa tai khoan thanh cong. He thong da gui thong bao noi bo.");
        }
    }
}
