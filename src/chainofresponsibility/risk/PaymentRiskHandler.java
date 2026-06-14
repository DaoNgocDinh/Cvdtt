package chainofresponsibility.risk;

import java.math.BigDecimal;
import java.time.LocalDate;

import model.risk.RuiRo;
import strategy.risk.*;

public class PaymentRiskHandler extends RiskHandler {

    @Override
    public void handle(RiskContext context) {

        if (context.vay.getSoTienVay()
                .compareTo(new BigDecimal("100000000")) > 0) {

            RuiRo r = new RuiRo();

            r.setMaRuiRo("RR" + System.nanoTime()% 1000);
            r.setMaKhoanVay(context.vay.getMaKhoanVay());
            r.setChiTiet("Khoan vay lon hon 100 trieu");
            r.setNgayPhatHien(LocalDate.now());

            RuiRoManager manager
                    = new RuiRoManager();

            manager.setStrategy(
                    new RuiRoThanhToanStrategy());

            manager.danhGiaRuiRo(r);
        }

        handleNext(context);
    }
}
