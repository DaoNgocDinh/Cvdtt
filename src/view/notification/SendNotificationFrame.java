package view.notification;

import facade.NotificationFacade;
import notification.Notification;
import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class SendNotificationFrame extends JFrame {

    private JTextField txtTitle;
    private JTextArea txtContent;
    private JComboBox<String> cbSender;
    private JComboBox<String> cbReceiver;
    private final NotificationFacade notificationFacade;

    public SendNotificationFrame() {
        notificationFacade = new NotificationFacade();
        AppUi.setupFrame(this, "Gui thong bao", 660, 560);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Gui thong bao", "Mediator se dieu phoi thong bao den bo phan lien quan.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        cbSender = AppUi.combo("Admin", "Manager", "Auditor", "Risk Officer", "System");
        cbReceiver = AppUi.combo("Nhan vien");
        txtTitle = AppUi.textField();
        txtContent = AppUi.textArea(6);

        AppUi.addField(form, 0, "Nguoi gui", cbSender);
        AppUi.addField(form, 1, "Nguoi nhan", cbReceiver);
        AppUi.addField(form, 2, "Tieu de", txtTitle);
        AppUi.addField(form, 3, "Noi dung", new JScrollPane(txtContent));

        JPanel actions = AppUi.toolbar();
        JButton send = AppUi.button("Gui thong bao");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(send);
        actions.add(cancel);

        send.addActionListener(e -> {
            if (AppUi.requireText(this, txtTitle)) {
                Notification notification = new Notification();
                notification.setSender(String.valueOf(cbSender.getSelectedItem()));
                notification.setReceiver(String.valueOf(cbReceiver.getSelectedItem()));
                notification.setTitle(txtTitle.getText().trim());
                notification.setContent(txtContent.getText().trim());
                notification.setStatus("Moi");
                notificationFacade.sendNotification(notification);
                AppUi.success(this, "Gui thong bao thanh cong.");
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
