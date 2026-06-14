package view.employee;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class EmployeeDetailFrame extends JFrame {

    public EmployeeDetailFrame() {
        this(new String[]{"NV001", "Nguyen Van An", "an.nv@bank.com", "Giao dich vien", "EMPLOYEE", "Hoat dong"});
    }

    public EmployeeDetailFrame(String[] data) {
        AppUi.setupFrame(this, "Chi tiet nhan vien", 560, 440);
        setContentPane(createContent(data));
        setVisible(true);
    }

    private JComponent createContent(String[] data) {
        JPanel page = AppUi.page("Chi tiet nhan vien", "Thong tin tai khoan va trang thai truy cap.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        String[] labels = {"Ma nhan vien", "Ho ten", "Email", "Chuc vu", "Vai tro", "Trang thai"};
        for (int i = 0; i < labels.length; i++) {
            JLabel value = new JLabel(data[i]);
            value.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            value.setForeground(AppUi.TEXT);
            AppUi.addField(form, i, labels[i], value);
        }

        JButton close = AppUi.button("Dong");
        close.addActionListener(e -> dispose());
        JPanel actions = AppUi.toolbar();
        actions.add(close);

        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);
        return page;
    }
}
