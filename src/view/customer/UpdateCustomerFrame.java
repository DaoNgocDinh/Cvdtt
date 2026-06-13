package view.customer;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class UpdateCustomerFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtIdentity;
    private JTextField txtPhone;
    private JTextField txtAddress;

    public UpdateCustomerFrame() {
        this(new String[]{"KH001", "Nguyen Thanh Tung", "tung.nt@email.com", "001203004455", "0901234567", "Ha Noi", "Ca nhan"});
    }

    public UpdateCustomerFrame(String[] data) {
        AppUi.setupFrame(this, "Sua khach hang", 640, 520);
        setContentPane(createContent(data));
        setVisible(true);
    }

    private JComponent createContent(String[] data) {
        JPanel page = AppUi.page("Sua khach hang", "Cap nhat thong tin ho so khach hang.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        txtId = AppUi.textField();
        txtName = AppUi.textField();
        txtEmail = AppUi.textField();
        txtIdentity = AppUi.textField();
        txtPhone = AppUi.textField();
        txtAddress = AppUi.textField();

        txtId.setText(data[0]);
        txtName.setText(data[1]);
        txtEmail.setText(data[2]);
        txtIdentity.setText(data[3]);
        txtPhone.setText(data[4]);
        txtAddress.setText(data[5]);

        AppUi.addField(form, 0, "Ma khach hang", txtId);
        AppUi.addField(form, 1, "Ho ten/Ten cong ty", txtName);
        AppUi.addField(form, 2, "Email", txtEmail);
        AppUi.addField(form, 3, "CCCD/MST", txtIdentity);
        AppUi.addField(form, 4, "So dien thoai", txtPhone);
        AppUi.addField(form, 5, "Dia chi", txtAddress);

        JPanel actions = AppUi.toolbar();
        JButton save = AppUi.button("Cap nhat");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(save);
        actions.add(cancel);

        save.addActionListener(e -> {
            if (AppUi.requireText(this, txtId, txtName, txtEmail, txtIdentity, txtPhone)) {
                AppUi.success(this, "Cap nhat khach hang thanh cong. Audit Log da duoc ghi nhan.");
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
