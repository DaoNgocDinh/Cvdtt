package view.customer;

import javax.swing.*;
import java.awt.*;

public class CustomerListFrame extends JFrame {

    public CustomerListFrame() {

        setTitle("Customer Management");
        setSize(900,500);

        JTable table = new JTable();

        JButton btnCreate =
                new JButton("Create Customer");

        add(btnCreate, BorderLayout.NORTH);
        add(new JScrollPane(table),
                BorderLayout.CENTER);

        setVisible(true);
    }
}