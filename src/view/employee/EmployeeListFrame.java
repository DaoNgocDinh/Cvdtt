package view.employee;

import javax.swing.*;
import java.awt.*;

public class EmployeeListFrame extends JFrame {

    private JTable table;

    public EmployeeListFrame() {

        setTitle("Employee Management");
        setSize(900, 500);
        setLocationRelativeTo(null);

        String[] columns = {
            "ID",
            "Name",
            "Department",
            "Role",
            "Status"
        };

        Object[][] data = {};

        table = new JTable(data, columns);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnCreate = new JButton("Create");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        JPanel topPanel = new JPanel();

        topPanel.add(btnCreate);
        topPanel.add(btnUpdate);
        topPanel.add(btnDelete);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
