package view.notification;

import facade.NotificationFacade;
import model.account.TaiKhoan;
import utils.AuthSession;
import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class SendNotificationFrame extends JFrame {

    private JTextField txtTitle;
    private JTextArea txtContent;
    private JComboBox<String> cbReceiver;
    private final NotificationFacade notificationFacade;

    public SendNotificationFrame() {
        notificationFacade = new NotificationFacade();
        AppUi.setupFrame(this, "Gui thong bao", 660, 560);
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Gui thong bao", "Nguoi gui se tu dong lay tu tai khoan dang nhap hien tai.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        cbReceiver = AppUi.combo("Admin", "Nhan vien");
        txtTitle = AppUi.textField();
        txtContent = AppUi.textArea(6);

        AppUi.addField(form, 0, "Nguoi nhan", cbReceiver);
        AppUi.addField(form, 1, "Tieu de", txtTitle);
        AppUi.addField(form, 2, "Noi dung", new JScrollPane(txtContent));

        JPanel actions = AppUi.toolbar();
        JButton send = AppUi.button("Gui thong bao");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(send);
        actions.add(cancel);

        send.addActionListener(e -> {
            if (AppUi.requireText(this, txtTitle, txtContent)) {
                String title = txtTitle.getText().trim();
                String message = txtContent.getText().trim();
                String sender = determineSender();
                String recipientRole = mapReceiverRole(String.valueOf(cbReceiver.getSelectedItem()));

                if (sender == null || sender.isEmpty()) {
                    AppUi.error(this, "Khong the gui thong bao khi chua dang nhap.");
                    return;
                }

                if (recipientRole == null) {
                    AppUi.error(this, "Lua chon nguoi nhan khong hop le.");
                    return;
                }

                notificationFacade.sendNotification(sender, recipientRole, title, message);
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

    private String determineSender() {
        TaiKhoan currentUser = AuthSession.getCurrentUser();
        return currentUser != null ? currentUser.getMaTaiKhoan() : null;
    }

    private String mapReceiverRole(String selectedRecipient) {
        if ("Admin".equalsIgnoreCase(selectedRecipient)) {
            return "ADMIN";
        }
        if ("Nhan vien".equalsIgnoreCase(selectedRecipient)) {
            return "NHANVIEN";
        }
        return null;
    }
}
