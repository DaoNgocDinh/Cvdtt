package view.notification;

import facade.NotificationFacade;
import notification.Notification;
import view.common.AppUi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotificationFrame extends JFrame {

    private final NotificationFacade notificationFacade;

    public NotificationFrame() {
        notificationFacade = new NotificationFacade();
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

        String[] columns = {"Thoi gian", "Nguoi gui", "Nguoi nhan", "Tieu de", "Noi dung", "Trang thai"};
        JTable table = AppUi.table(columns, new Object[0][0]);

        send.addActionListener(e -> new SendNotificationFrame());
        markRead.addActionListener(e -> AppUi.success(this, "Thong bao da duoc danh dau da doc."));

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);

        loadData(table);
        return page;
    }

    private void loadData(JTable table) {
        List<Notification> notifications = notificationFacade.getAllNotifications();
        Object[][] rows = new Object[notifications.size()][6];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < notifications.size(); i++) {
            Notification notification = notifications.get(i);
            rows[i][0] = notification.getSentAt().format(formatter);
            rows[i][1] = notification.getSender();
            rows[i][2] = notification.getReceiver();
            rows[i][3] = notification.getTitle();
            rows[i][4] = notification.getContent();
            rows[i][5] = notification.getStatus();
        }

        DefaultTableModel model = new DefaultTableModel(rows, new String[]{"Thoi gian", "Nguoi gui", "Nguoi nhan", "Tieu de", "Noi dung", "Trang thai"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
    }
}
