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
        toolbar.add(create);

        String[] columns = {"Ma KH", "Ho ten", "Email", "CCCD", "So dien thoai"};
        table = AppUi.table(columns, new Object[0][0]);
        refreshTable();

        create.addActionListener(e -> new CreateCustomerFrame(this::refreshTable));

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private void refreshTable() {
        String[] columns = {"Ma KH", "Ho ten", "Email", "CCCD", "So dien thoai"};
        TaiKhoanFacade facade = new TaiKhoanFacade();
        java.util.List<TaiKhoan> allAccounts = facade.getAllTaiKhoan();
        java.util.List<TaiKhoan> customers = new java.util.ArrayList<>();
        for (TaiKhoan account : allAccounts) {
            if (account.getRoleName() != null && account.getRoleName().equalsIgnoreCase("KHACHHANG")) {
                customers.add(account);
            }
        }

        Object[][] rows = new Object[customers.size()][columns.length];
        for (int i = 0; i < customers.size(); i++) {
            TaiKhoan account = customers.get(i);
            rows[i][0] = account.getMaTaiKhoan();
            rows[i][1] = account.getHoTen();
            rows[i][2] = account.getEmail();
            rows[i][3] = account.getCccd();
            rows[i][4] = account.getSoDienThoai();
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(rows, columns);
    }
}
