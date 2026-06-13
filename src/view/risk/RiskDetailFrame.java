package view.risk;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class RiskDetailFrame extends JFrame {

    public RiskDetailFrame() {
        this(new String[]{"CB001", "Dang nhap bat thuong", "LoginService", "Cao", "Dang nhap sai nhieu lan", "Moi"});
    }

    public RiskDetailFrame(String[] data) {
        AppUi.setupFrame(this, "Chi tiet canh bao rui ro", 640, 500);
        setContentPane(createContent(data));
        setVisible(true);
    }

    private JComponent createContent(String[] data) {
        JPanel page = AppUi.page("Chi tiet canh bao", "Theo doi nguyen nhan, muc do va huong xu ly.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();
        String[] labels = {"Ma canh bao", "Loai rui ro", "Nguon", "Muc do", "Noi dung", "Trang thai"};

        for (int i = 0; i < labels.length; i++) {
            JLabel value = new JLabel(data[i]);
            value.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            value.setForeground(AppUi.TEXT);
            AppUi.addField(form, i, labels[i], value);
        }

        JTextArea note = AppUi.textArea(4);
        note.setText("De xuat: kiem tra Audit Log, xac minh nguoi dung lien quan va gui thong bao den bo phan an ninh.");
        AppUi.addField(form, 6, "Huong xu ly", new JScrollPane(note));

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
