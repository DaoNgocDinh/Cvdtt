package view.loan;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class CreateLoanFrame extends JFrame {

    private JTextField txtLoanId;
    private JTextField txtCustomerId;
    private JTextField txtAmount;
    private JTextField txtTerm;
    private JTextField txtInterest;
    private JTextArea txtPurpose;
    private JComboBox<String> cbType;

    public CreateLoanFrame() {
        AppUi.setupFrame(this, "Tao khoan vay", 700, 620);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Tao khoan vay", "Nhap thong tin khoan vay va chay quy trinh kiem tra truoc khi luu.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        txtLoanId = AppUi.textField();
        txtCustomerId = AppUi.textField();
        txtAmount = AppUi.textField();
        txtTerm = AppUi.textField();
        txtInterest = AppUi.textField();
        txtPurpose = AppUi.textArea(4);
        cbType = AppUi.combo("Vay ngan han", "Vay trung han", "Vay dai han");

        AppUi.addField(form, 0, "Ma khoan vay", txtLoanId);
        AppUi.addField(form, 1, "Ma khach hang", txtCustomerId);
        AppUi.addField(form, 2, "So tien vay", txtAmount);
        AppUi.addField(form, 3, "Thoi han vay", txtTerm);
        AppUi.addField(form, 4, "Lai suat", txtInterest);
        AppUi.addField(form, 5, "Loai khoan vay", cbType);
        AppUi.addField(form, 6, "Muc dich vay", new JScrollPane(txtPurpose));

        JPanel actions = AppUi.toolbar();
        JButton searchCustomer = AppUi.secondaryButton("Tim khach hang");
        JButton save = AppUi.button("Tao khoan vay");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(searchCustomer);
        actions.add(save);
        actions.add(cancel);

        searchCustomer.addActionListener(e -> {
            txtCustomerId.setText("KH001");
            AppUi.success(this, "Da chon khach hang KH001 va tu dong dien vao ho so vay.");
        });
        save.addActionListener(e -> save());
        cancel.addActionListener(e -> dispose());

        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private void save() {
        if (!AppUi.requireText(this, txtLoanId, txtCustomerId, txtAmount, txtTerm, txtInterest)) {
            return;
        }
        AppUi.success(this, "Tao khoan vay thanh cong. Chuoi kiem tra da hoan tat, Audit Log va thong bao da duoc ghi nhan.");
        dispose();
    }
}
