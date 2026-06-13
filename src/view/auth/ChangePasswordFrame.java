package view.auth;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirm;

    public ChangePasswordFrame() {
        AppUi.setupFrame(this, "Dat lai mat khau", 560, 420);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Dat lai mat khau", "Nhap email da dang ky va mat khau moi.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        txtEmail = AppUi.textField();
        txtPassword = new JPasswordField();
        txtConfirm = new JPasswordField();

        AppUi.addField(form, 0, "Email", txtEmail);
        AppUi.addField(form, 1, "Mat khau moi", txtPassword);
        AppUi.addField(form, 2, "Nhap lai mat khau", txtConfirm);

        JPanel actions = AppUi.toolbar();
        JButton save = AppUi.button("Dat lai mat khau");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(save);
        actions.add(cancel);

        save.addActionListener(e -> resetPassword());
        cancel.addActionListener(e -> dispose());

        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private void resetPassword() {
        if (!AppUi.requireText(this, txtEmail)) {
            return;
        }
        String password = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirm.getPassword());
        if (password.isEmpty() || !password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Mat khau xac nhan khong khop.", "Du lieu khong hop le", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AppUi.success(this, "Dat lai mat khau thanh cong. Hay quay lai man hinh dang nhap.");
        dispose();
    }
}
