package view.loan;

import javax.swing.*;
import java.awt.*;

public class LoanListFrame extends JFrame {

    public LoanListFrame() {

        setTitle("Loan Management");
        setSize(900, 500);

        JTable table = new JTable();

        JButton btnCreate
                = new JButton("Create Loan");

        JButton btnApprove
                = new JButton("Approve");

        JButton btnReject
                = new JButton("Reject");

        JPanel top = new JPanel();

        top.add(btnCreate);
        top.add(btnApprove);
        top.add(btnReject);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table),
                BorderLayout.CENTER);

        setVisible(true);
    }
}
