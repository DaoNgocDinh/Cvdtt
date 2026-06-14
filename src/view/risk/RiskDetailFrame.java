package view.risk;

import repository.RiskRepository;
import view.common.AppUi;
import model.risk.RuiRo;
import javax.swing.*;
import java.awt.*;

public class RiskDetailFrame extends JFrame {
    private RiskRepository repository = new RiskRepository();

    public RiskDetailFrame(String maRuiRo) {
        AppUi.setupFrame(this, "Chi tiết Cảnh báo Rủi ro", 640, 520);
        
        RuiRo ruiRo = repository.getRuiRoByMa(maRuiRo);
        if (ruiRo == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu rủi ro với mã: " + maRuiRo, 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        
        setContentPane(createContent(ruiRo));
        setVisible(true);
    }

    private JComponent createContent(RuiRo data) {
        JPanel page = AppUi.page("Chi tiết Cảnh báo Rủi ro", 
                "Thông tin chi tiết về rủi ro.");

        JPanel card = AppUi.card();
        JPanel form = AppUi.form();

        String[] labels = {"Mã cảnh báo", "Tên rủi ro", "Loại rủi ro", "Mã khoản vay", "Ngày phát hiện", "Nội dung"};
        Object[] values = {
            data.getMaRuiRo(),
            data.getTenRuiRo(),
            data.getLoaiRuiRo(),
            data.getMaKhoanVay(),
            data.getNgayPhatHien(),
            data.getChiTiet()
        };

        for (int i = 0; i < labels.length; i++) {
            JLabel value = new JLabel(values[i] != null ? values[i].toString() : "Không có dữ liệu");
            value.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            value.setForeground(AppUi.TEXT);
            AppUi.addField(form, i, labels[i], value);
        }

        JTextArea note = AppUi.textArea(5);
        note.setText("Đề xuất: Kiểm tra Audit Log, xác minh người dùng và thông báo cho bộ phận an ninh.");
        AppUi.addField(form, 6, "Hướng xử lý", new JScrollPane(note));

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