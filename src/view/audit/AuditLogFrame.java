package view.audit;

import javax.swing.*;
import java.awt.*;

public class AuditLogFrame extends JFrame {

    public AuditLogFrame() {

        setTitle("Audit Log");
        setSize(1000, 500);

        String[] columns = {
            "User",
            "Action",
            "Time"
        };

        JTable table
                = new JTable(new Object[][]{},
                columns);

        add(new JScrollPane(table));

        setVisible(true);
    }
}
