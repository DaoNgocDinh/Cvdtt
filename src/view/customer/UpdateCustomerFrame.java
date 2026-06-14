package view.customer;

import facade.TaiKhoanFacade;
import model.account.TaiKhoan;
import view.common.AppUi;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class UpdateCustomerFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtIdentity;
    private JTextField txtPhone;
    private final Runnable onSaved;

    public UpdateCustomerFrame() {
        this(null, new String[]{"KH001", "Nguyen Thanh Tung", "tung.nt@email.com", "001203004455", "0901234567", "Ha Noi", "Ca nhan"});
    }

    public UpdateCustomerFrame(Runnable onSaved, String[] data) {
        this.onSaved = onSaved;
        AppUi.setupFrame(this, "Sua khach hang", 640, 520);
        setContentPane(createContent(data));
        setVisible(true);
    }

    public UpdateCustomerFrame(String[] data) {
        this(null, data);
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

        txtId.setText(data[0]);
        txtName.setText(data[1]);
        txtEmail.setText(data[2]);
        txtIdentity.setText(data[3]);
        txtPhone.setText(data[4]);

        AppUi.addField(form, 0, "Ma khach hang", txtId);
        AppUi.addField(form, 1, "Ho ten/Ten cong ty", txtName);
        AppUi.addField(form, 2, "Email", txtEmail);
        AppUi.addField(form, 3, "CCCD/MST", txtIdentity);
        AppUi.addField(form, 4, "So dien thoai", txtPhone);

        JPanel actions = AppUi.toolbar();
        JButton save = AppUi.button("Cap nhat");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(save);
        actions.add(cancel);

        save.addActionListener(e -> {
            if (AppUi.requireText(this, txtId, txtName, txtEmail, txtIdentity, txtPhone)) {
                TaiKhoanFacade facade = new TaiKhoanFacade();
                TaiKhoan existing = facade.getTaiKhoanById(txtId.getText().trim());
                if (existing == null) {
                    JOptionPane.showMessageDialog(this, "Khong tim thay tai khoan.", "Loi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setMaTaiKhoan(txtId.getText().trim());
                taiKhoan.setHoTen(txtName.getText().trim());
                taiKhoan.setEmail(txtEmail.getText().trim());
                taiKhoan.setMatKhau(existing.getMatKhau());
                taiKhoan.setCccd(txtIdentity.getText().trim());
                taiKhoan.setSoDienThoai(txtPhone.getText().trim());
                taiKhoan.setChucVu(existing.getChucVu() != null ? existing.getChucVu() : "Ca nhan");
                taiKhoan.setLocker(existing.getLocker());
                taiKhoan.setRoleName(existing.getRoleName());
                taiKhoan.setSoTienConNo(existing.getSoTienConNo());

                facade.updateTaiKhoan(taiKhoan);

                if (onSaved != null) {
                    onSaved.run();
                }

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
