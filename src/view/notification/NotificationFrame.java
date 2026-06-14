package view.notification;

import facade.NotificationFacade;
import model.account.TaiKhoan;
import utils.AuthSession;
import view.common.AppUi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import notification.Notification;

public class NotificationFrame extends JFrame {

    private final NotificationFacade notificationFacade;
    private JTable table;

    public NotificationFrame() {
        notificationFacade = new NotificationFacade();
        AppUi.setupFrame(this, "Thong bao noi bo", 980, 600);
        setContentPane(createContent());
        setVisible(true);
        loadNotifications();
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Thong bao noi bo", "Gui va xem thong bao giua cac bo phan trong ngan hang.");
        JPanel card = AppUi.card();

        JPanel toolbar = AppUi.toolbar();
        JButton send = AppUi.button("Gui thong bao");
        JButton refresh = AppUi.button("Lam moi");
        JButton markRead = AppUi.secondaryButton("Danh dau da doc");
        toolbar.add(send);
        toolbar.add(refresh);
        toolbar.add(markRead);

        String[] columns = {"Thoi gian", "Nguoi gui", "Nguoi nhan", "Tieu de", "Trang thai"};
        table = AppUi.table(columns, new Object[0][0]);

        send.addActionListener(e -> new SendNotificationFrame());
        refresh.addActionListener(e -> loadNotifications());
        markRead.addActionListener(e -> markSelectedNotificationRead());

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private void loadNotifications() {
        TaiKhoan currentUser = AuthSession.getCurrentUser();
        String recipient = currentUser != null ? currentUser.getMaTaiKhoan() : null;
        String roleName = currentUser != null ? currentUser.getRoleName() : null;
        List<Notification> notifications = recipient != null ? notificationFacade.getNotificationsForUser(recipient, roleName) : notificationFacade.getAllNotifications();

        Object[][] rows = new Object[notifications.size()][5];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (int i = 0; i < notifications.size(); i++) {
            Notification notification = notifications.get(i);
            rows[i][0] = notification.getCreatedAt().format(formatter);
            rows[i][1] = notification.getSender();
            rows[i][2] = notification.getRecipient();
            rows[i][3] = notification.getTitle();
            rows[i][4] = notification.getStatus();
        }

        DefaultTableModel model = new DefaultTableModel(rows, new String[]{"Thoi gian", "Nguoi gui", "Nguoi nhan", "Tieu de", "Trang thai"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
    }

    private void markSelectedNotificationRead() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon thong bao de danh dau da doc.", "Chua chon thong bao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        TaiKhoan currentUser = AuthSession.getCurrentUser();
        String recipient = currentUser != null ? currentUser.getMaTaiKhoan() : null;
        String roleName = currentUser != null ? currentUser.getRoleName() : null;
        List<Notification> notifications = notificationFacade.getNotificationsForUser(recipient, roleName);
        if (row < notifications.size()) {
            notificationFacade.markAsRead(notifications.get(row).getId());
            loadNotifications();
            AppUi.success(this, "Thong bao da duoc danh dau da doc.");
        }
    }
}
