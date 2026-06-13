package view.report;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class ReportFrame extends JFrame {

    public ReportFrame() {
        AppUi.setupFrame(this, "Bao cao thong ke", 980, 600);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Bao cao thong ke", "Theo doi tinh trang he thong, khoan vay, rui ro va hoat dong noi bo.");

        JPanel content = new JPanel(new BorderLayout(0, 18));
        content.setOpaque(false);

        JPanel stats = new JPanel(new GridLayout(1, 4, 14, 0));
        stats.setOpaque(false);
        stats.add(metric("Nhan vien", "128", "4 tai khoan bi khoa"));
        stats.add(metric("Khach hang", "1,842", "32 ho so moi"));
        stats.add(metric("Khoan vay", "316", "18 cho duyet"));
        stats.add(metric("Canh bao", "7", "2 muc cao"));

        JPanel card = AppUi.card();
        String[] columns = {"Chi so", "Gia tri", "Trang thai", "Ghi chu"};
        Object[][] rows = {
                {"Thoi gian phan hoi", "< 3 giay", "Dat", "Dung yeu cau phi chuc nang"},
                {"Nguoi dung dong thoi", "100+", "Dat", "Mo phong kha nang ho tro"},
                {"Audit Log", "Day du", "Dat", "Ghi nhan thao tac nhay cam"},
                {"Rui ro", "Dang giam sat", "Can theo doi", "Canh bao muc cao can xu ly"}
        };
        card.add(new JScrollPane(AppUi.table(columns, rows)), BorderLayout.CENTER);

        content.add(stats, BorderLayout.NORTH);
        content.add(card, BorderLayout.CENTER);
        page.add(content, BorderLayout.CENTER);
        return page;
    }

    private JComponent metric(String title, String value, String note) {
        JPanel card = AppUi.card();
        card.setLayout(new BorderLayout(0, 8));

        JLabel titleView = new JLabel(title);
        titleView.setFont(new Font("Segoe UI", Font.BOLD, 13));
        titleView.setForeground(AppUi.MUTED);

        JLabel valueView = new JLabel(value);
        valueView.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueView.setForeground(AppUi.TEXT);

        JLabel noteView = new JLabel(note);
        noteView.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        noteView.setForeground(AppUi.MUTED);

        card.add(titleView, BorderLayout.NORTH);
        card.add(valueView, BorderLayout.CENTER);
        card.add(noteView, BorderLayout.SOUTH);
        return card;
    }
}
