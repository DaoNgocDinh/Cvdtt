package view.audit;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import singleton.AuditLog;
import singleton.AuditLogManager;

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
        DefaultTableModel model
                = new DefaultTableModel(columns, 0);

        for (AuditLog log
                : AuditLogManager
                        .getInstance()
                        .getLogs()) {

            model.addRow(new Object[]{
                log.getThoiGian(),
                log.getMaTaiKhoan(),
                "-",
                log.getHanhDong(),
                "-",
                "Success"
            });
        }

        JTable table = new JTable(model);

        find.addActionListener(e -> JOptionPane.showMessageDialog(this, "Da loc Audit Log theo tu khoa: " + search.getText(), "Tim kiem", JOptionPane.INFORMATION_MESSAGE));
        export.addActionListener(e -> AppUi.success(this, "Bao cao Audit Log da duoc tao mau."));

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
    }
}
