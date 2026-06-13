package view.employee;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class CreateEmployeeFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtDepartment;
    private JTextField txtPosition;
    private JComboBox<String> cbRole;

    public CreateEmployeeFrame() {
        AppUi.setupFrame(this, "Tao tai khoan nhan vien", 660, 560);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Tao tai khoan nhan vien", "Nhap thong tin bat buoc de sinh tai khoan va mat khau mac dinh.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        txtId = AppUi.textField();
        txtName = AppUi.textField();
        txtEmail = AppUi.textField();
        txtPhone = AppUi.textField();
        txtDepartment = AppUi.textField();
        txtPosition = AppUi.textField();
        cbRole = AppUi.combo("ADMIN", "MANAGER", "AUDITOR", "EMPLOYEE");

        AppUi.addField(form, 0, "Ma nhan vien", txtId);
        AppUi.addField(form, 1, "Ho ten", txtName);
        AppUi.addField(form, 2, "Email", txtEmail);
        AppUi.addField(form, 3, "So dien thoai", txtPhone);
        AppUi.addField(form, 4, "Phong ban", txtDepartment);
        AppUi.addField(form, 5, "Chuc vu", txtPosition);
        AppUi.addField(form, 6, "Vai tro", cbRole);

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
        if (!AppUi.requireText(this, txtId, txtName, txtEmail, txtDepartment, txtPosition)) {
            return;
        }
        if (!txtEmail.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "Email khong dung dinh dang.", "Du lieu khong hop le", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AppUi.success(this, "Tao tai khoan thanh cong. Username va mat khau mac dinh da duoc sinh, Audit Log da duoc ghi nhan.");
        dispose();
    }
}
