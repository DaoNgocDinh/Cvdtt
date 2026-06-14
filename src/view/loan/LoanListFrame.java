package view.loan;

import repository.VayRepository;
import view.common.AppUi;
import model.loan.Vay;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class LoanListFrame extends JFrame {
    private JTable table;
    private VayRepository vayRepository = new VayRepository();

    public LoanListFrame() {
        AppUi.setupFrame(this, "Quản lý Khoản Vay", 1100, 650);
        setContentPane(createContent());
        setVisible(true);
        loadData();
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Quản lý Khoản Vay", 
                "Tạo, kiểm tra và phê duyệt hồ sơ vay của khách hàng.");

        JPanel card = AppUi.card();
        JPanel toolbar = AppUi.toolbar();

        JButton create = AppUi.button("Tạo khoản vay");
        JButton detail = AppUi.secondaryButton("Chi tiết");
        JButton approve = AppUi.button("Phê duyệt", AppUi.SUCCESS, Color.WHITE);
        JButton reject = AppUi.dangerButton("Từ chối");

        toolbar.add(create);
        toolbar.add(detail);
        toolbar.add(approve);
        toolbar.add(reject);

        String[] columns = {"Mã vay", "Mã KH", "Số tiền", "Ngày vay", "Hạn trả", "Trạng thái"};
        table = AppUi.table(columns, new Object[0][0]);

        create.addActionListener(e -> new CreateLoanFrame());

        detail.addActionListener(e -> {
            int row = selectedRow();
            if (row >= 0) {
                String maVay = (String) table.getValueAt(row, 0);
                new LoanDetailFrame(maVay);
            }
        });

        approve.addActionListener(e -> updateTrangThai(selectedRow(), "Đã duyệt"));
        reject.addActionListener(e -> updateTrangThai(selectedRow(), "Từ chối"));

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);

        return page;
    }

    private void loadData() {
        List<Vay> list = vayRepository.getAll();
        Object[][] rows = new Object[list.size()][6];

        for (int i = 0; i < list.size(); i++) {
            Vay v = list.get(i);
            rows[i][0] = v.getMaKhoanVay();
            rows[i][1] = v.getMaTaiKhoan();
            rows[i][2] = v.getSoTienVay() != null ? String.format("%,.0f", v.getSoTienVay()) : "";
            rows[i][3] = v.getNgayVay();
            rows[i][4] = v.getHanTraNo();
            rows[i][5] = "Chờ duyệt";
        }

        DefaultTableModel model = new DefaultTableModel(rows, 
            new String[]{"Mã vay", "Mã KH", "Số tiền", "Ngày vay", "Hạn trả", "Trạng thái"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(model);
    }

    private void updateTrangThai(int row, String trangThai) {
        if (row >= 0) {
            table.setValueAt(trangThai, row, 5);
            AppUi.success(this, "Đã cập nhật trạng thái: " + trangThai);
        }
    }

    private int selectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khoản vay.", 
                    "Chưa chọn dữ liệu", JOptionPane.WARNING_MESSAGE);
        }
        return row;
    }
}