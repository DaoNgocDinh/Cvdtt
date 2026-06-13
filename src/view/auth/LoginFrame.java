package view.auth;

import model.account.TaiKhoan;
import observer.subjects.LoginService;
import proxy.AuditProxy;
import repository.TaiKhoanRepository;
import service.TaiKhoanAuthenticationService;
import view.dashboard.DashboardFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private static final Color BACKGROUND = new Color(241, 245, 249);
    private static final Color PRIMARY = new Color(28, 100, 242);
    private static final Color PRIMARY_DARK = new Color(30, 64, 175);
    private static final Color TEXT = new Color(15, 23, 42);
    private static final Color MUTED = new Color(100, 116, 139);
    private static final Color BORDER = new Color(203, 213, 225);

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private LoginService loginService;

    public LoginFrame() {
        setTitle("Bank Internal System - Login");
        setSize(860, 520);
        setMinimumSize(new Dimension(760, 460));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginService = createLoginService();
        setContentPane(createContent());
        getRootPane().setDefaultButton((JButton) getRootPane().getClientProperty("loginButton"));

        setVisible(true);
    }

    private JComponent createContent() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(BACKGROUND);
        root.setBorder(new EmptyBorder(28, 28, 28, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.weightx = 0.9;
        gbc.insets = new Insets(0, 0, 0, 0);
        root.add(new BrandPanel(), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.1;
        gbc.insets = new Insets(0, 24, 0, 0);
        root.add(createLoginPanel(), gbc);

        return root;
    }

    private JComponent createLoginPanel() {
        RoundedPanel panel = new RoundedPanel(24, Color.WHITE);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(36, 40, 36, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);

        JLabel title = new JLabel("Sign in");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(TEXT);
        panel.add(title, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 28, 0);
        JLabel subtitle = new JLabel("Welcome back. Please login to continue.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(MUTED);
        panel.add(subtitle, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 8, 0);
        panel.add(createLabel("Username"), gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 18, 0);
        txtUsername = createTextField("Enter your username");
        panel.add(txtUsername, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 8, 0);
        panel.add(createLabel("Password"), gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 12, 0);
        txtPassword = createPasswordField();
        panel.add(txtPassword, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 28, 0);
        JPanel options = new JPanel(new BorderLayout());
        options.setOpaque(false);
        JCheckBox remember = new JCheckBox("Remember this device");
        remember.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        remember.setForeground(MUTED);
        remember.setFocusPainted(false);
        remember.setOpaque(false);
        JButton forgot = new JButton("Forgot password?");
        forgot.setBorderPainted(false);
        forgot.setContentAreaFilled(false);
        forgot.setFocusPainted(false);
        forgot.setForeground(PRIMARY);
        forgot.setFont(new Font("Segoe UI", Font.BOLD, 12));
        forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgot.addActionListener(e -> new ChangePasswordFrame());
        options.add(remember, BorderLayout.WEST);
        options.add(forgot, BorderLayout.EAST);
        panel.add(options, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 18, 0);
        JButton btnLogin = createLoginButton();
        panel.add(btnLogin, gbc);
        getRootPane().putClientProperty("loginButton", btnLogin);

        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 0, 0);
        JLabel footer = new JLabel("Secure access for internal banking operations", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(MUTED);
        panel.add(footer, gbc);

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(TEXT);
        return label;
    }

    private JTextField createTextField(String tooltip) {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(TEXT);
        field.setToolTipText(tooltip);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                new EmptyBorder(12, 14, 12, 14)
        ));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(TEXT);
        field.setToolTipText("Enter your password");
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                new EmptyBorder(12, 14, 12, 14)
        ));
        return field;
    }

    private JButton createLoginButton() {
        JButton button = new JButton("Login");
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY);
        button.setBorder(new EmptyBorder(13, 18, 13, 18));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(PRIMARY_DARK);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(PRIMARY);
            }
        });
        button.addActionListener(e -> openDashboard());
        return button;
    }

    private void openDashboard() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.", "Login failed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TaiKhoan taiKhoan = loginService.login(username, password);
        if (taiKhoan == null) {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your username.", "Login failed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (Boolean.TRUE.equals(taiKhoan.getLocker())) {
            JOptionPane.showMessageDialog(this, "Your account is locked.", "Login failed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        dispose();
        new DashboardFrame();
    }

    private LoginService createLoginService() {
        TaiKhoanRepository taiKhoanRepository = new TaiKhoanRepository();
        return new LoginService(new AuditProxy(new TaiKhoanAuthenticationService(taiKhoanRepository)));
    }

    private static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color color;

        RoundedPanel(int radius, Color color) {
            this.radius = radius;
            this.color = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class BrandPanel extends JPanel {

        BrandPanel() {
            setOpaque(false);
            setBorder(new EmptyBorder(38, 36, 38, 36));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint paint = new GradientPaint(
                    0, 0, new Color(15, 23, 42),
                    getWidth(), getHeight(), new Color(37, 99, 235)
            );
            g2.setPaint(paint);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);

            g2.setColor(new Color(255, 255, 255, 34));
            g2.fillOval(getWidth() - 142, 42, 176, 176);
            g2.fillOval(26, getHeight() - 128, 138, 138);

            int x = 38;
            int y = 58;

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(x, y, 58, 58, 20, 20);
            g2.setColor(PRIMARY_DARK);
            g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(x + 16, y + 32, x + 28, y + 20);
            g2.drawLine(x + 28, y + 20, x + 43, y + 36);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 30));
            g2.drawString("Bank Internal", x, y + 112);
            g2.drawString("System", x, y + 150);

            g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            g2.setColor(new Color(226, 232, 240));
            g2.drawString("Nhan vien, khach hang, khoan vay", x, y + 194);
            g2.drawString("phan quyen va rui ro trong mot noi.", x, y + 218);

            g2.setColor(new Color(255, 255, 255, 44));
            g2.fillRoundRect(x, getHeight() - 118, getWidth() - 76, 1, 1, 1);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            g2.setColor(new Color(240, 249, 255));
            g2.drawString("Secure workspace", x, getHeight() - 80);
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            g2.setColor(new Color(203, 213, 225));
            g2.drawString("Audit ready, role aware, risk monitored", x, getHeight() - 56);

            g2.dispose();
            super.paintComponent(g);
        }
    }
}
