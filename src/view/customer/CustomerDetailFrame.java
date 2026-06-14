package view.customer;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class CustomerDetailFrame extends JFrame {

    public CustomerDetailFrame() {
        this(new String[]{"KH001", "Nguyen Thanh Tung", "tung.nt@email.com", "001203004455", "0901234567"});
    }

    public CustomerDetailFrame(String[] data) {
        AppUi.setupFrame(this, "Chi tiet khach hang", 580, 460);
        setContentPane(createContent(data));
        setVisible(true);
    }

    private JComponent createContent(String[] data) {
        JPanel page = AppUi.page("Chi tiet khach hang", "Thong tin ho so khach hang trong he thong.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();
        String[] labels = {"Ma KH", "Ho ten/Ten cong ty", "Email", "CCCD/MST", "So dien thoai"};

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
