package view.loan;

import view.common.AppUi;

import javax.swing.*;
import java.awt.*;

public class LoanApprovalFrame extends JFrame {

    public LoanApprovalFrame() {
        this(new String[]{"VAY001", "KH001", "500,000,000", "36 thang", "9.5%", "Mua nha", "Thap", "Cho duyet"}, true);
    }

    public LoanApprovalFrame(String[] data, boolean approving) {
        AppUi.setupFrame(this, approving ? "Phe duyet khoan vay" : "Tu choi khoan vay", 640, 520);
        setContentPane(createContent(data, approving));
        setVisible(true);
    }

    private JComponent createContent(String[] data, boolean approving) {
        JPanel page = AppUi.page(approving ? "Phe duyet khoan vay" : "Tu choi khoan vay", "Ghi nhan ket qua xu ly va gui thong bao den cac bo phan lien quan.");
        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        JLabel loan = new JLabel(data[0] + " - " + data[2]);
        loan.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loan.setForeground(AppUi.TEXT);
        JTextArea note = AppUi.textArea(5);
        JComboBox<String> risk = AppUi.combo("Thap", "Trung binh", "Cao");
        risk.setSelectedItem(data[6]);

        AppUi.addField(form, 0, "Khoan vay", loan);
        AppUi.addField(form, 1, "Danh gia rui ro", risk);
        AppUi.addField(form, 2, "Ghi chu xu ly", new JScrollPane(note));

        JPanel actions = AppUi.toolbar();
        JButton confirm = approving ? AppUi.button("Xac nhan phe duyet", AppUi.SUCCESS, Color.WHITE) : AppUi.dangerButton("Xac nhan tu choi");
        JButton cancel = AppUi.secondaryButton("Huy");
        actions.add(confirm);
        actions.add(cancel);

        confirm.addActionListener(e -> {
            AppUi.success(this, approving
                    ? "Khoan vay da duoc phe duyet. Audit Log va thong bao da duoc gui."
                    : "Khoan vay da bi tu choi. Ly do xu ly da duoc ghi nhan.");
            dispose();
        });
        cancel.addActionListener(e -> dispose());

        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);
        return page;
    }
}
