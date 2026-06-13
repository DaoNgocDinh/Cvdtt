package view.auth;

import view.dashboard.DashboardFrame;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginFrame() {

        setTitle("Bank Internal System - Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> {
            dispose();
            new DashboardFrame();
        });

        setVisible(true);
    }
}
