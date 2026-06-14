package view.loan;

import facade.VayFacade;
import model.loan.Vay;
import utils.Authorization;
import view.common.AppUi;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoanListFrame extends JFrame {

    private JTable table;
    private VayFacade vayFacade;

    public LoanListFrame() {
        AppUi.setupFrame(this, "Quan ly khoan vay", 1050, 620);
        this.vayFacade = new VayFacade();
        setContentPane(createContent());
        setVisible(true);
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Quan ly khoan vay", "Tao, kiem tra va phe duyet ho so vay cua khach hang.");
        JPanel card = AppUi.card();

        JPanel toolbar = AppUi.toolbar();
        JButton create = AppUi.button("Tao khoan vay");
        JButton detail = AppUi.secondaryButton("Chi tiet");
        JButton approve = AppUi.button("Phe duyet", AppUi.SUCCESS, Color.WHITE);
        JButton reject = AppUi.dangerButton("Tu choi");
        toolbar.add(create);
        toolbar.add(detail);
        toolbar.add(approve);
        toolbar.add(reject);

        String[] columns = {"Ma vay", "Ma KH", "So tien", "Ngay vay", "Han tra no"};
        Object[][] rows = loadLoanData();
        table = AppUi.table(columns, rows);

        create.addActionListener(e -> {
        if (Authorization.requireAnyRole(this, "ADMIN", "NHANVIEN")) {
            new CreateLoanFrame(new VayFacade()).setVisible(true);
        }
    });
        detail.addActionListener(e -> {
        int row = selectedRow();
        if (row >= 0) {
            new LoanDetailFrame(rowData(row));
        }
    });
        approve.addActionListener(e -> {
        if (!Authorization.requireAnyRole(this, "ADMIN", "MANAGER")) {
            return;
        }
        int row = selectedRow();
        if (row >= 0) {
            new LoanApprovalFrame(rowData(row), true);
            table.setValueAt("Da duyet", row, 7);
        }
    });
        reject.addActionListener(e -> {
        if (!Authorization.requireAnyRole(this, "ADMIN", "MANAGER")) {
            return;
        }
        int row = selectedRow();
        if (row >= 0) {
            new LoanApprovalFrame(rowData(row), false);
            table.setValueAt("Tu choi", row, 7);
        }
    });

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);
        return page;
}

private int selectedRow() {
    int row = table.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Vui long chon mot khoan vay.", "Chua chon du lieu", JOptionPane.WARNING_MESSAGE);
    }
    return row;
}

private String[] rowData(int row) {
    String[] data = new String[table.getColumnCount()];
    for (int i = 0; i < data.length; i++) {
        data[i] = String.valueOf(table.getValueAt(row, i));
    }
    return data;
}

private Object[][] loadLoanData() {
    try {
        List<Vay> vayList = vayFacade.getAllVay();
        Object[][] rows = new Object[vayList.size()][8];

        for (int i = 0; i < vayList.size(); i++) {
            Vay vay = vayList.get(i);
            rows[i][0] = vay.getMaKhoanVay();
            rows[i][1] = vay.getMaTaiKhoan();
            rows[i][2] = vay.getSoTienVay() != null ? vay.getSoTienVay().toString() : "";
            rows[i][3] = vay.getHanTraNo() != null ? vay.getHanTraNo().toString() : "";
            rows[i][4] = "-";
            rows[i][5] = "-";
            rows[i][6] = "-";
            rows[i][7] = "-";
        }
        return rows;
    } catch (Exception e) {
        e.printStackTrace();
        return new Object[0][8];
    }
}
}
