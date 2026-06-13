package view.permission;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class PermissionFrame extends JFrame {

    private JTable table;

    public PermissionFrame() {
        AppUi.setupFrame(this, "Quan ly phan quyen", 980, 600);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Quan ly phan quyen", "Gan quyen, thu hoi quyen va theo doi vai tro nguoi dung.");
        JPanel card = AppUi.card();

        JPanel toolbar = AppUi.toolbar();
        JButton grant = AppUi.button("Gan/Thu hoi quyen");
        JButton role = AppUi.secondaryButton("Tao vai tro");
        toolbar.add(grant);
        toolbar.add(role);

        String[] columns = {"Ma NV", "Ho ten", "Vai tro", "Quyen hien co", "Trang thai"};
        Object[][] rows = {
                {"NV001", "Nguyen Van An", "EMPLOYEE", "Tao khach hang, Tao khoan vay", "Hoat dong"},
                {"NV002", "Tran Thi Binh", "AUDITOR", "Xem Audit Log, Xem canh bao", "Hoat dong"},
                {"NV003", "Le Minh Quan", "ADMIN", "Toan quyen", "Hoat dong"},
                {"NV004", "Pham Hoai Nam", "MANAGER", "Phe duyet vay, Gan quyen", "Hoat dong"}
        };
        table = AppUi.table(columns, rows);

        grant.addActionListener(e -> new GrantPermissionFrame());
        role.addActionListener(e -> JOptionPane.showMessageDialog(this, "Vai tro moi da duoc tao mau. He thong san sang gan quyen.", "Tao vai tro", JOptionPane.INFORMATION_MESSAGE));

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
    }
}
