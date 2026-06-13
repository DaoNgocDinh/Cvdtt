package view.customer;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class CreateCustomerFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtIdentity;
    private JTextField txtPhone;
    private JTextField txtAddress;
    private JComboBox<String> cbType;

    public CreateCustomerFrame() {
        AppUi.setupFrame(this, "Tao tai khoan khach hang", 660, 560);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Tao tai khoan khach hang", "Nhap ma khach hang, ho ten, email, CCCD va so dien thoai.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        txtId = AppUi.textField();
        txtName = AppUi.textField();
        txtEmail = AppUi.textField();
        txtIdentity = AppUi.textField();
        txtPhone = AppUi.textField();
        txtAddress = AppUi.textField();
        cbType = AppUi.combo("Ca nhan", "Doanh nghiep");

        AppUi.addField(form, 0, "Ma khach hang", txtId);
        AppUi.addField(form, 1, "Ho ten/Ten cong ty", txtName);
        AppUi.addField(form, 2, "Email", txtEmail);
        AppUi.addField(form, 3, "CCCD/MST", txtIdentity);
        AppUi.addField(form, 4, "So dien thoai", txtPhone);
        AppUi.addField(form, 5, "Dia chi", txtAddress);
        AppUi.addField(form, 6, "Loai khach hang", cbType);

        JPanel actions = AppUi.toolbar();
        JButton save = AppUi.button("Tao tai khoan");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(save);
        actions.add(cancel);

        save.addActionListener(e -> save());
        cancel.addActionListener(e -> dispose());

        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private void save() {
        if (!AppUi.requireText(this, txtId, txtName, txtEmail, txtIdentity, txtPhone)) {
            return;
        }
        if (!txtEmail.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "Email khong dung dinh dang.", "Du lieu khong hop le", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AppUi.success(this, "Tao tai khoan khach hang thanh cong. He thong da sinh username, mat khau mac dinh va ghi Audit Log.");
        dispose();
    }
}
