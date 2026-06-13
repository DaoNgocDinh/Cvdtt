package view.customer;

import javax.swing.*;
import java.awt.*;

public class CreateCustomerFrame extends JFrame {

    public CreateCustomerFrame() {

        setTitle("Create Customer");
        setSize(500, 500);

        JPanel panel
                = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Customer ID"));
        panel.add(new JTextField());

        panel.add(new JLabel("Full Name"));
        panel.add(new JTextField());

        panel.add(new JLabel("Phone"));
        panel.add(new JTextField());

        panel.add(new JLabel("Address"));
        panel.add(new JTextField());

        panel.add(new JButton("Save"));

        add(panel);

        setVisible(true);
    }
}
