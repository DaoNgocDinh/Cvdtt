package view.notification;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class NotificationFrame extends JFrame {

    public NotificationFrame() {
        AppUi.setupFrame(this, "Thong bao noi bo", 980, 600);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Thong bao noi bo", "Gui va xem thong bao giua cac bo phan trong ngan hang.");
        JPanel card = AppUi.card();

        JPanel toolbar = AppUi.toolbar();
        JButton send = AppUi.button("Gui thong bao");
        JButton markRead = AppUi.secondaryButton("Danh dau da doc");
        toolbar.add(send);
        toolbar.add(markRead);

        String[] columns = {"Thoi gian", "Nguoi gui", "Nguoi nhan", "Tieu de", "Trang thai"};
        Object[][] rows = {
                {"2026-04-12 08:45", "Admin", "Toan bo nhan vien", "Bao tri he thong luc 22h", "Moi"},
                {"2026-04-12 09:10", "Risk Officer", "Auditor", "Canh bao dang nhap bat thuong", "Moi"},
                {"2026-04-12 10:30", "PermissionService", "NV001", "Quyen truy cap da thay doi", "Da doc"}
        };
        JTable table = AppUi.table(columns, rows);

        send.addActionListener(e -> new SendNotificationFrame());
        markRead.addActionListener(e -> AppUi.success(this, "Thong bao da duoc danh dau da doc."));

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
    }
}
