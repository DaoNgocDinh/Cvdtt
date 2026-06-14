package view.permission;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class GrantPermissionFrame extends JFrame {

    private JTextField txtEmployeeId;
    private JComboBox<String> cbRole;
    private JList<String> permissionList;

    public GrantPermissionFrame() {
        AppUi.setupFrame(this, "Gan quyen cho nhan vien", 650, 560);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Gan/Thu hoi quyen", "Proxy va chuoi kiem tra quyen se duoc mo phong truoc khi luu.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        txtEmployeeId = AppUi.textField();
        cbRole = AppUi.combo("ADMIN", "MANAGER", "AUDITOR", "EMPLOYEE", "RISK_OFFICER");
        permissionList = new JList<>(new String[]{
                "CREATE_EMPLOYEE",
                "UPDATE_EMPLOYEE",
                "LOCK_EMPLOYEE",
                "GRANT_PERMISSION",
                "VIEW_AUDIT_LOG",
                "CREATE_LOAN",
                "APPROVE_LOAN",
                "VIEW_RISK_ALERT",
                "SEND_NOTIFICATION"
        });
        permissionList.setVisibleRowCount(7);
        permissionList.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        AppUi.addField(form, 0, "Ma nhan vien", txtEmployeeId);
        AppUi.addField(form, 1, "Vai tro", cbRole);
        AppUi.addField(form, 2, "Danh sach quyen", new JScrollPane(permissionList));

        JPanel actions = AppUi.toolbar();
        JButton grant = AppUi.button("Luu quyen");
        JButton revoke = AppUi.dangerButton("Thu hoi quyen");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(grant);
        actions.add(revoke);
        actions.add(cancel);

        grant.addActionListener(e -> save("Gan quyen thanh cong. Thong bao da duoc gui den nhan vien va bo phan kiem toan."));
        revoke.addActionListener(e -> save("Thu hoi quyen thanh cong. Audit Log da duoc ghi nhan."));
        cancel.addActionListener(e -> dispose());

        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private void save(String message) {
        if (AppUi.requireText(this, txtEmployeeId)) {
            AppUi.success(this, message);
            dispose();
        }
    }
}
