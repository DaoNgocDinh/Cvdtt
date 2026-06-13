package view.customer;

import view.common.AppUi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
        Object[][] rows = {
                {"KH001", "Nguyen Thanh Tung", "tung.nt@email.com", "001203004455", "0901234567", "Ha Noi", "Ca nhan"},
                {"KH002", "Cong ty Minh Phat", "contact@minhphat.vn", "010203040506", "0912345678", "Da Nang", "Doanh nghiep"},
                {"KH003", "Le Thu Ha", "ha.lt@email.com", "079203009911", "0987654321", "TP HCM", "Ca nhan"}
        };
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
