package view.common;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class AppUi {

    public static final Color BACKGROUND = new Color(244, 247, 251);
    public static final Color CARD = Color.WHITE;
    public static final Color PRIMARY = new Color(37, 99, 235);
    public static final Color PRIMARY_DARK = new Color(29, 78, 216);
    public static final Color PRIMARY_SOFT = new Color(219, 234, 254);
    public static final Color DANGER = new Color(220, 38, 38);
    public static final Color DANGER_DARK = new Color(185, 28, 28);
    public static final Color SUCCESS = new Color(22, 163, 74);
    public static final Color SUCCESS_DARK = new Color(21, 128, 61);
    public static final Color WARNING = new Color(217, 119, 6);
    public static final Color TEXT = new Color(15, 23, 42);
    public static final Color MUTED = new Color(100, 116, 139);
    public static final Color BORDER = new Color(214, 222, 235);

    private AppUi() {
    }

    public static void setupFrame(JFrame frame, String title, int width, int height) {
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setMinimumSize(new Dimension(Math.min(width, 760), Math.min(height, 500)));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static JPanel page(String title, String subtitle) {
        JPanel page = new JPanel(new BorderLayout(0, 20));
        page.setBackground(BACKGROUND);
        page.setBorder(new EmptyBorder(26, 30, 30, 30));
        page.add(header(title, subtitle), BorderLayout.NORTH);
        return page;
    }

    public static JPanel header(String title, String subtitle) {
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel titleView = new JLabel(title);
        titleView.setForeground(TEXT);
        titleView.setFont(new Font("Segoe UI", Font.BOLD, 30));
        header.add(titleView);

        JLabel subtitleView = new JLabel(subtitle);
        subtitleView.setForeground(MUTED);
        subtitleView.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        header.add(Box.createVerticalStrut(4));
        header.add(subtitleView);

        return header;
    }

    public static JPanel card() {
        JPanel panel = new ShadowPanel(20, CARD);
        panel.setLayout(new BorderLayout(0, 16));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        return panel;
    }

    public static JPanel toolbar() {
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        toolbar.setOpaque(false);
        return toolbar;
    }

    public static JButton button(String text) {
        return button(text, PRIMARY, Color.WHITE);
    }

    public static JButton secondaryButton(String text) {
        return button(text, Color.WHITE, PRIMARY);
    }

    public static JButton dangerButton(String text) {
        return button(text, DANGER, Color.WHITE);
    }

    public static JButton button(String text, Color background, Color foreground) {
        JButton button = new RoundedButton(text, background, foreground);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBorder(new EmptyBorder(11, 18, 11, 18));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (background.equals(PRIMARY)) {
                    button.setBackground(PRIMARY_DARK);
                } else if (background.equals(DANGER)) {
                    button.setBackground(DANGER_DARK);
                } else if (background.equals(SUCCESS)) {
                    button.setBackground(SUCCESS_DARK);
                } else if (background.equals(Color.WHITE)) {
                    button.setBackground(new Color(248, 250, 252));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(background);
            }
        });
        return button;
    }

    public static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return label;
    }

    public static JTextField textField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(11, 13, 11, 13)
        ));
        return field;
    }

    public static JTextArea textArea(int rows) {
        JTextArea area = new JTextArea(rows, 20);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setForeground(TEXT);
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(new EmptyBorder(11, 13, 11, 13));
        return area;
    }

    public static JComboBox<String> combo(String... values) {
        JComboBox<String> combo = new JComboBox<>(values);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setForeground(TEXT);
        return combo;
    }

    public static JPanel form() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        return form;
    }

    public static void addField(JPanel form, int row, String label, JComponent input) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 16);
        form.add(label(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        form.add(input, gbc);
    }

    public static JTable table(String[] columns, Object[][] rows) {
        JTable table = new JTable(new DefaultTableModel(rows, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setRowHeight(38);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(239, 246, 255));
        table.getTableHeader().setForeground(TEXT);
        table.getTableHeader().setPreferredSize(new Dimension(0, 38));
        table.setGridColor(new Color(226, 232, 240));
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setSelectionBackground(PRIMARY_SOFT);
        table.setSelectionForeground(TEXT);
        return table;
    }

    public static boolean requireText(Component parent, JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(parent, "Vui long nhap day du thong tin bat buoc.", "Du lieu khong hop le", JOptionPane.WARNING_MESSAGE);
                field.requestFocus();
                return false;
            }
        }
        return true;
    }

    public static void success(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thanh cong", JOptionPane.INFORMATION_MESSAGE);
    }

    private static class ShadowPanel extends JPanel {
        private final int radius;
        private final Color color;

        ShadowPanel(int radius, Color color) {
            this.radius = radius;
            this.color = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(15, 23, 42, 18));
            g2.fillRoundRect(4, 6, getWidth() - 8, getHeight() - 8, radius, radius);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth() - 8, getHeight() - 10, radius, radius);
            g2.setColor(BORDER);
            g2.drawRoundRect(0, 0, getWidth() - 9, getHeight() - 11, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class RoundedButton extends JButton {
        private final Color foreground;

        RoundedButton(String text, Color background, Color foreground) {
            super(text);
            this.foreground = foreground;
            setBackground(background);
            setForeground(foreground);
            setContentAreaFilled(false);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
            if (getBackground().equals(Color.WHITE) || getBackground().equals(new Color(248, 250, 252))) {
                g2.setColor(BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            }
            g2.dispose();
            setForeground(foreground);
            super.paintComponent(g);
        }
    }
}
