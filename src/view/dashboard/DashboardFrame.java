package view.dashboard;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));

        JButton btnEmployee = new JButton("Employee");
        JButton btnCustomer = new JButton("Customer");
        JButton btnLoan = new JButton("Loan");
        JButton btnPermission = new JButton("Permission");
        JButton btnRisk = new JButton("Risk");
        JButton btnAudit = new JButton("Audit Log");
        JButton btnNotification = new JButton("Notification");

        panel.add(btnEmployee);
        panel.add(btnCustomer);
        panel.add(btnLoan);
        panel.add(btnPermission);
        panel.add(btnRisk);
        panel.add(btnAudit);
        panel.add(btnNotification);

        add(panel);

        setVisible(true);
    }
}
