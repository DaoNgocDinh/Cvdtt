package view.employee;

import javax.swing.*;
import java.awt.*;

public class CreateEmployeeFrame extends JFrame {

    public CreateEmployeeFrame() {

        setTitle("Create Employee");
        setSize(500,500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8,2,10,10));

        panel.add(new JLabel("Employee ID"));
        panel.add(new JTextField());

        panel.add(new JLabel("Name"));
        panel.add(new JTextField());

        panel.add(new JLabel("Email"));
        panel.add(new JTextField());

        panel.add(new JLabel("Department"));
        panel.add(new JTextField());

        panel.add(new JLabel("Role"));

        JComboBox<String> cbRole =
                new JComboBox<>(new String[]{
                        "ADMIN",
                        "MANAGER",
                        "AUDITOR"
                });

        panel.add(cbRole);

        JButton btnSave = new JButton("Save");

        panel.add(btnSave);

        add(panel);

        setVisible(true);
    }
}