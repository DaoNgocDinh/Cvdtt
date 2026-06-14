package view.audit;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class AuditLogFrame extends JFrame {

    public AuditLogFrame() {
        AppUi.setupFrame(this, "Audit Log", 1050, 620);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Audit Log", "Tra cuu lich su hoat dong nguoi dung va cac thao tac nhay cam.");
        JPanel card = AppUi.card();

        JPanel toolbar = AppUi.toolbar();
        JTextField search = AppUi.textField();
        search.setColumns(22);
        JButton find = AppUi.secondaryButton("Tim kiem");
        JButton export = AppUi.button("Xuat bao cao");
        toolbar.add(AppUi.label("Tu khoa"));
        toolbar.add(search);
        toolbar.add(find);
        toolbar.add(export);

        String[] columns = {"Thoi gian", "Nguoi dung", "Vai tro", "Hanh dong", "Doi tuong", "Ket qua"};
        Object[][] rows = {
                {"2026-04-12 08:30", "admin", "ADMIN", "Tao tai khoan nhan vien", "NV001", "Thanh cong"},
                {"2026-04-12 09:05", "manager01", "MANAGER", "Phe duyet khoan vay", "VAY001", "Thanh cong"},
                {"2026-04-12 10:18", "auditor01", "AUDITOR", "Xem Audit Log", "SYSTEM", "Thanh cong"},
                {"2026-04-12 11:22", "admin", "ADMIN", "Thu hoi quyen", "NV004", "Thanh cong"}
        };
        JTable table = AppUi.table(columns, rows);

        find.addActionListener(e -> JOptionPane.showMessageDialog(this, "Da loc Audit Log theo tu khoa: " + search.getText(), "Tim kiem", JOptionPane.INFORMATION_MESSAGE));
        export.addActionListener(e -> AppUi.success(this, "Bao cao Audit Log da duoc tao mau."));

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
    }
}
