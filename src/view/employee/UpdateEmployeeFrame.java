package view.employee;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class UpdateEmployeeFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtPosition;

    public UpdateEmployeeFrame() {
        this(new String[]{"NV001", "Nguyen Van An", "an.nv@bank.com", "Van hanh", "Giao dich vien", "EMPLOYEE", "Hoat dong"});
    }

    public UpdateEmployeeFrame(String[] data) {
        AppUi.setupFrame(this, "Sua tai khoan nhan vien", 640, 500);
        setContentPane(createContent(data));
        setVisible(true);
    }

    private JComponent createContent(String[] data) {
        JPanel page = AppUi.page("Sua tai khoan nhan vien", "Cap nhat thong tin va gui thong bao den nhan vien.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        txtId = AppUi.textField();
        txtName = AppUi.textField();
        txtEmail = AppUi.textField();
        txtPhone = AppUi.textField();
        txtPosition = AppUi.textField();

        txtId.setText(data[0]);
        txtName.setText(data[1]);
        txtEmail.setText(data[2]);
        txtPosition.setText(data[4]);

        AppUi.addField(form, 0, "Ma nhan vien", txtId);
        AppUi.addField(form, 1, "Ho ten", txtName);
        AppUi.addField(form, 2, "So dien thoai", txtPhone);
        AppUi.addField(form, 3, "Email", txtEmail);
        AppUi.addField(form, 4, "Chuc vu", txtPosition);

        JPanel actions = AppUi.toolbar();
        JButton save = AppUi.button("Cap nhat");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(save);
        actions.add(cancel);

        save.addActionListener(e -> {
            if (AppUi.requireText(this, txtId, txtName, txtEmail, txtPosition)) {
                AppUi.success(this, "Cap nhat tai khoan thanh cong. Audit Log da duoc ghi nhan.");
                dispose();
            }
        });
        cancel.addActionListener(e -> dispose());

        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);
        return page;
    }
}
