package view.employee;

import facade.TaiKhoanFacade;
import model.account.TaiKhoan;
import view.common.AppUi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        table = AppUi.table(columns, new Object[0][0]);
        refreshTable();

        btnCreate.addActionListener(e -> new CreateEmployeeFrame(this::refreshTable));
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
            new UpdateEmployeeFrame(this::refreshTable, rowData(row));
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
        String maNV = String.valueOf(table.getValueAt(row, 0));
        TaiKhoanFacade facade = new TaiKhoanFacade();
        TaiKhoan account = facade.getTaiKhoanById(maNV);
        if (account != null) {
            account.setLocker(!Boolean.TRUE.equals(account.getLocker()));
            facade.updateTaiKhoan(account);
            refreshTable();
            AppUi.success(this, "Cap nhat trang thai tai khoan thanh cong. Audit Log da duoc ghi nhan.");
        }
    }

    private void deleteSelected() {
        int row = selectedRow();
        if (row < 0) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Thao tac xoa tai khoan co rui ro trung binh. Ban co muon tiep tuc?", "Xac nhan xoa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String maNV = String.valueOf(table.getValueAt(row, 0));
            TaiKhoanFacade facade = new TaiKhoanFacade();
            facade.deleteTaiKhoan(maNV);
            refreshTable();
            AppUi.success(this, "Xoa tai khoan thanh cong. He thong da gui thong bao noi bo.");
        }
    }

    private void refreshTable() {
        String[] columns = {"Ma NV", "Ho ten", "Email", "Phong ban", "Chuc vu", "Vai tro", "Trang thai"};
        TaiKhoanFacade facade = new TaiKhoanFacade();
        java.util.List<TaiKhoan> allAccounts = facade.getAllTaiKhoan();
        java.util.List<TaiKhoan> employees = new ArrayList<>();
        for (TaiKhoan account : allAccounts) {
            if (account.getRoleName() != null && !account.getRoleName().equalsIgnoreCase("KHACHHANG")) {
                employees.add(account);
            }
        }

        Object[][] rows = new Object[employees.size()][columns.length];
        for (int i = 0; i < employees.size(); i++) {
            TaiKhoan account = employees.get(i);
            rows[i][0] = account.getMaTaiKhoan();
            rows[i][1] = account.getHoTen();
            rows[i][2] = account.getEmail();
            rows[i][3] = "";
            rows[i][4] = account.getChucVu();
            rows[i][5] = account.getRoleName();
            rows[i][6] = Boolean.TRUE.equals(account.getLocker()) ? "Bi khoa" : "Hoat dong";
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(rows, columns);
    }
}
