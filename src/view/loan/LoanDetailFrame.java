package view.loan;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class LoanDetailFrame extends JFrame {

    public LoanDetailFrame() {
        this(new String[]{"VAY001", "KH001", "500,000,000", "36 thang", "9.5%", "Mua nha", "Thap", "Cho duyet"});
    }

    public LoanDetailFrame(String[] data) {
        AppUi.setupFrame(this, "Chi tiet khoan vay", 620, 500);
        setContentPane(createContent(data));
        setVisible(true);
    }

    private JComponent createContent(String[] data) {
        JPanel page = AppUi.page("Chi tiet khoan vay", "Thong tin ho so vay va trang thai xu ly.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();
        String[] labels = {"Ma vay", "Ma KH", "So tien", "Thoi han", "Lai suat", "Muc dich", "Rui ro", "Trang thai"};

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
