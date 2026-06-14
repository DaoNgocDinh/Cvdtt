package view.risk;

import repository.RiskRepository;
import view.common.AppUi;
import model.risk.RuiRo;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RiskAlertFrame extends JFrame {
    private JTable table;
    private RiskRepository repository = new RiskRepository();

    public RiskAlertFrame() {
        AppUi.setupFrame(this, "Quản lý Rủi ro và Cảnh báo", 1050, 620);
        setContentPane(createContent());
        setVisible(true);
        loadData(); // Load dữ liệu từ DB khi mở
    }

    private JComponent createContent() {
        JPanel page = AppUi.page("Rủi ro và Cảnh báo", 
                "Phát hiện rủi ro, tạo cảnh báo và theo dõi xử lý.");

        JPanel card = AppUi.card();
        JPanel toolbar = AppUi.toolbar();

        JButton detect = AppUi.button("Phát hiện rủi ro");
        JButton detail = AppUi.secondaryButton("Chi tiết");
        JButton refresh = AppUi.button("Làm mới");

        toolbar.add(detect);
        toolbar.add(detail);
        toolbar.add(refresh);

        // Khởi tạo table
        String[] columns = {"Mã cảnh báo", "Tên rủi ro", "Loại rủi ro", "Nội dung", "Ngày phát hiện", "Mã khoản vay"};
        table = AppUi.table(columns, new Object[0][0]);

        // Các sự kiện
        detect.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Đã quét xong. Hệ thống sẽ tạo cảnh báo nếu phát hiện rủi ro mới.", 
                "Phát hiện rủi ro", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        });

        detail.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String maRuiRo = (String) table.getValueAt(row, 0);
                new RiskDetailFrame(maRuiRo);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn một dòng để xem chi tiết!", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        refresh.addActionListener(e -> loadData());

        card.add(toolbar, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(card, BorderLayout.CENTER);

        return page;
    }

    private void loadData() {
        List<RuiRo> list = repository.getAllRuiRo();
        Object[][] rows = new Object[list.size()][6];

        for (int i = 0; i < list.size(); i++) {
            RuiRo r = list.get(i);
            rows[i][0] = r.getMaRuiRo();
            rows[i][1] = r.getTenRuiRo();
            rows[i][2] = r.getLoaiRuiRo();
            rows[i][3] = truncateText(r.getChiTiet(), 70);
            rows[i][4] = r.getNgayPhatHien();
            rows[i][5] = r.getMaKhoanVay();
        }

        // Tạo model chỉ đọc (Read Only)
        DefaultTableModel model = new DefaultTableModel(
            rows, 
            new String[]{"Mã cảnh báo", "Tên rủi ro", "Loại rủi ro", "Nội dung", "Ngày phát hiện", "Mã khoản vay"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa bất kỳ ô nào
            }
        };

        table.setModel(model);
    }

    // Hàm hỗ trợ cắt ngắn nội dung dài
    private String truncateText(String text, int maxLength) {
        if (text == null || text.isEmpty()) return "";
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }
}