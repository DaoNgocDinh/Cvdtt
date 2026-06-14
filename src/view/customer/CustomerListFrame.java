package view.customer;

import view.common.AppUi;
import facade.TaiKhoanFacade;
import model.account.TaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerListFrame extends JFrame {

    private JTable table;

    public CustomerListFrame() {
        AppUi.setupFrame(this, "Quan ly khach hang", 1000, 620);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Quan ly khach hang", "Tao va cap nhat ho so khach hang phuc vu nghiep vu tin dung.");
        JPanel card = AppUi.card();

        JPanel toolbar = AppUi.toolbar();
        JButton create = AppUi.button("Tao khach hang");
        JButton update = AppUi.secondaryButton("Sua");
        JButton detail = AppUi.secondaryButton("Chi tiet");
        JButton delete = AppUi.dangerButton("Xoa");
        toolbar.add(create);
        toolbar.add(update);
        toolbar.add(detail);
        toolbar.add(delete);

        String[] columns = {"Ma KH", "Ho ten", "Email", "CCCD", "So dien thoai", "Dia chi", "Loai KH"};
        
        // Load data from database
        TaiKhoanFacade facade = new TaiKhoanFacade();
        List<TaiKhoan> accounts = facade.getAllTaiKhoan();
        
        Object[][] rows = new Object[accounts.size()][columns.length];
        for (int i = 0; i < accounts.size(); i++) {
            TaiKhoan account = accounts.get(i);
            rows[i][0] = account.getMaTaiKhoan();
            rows[i][1] = account.getHoTen();
            rows[i][2] = account.getEmail();
            rows[i][3] = account.getCccd();
            rows[i][4] = account.getSoDienThoai();
            rows[i][5] = ""; // Dia chi - not in model yet
            rows[i][6] = account.getChucVu(); // Using ChucVu as Loai KH
        }
        
        table = AppUi.table(columns, rows);

        create.addActionListener(e -> new CreateCustomerFrame());
        update.addActionListener(e -> {
            int row = selectedRow();
            if (row >= 0) {
                new UpdateCustomerFrame(rowData(row));
            }
        });
        detail.addActionListener(e -> {
            int row = selectedRow();
            if (row >= 0) {
                new CustomerDetailFrame(rowData(row));
            }
        });
        delete.addActionListener(e -> {
            int row = selectedRow();
            if (row >= 0 && JOptionPane.showConfirmDialog(this, "Ban co chac muon xoa khach hang nay?", "Xac nhan", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String maKh = String.valueOf(table.getValueAt(row, 0));
                facade.deleteTaiKhoan(maKh);
                ((DefaultTableModel) table.getModel()).removeRow(row);
                AppUi.success(this, "Xoa khach hang thanh cong. Audit Log da duoc ghi nhan.");
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
            JOptionPane.showMessageDialog(this, "Vui long chon mot khach hang.", "Chua chon du lieu", JOptionPane.WARNING_MESSAGE);
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
