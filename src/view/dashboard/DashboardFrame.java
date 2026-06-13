package view.dashboard;

import view.audit.AuditLogFrame;
import view.auth.LoginFrame;
import view.customer.CustomerListFrame;
import view.employee.EmployeeListFrame;
import view.loan.LoanListFrame;
import view.notification.NotificationFrame;
import view.permission.PermissionFrame;
import view.risk.RiskAlertFrame;
import view.report.ReportFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardFrame extends JFrame {

    private static final Color BACKGROUND = new Color(241, 245, 249);
    private static final Color SIDEBAR = new Color(15, 23, 42);
    private static final Color PRIMARY = new Color(28, 100, 242);
    private static final Color TEXT = new Color(15, 23, 42);
    private static final Color MUTED = new Color(100, 116, 139);
    private static final Color BORDER = new Color(226, 232, 240);

    public DashboardFrame() {
        setTitle("Bank Internal System - Dashboard");
        setSize(1080, 680);
        setMinimumSize(new Dimension(940, 580));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(createContent());

        setVisible(true);
    }

    private JComponent createContent() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BACKGROUND);
        root.add(createSidebar(), BorderLayout.WEST);
        root.add(createMainPanel(), BorderLayout.CENTER);
        return root;
    }

    private JComponent createSidebar() {
        JPanel sidebar = new SidebarPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBorder(new EmptyBorder(28, 24, 24, 24));

        JPanel brand = new JPanel();
        brand.setOpaque(false);
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("BIS");
        logo.setOpaque(true);
        logo.setBackground(Color.WHITE);
        logo.setForeground(PRIMARY);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setMaximumSize(new Dimension(58, 46));
        logo.setPreferredSize(new Dimension(58, 46));
        brand.add(logo);
        brand.add(Box.createVerticalStrut(18));

        JLabel title = new JLabel("Bank Internal");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        brand.add(title);

        JLabel subtitle = new JLabel("System Control");
        subtitle.setForeground(new Color(203, 213, 225));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        brand.add(subtitle);

        JPanel status = new JPanel();
        status.setOpaque(false);
        status.setLayout(new BoxLayout(status, BoxLayout.Y_AXIS));

        status.add(createSidebarNote("Session", "Active workspace"));
        status.add(Box.createVerticalStrut(14));
        status.add(createSidebarNote("Access", "Internal banking"));

        JButton logout = new JButton("Logout");
        logout.setFont(new Font("Segoe UI", Font.BOLD, 13));
        logout.setForeground(Color.WHITE);
        logout.setBackground(new Color(51, 65, 85));
        logout.setBorder(new EmptyBorder(12, 18, 12, 18));
        logout.setFocusPainted(false);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Ban co chac muon dang xuat?", "Xac nhan dang xuat", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame();
            }
        });

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.add(status);
        bottom.add(Box.createVerticalStrut(28));
        bottom.add(logout);

        sidebar.add(brand, BorderLayout.NORTH);
        sidebar.add(bottom, BorderLayout.SOUTH);
        return sidebar;
    }

    private JComponent createSidebarNote(String label, String value) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelView = new JLabel(label);
        labelView.setForeground(new Color(148, 163, 184));
        labelView.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(labelView);

        JLabel valueView = new JLabel(value);
        valueView.setForeground(Color.WHITE);
        valueView.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(valueView);

        return panel;
    }

    private JComponent createMainPanel() {
        JPanel main = new JPanel(new BorderLayout(0, 22));
        main.setBackground(BACKGROUND);
        main.setBorder(new EmptyBorder(30, 32, 30, 32));

        main.add(createHeader(), BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(0, 24));
        content.setOpaque(false);
        content.add(createStatsPanel(), BorderLayout.NORTH);
        content.add(createModulePanel(), BorderLayout.CENTER);

        main.add(content, BorderLayout.CENTER);
        return main;
    }

    private JComponent createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel copy = new JPanel();
        copy.setOpaque(false);
        copy.setLayout(new BoxLayout(copy, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Dashboard");
        title.setForeground(TEXT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        copy.add(title);

        JLabel subtitle = new JLabel("Quick access to daily banking operations.");
        subtitle.setForeground(MUTED);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        copy.add(subtitle);

        JButton refresh = new JButton("Refresh");
        refresh.setFont(new Font("Segoe UI", Font.BOLD, 13));
        refresh.setForeground(PRIMARY);
        refresh.setBackground(Color.WHITE);
        refresh.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(10, 18, 10, 18)
        ));
        refresh.setFocusPainted(false);
        refresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        header.add(copy, BorderLayout.WEST);
        header.add(refresh, BorderLayout.EAST);
        return header;
    }

    private JComponent createStatsPanel() {
        JPanel stats = new JPanel(new GridLayout(1, 4, 16, 0));
        stats.setOpaque(false);
        stats.add(createStatCard("Employees", "Manage staff", "Ready"));
        stats.add(createStatCard("Customers", "Profiles and KYC", "Ready"));
        stats.add(createStatCard("Loans", "Applications", "Ready"));
        stats.add(createStatCard("Audit", "Activity logs", "Ready"));
        return stats;
    }

    private JComponent createStatCard(String title, String subtitle, String badge) {
        RoundedPanel card = new RoundedPanel(18, Color.WHITE);
        card.setLayout(new BorderLayout(0, 12));
        card.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel badgeView = new JLabel(badge);
        badgeView.setFont(new Font("Segoe UI", Font.BOLD, 11));
        badgeView.setForeground(new Color(22, 101, 52));
        badgeView.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel titleView = new JLabel(title);
        titleView.setForeground(TEXT);
        titleView.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel subtitleView = new JLabel(subtitle);
        subtitleView.setForeground(MUTED);
        subtitleView.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JPanel copy = new JPanel();
        copy.setOpaque(false);
        copy.setLayout(new BoxLayout(copy, BoxLayout.Y_AXIS));
        copy.add(titleView);
        copy.add(Box.createVerticalStrut(4));
        copy.add(subtitleView);

        card.add(badgeView, BorderLayout.NORTH);
        card.add(copy, BorderLayout.CENTER);
        return card;
    }

    private JComponent createModulePanel() {
        JPanel modules = new JPanel(new GridLayout(2, 4, 18, 18));
        modules.setOpaque(false);

        modules.add(createModuleCard("Employee", "Staff records and roles", "E", new Color(37, 99, 235), () -> new EmployeeListFrame()));
        modules.add(createModuleCard("Customer", "Customer profiles", "C", new Color(14, 165, 233), () -> new CustomerListFrame()));
        modules.add(createModuleCard("Loan", "Loan files and approval", "L", new Color(22, 163, 74), () -> new LoanListFrame()));
        modules.add(createModuleCard("Permission", "Access control", "P", new Color(124, 58, 237), () -> new PermissionFrame()));
        modules.add(createModuleCard("Risk", "Risk alerts", "R", new Color(220, 38, 38), () -> new RiskAlertFrame()));
        modules.add(createModuleCard("Audit Log", "System activity", "A", new Color(217, 119, 6), () -> new AuditLogFrame()));
        modules.add(createModuleCard("Notification", "Internal messages", "N", new Color(6, 182, 212), () -> new NotificationFrame()));
        modules.add(createModuleCard("Report", "System statistics", "B", new Color(15, 118, 110), () -> new ReportFrame()));

        return modules;
    }

    private JComponent createModuleCard(String title, String subtitle, String mark, Color accent, Runnable action) {
        RoundedPanel card = new RoundedPanel(18, Color.WHITE);
        card.setLayout(new BorderLayout(0, 18));
        card.setBorder(new EmptyBorder(22, 20, 20, 20));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel icon = new JLabel(mark, SwingConstants.CENTER);
        icon.setOpaque(true);
        icon.setBackground(soften(accent));
        icon.setForeground(accent);
        icon.setFont(new Font("Segoe UI", Font.BOLD, 18));
        icon.setPreferredSize(new Dimension(46, 46));

        JLabel titleView = new JLabel(title);
        titleView.setForeground(TEXT);
        titleView.setFont(new Font("Segoe UI", Font.BOLD, 17));

        JLabel subtitleView = new JLabel("<html><body style='width:140px'>" + subtitle + "</body></html>");
        subtitleView.setForeground(MUTED);
        subtitleView.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JPanel copy = new JPanel();
        copy.setOpaque(false);
        copy.setLayout(new BoxLayout(copy, BoxLayout.Y_AXIS));
        copy.add(titleView);
        copy.add(Box.createVerticalStrut(6));
        copy.add(subtitleView);

        card.add(icon, BorderLayout.NORTH);
        card.add(copy, BorderLayout.CENTER);
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(248, 250, 252));
                titleView.setForeground(accent);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
                titleView.setForeground(TEXT);
            }
        });

        return card;
    }

    private static Color soften(Color color) {
        return new Color(
                Math.min(255, color.getRed() + 198),
                Math.min(255, color.getGreen() + 198),
                Math.min(255, color.getBlue() + 198)
        );
    }

    private static class SidebarPanel extends JPanel {
        SidebarPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint paint = new GradientPaint(0, 0, SIDEBAR, getWidth(), getHeight(), new Color(30, 64, 175));
            g2.setPaint(paint);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(new Color(255, 255, 255, 22));
            g2.fillOval(getWidth() - 90, 42, 150, 150);
            g2.fillOval(-70, getHeight() - 120, 150, 150);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color color;

        RoundedPanel(int radius, Color color) {
            this.radius = radius;
            this.color = color;
            setOpaque(false);
            setBackground(color);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(15, 23, 42, 14));
            g2.fillRoundRect(4, 6, getWidth() - 8, getHeight() - 8, radius, radius);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 8, getHeight() - 10, radius, radius);
            g2.setColor(BORDER);
            g2.drawRoundRect(0, 0, getWidth() - 9, getHeight() - 11, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
