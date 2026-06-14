package view.loan;

import repository.VayRepository;
import view.common.AppUi;
import model.loan.Vay;
import javax.swing.*;
import java.awt.*;

public class LoanDetailFrame extends JFrame {
    
    private VayRepository vayRepository = new VayRepository();

    public LoanDetailFrame(String maKhoanVay) {
        AppUi.setupFrame(this, "Chi tiết Khoản Vay", 640, 520);
        
        Vay vay = vayRepository.getById(maKhoanVay);
        if (vay == null) {
            JOptionPane.showMessageDialog(this, 
                "Không tìm thấy khoản vay với mã: " + maKhoanVay, 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        
        setContentPane(createContent(vay));
        setVisible(true);
    }

    private JComponent createContent(Vay vay) {
        JPanel page = AppUi.page("Chi tiết Khoản Vay", 
                "Thông tin chi tiết hồ sơ vay và trạng thái xử lý.");

        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        String[] labels = {
            "Mã vay", 
            "Mã khách hàng", 
            "Số tiền vay", 
            "Ngày vay", 
            "Hạn trả nợ", 
            "Trạng thái"
        };

        Object[] values = {
            vay.getMaKhoanVay(),
            vay.getMaTaiKhoan(),
            vay.getSoTienVay() != null ? String.format("%,.0f VNĐ", vay.getSoTienVay()) : "",
            vay.getNgayVay(),
            vay.getHanTraNo(),
            "Chờ duyệt"
        };

        for (int i = 0; i < labels.length; i++) {
            JLabel valueLabel = new JLabel(values[i] != null ? values[i].toString() : "Không có dữ liệu");
            valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            valueLabel.setForeground(AppUi.TEXT);
            AppUi.addField(form, i, labels[i], valueLabel);
        }

        JTextArea note = AppUi.textArea(4);
        note.setText("Đề xuất: Kiểm tra khả năng trả nợ và rủi ro của khách hàng trước khi phê duyệt.");
        AppUi.addField(form, labels.length, "Ghi chú / Đề xuất", new JScrollPane(note));

        JButton close = AppUi.button("Đóng");
        close.addActionListener(e -> dispose());

        JPanel actions = AppUi.toolbar();
        actions.add(close);

        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);

        return page;
    }
}