package view.notification;

import javax.swing.*;
import java.awt.*;

public class NotificationFrame extends JFrame {

    public NotificationFrame() {

        setTitle("Notification");
        setSize(700, 500);

        JTextArea area
                = new JTextArea();

        JButton btnSend
                = new JButton("Send");

        add(new JScrollPane(area),
                BorderLayout.CENTER);

        add(btnSend,
                BorderLayout.SOUTH);

        setVisible(true);
    }
}
