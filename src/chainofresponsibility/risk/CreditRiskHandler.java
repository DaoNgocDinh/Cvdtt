package chainofresponsibility.risk;

import java.math.BigDecimal;
import java.time.LocalDate;

import model.risk.RuiRo;
import strategy.risk.*;

public class CreditRiskHandler extends RiskHandler {

    @Override
    public void handle(RiskContext context) {

        if (context.khachHang.getSoTienConNo()
                .compareTo(new BigDecimal("50000000")) > 0) {

            RuiRo r = new RuiRo();

            r.setMaRuiRo("RR" + System.currentTimeMillis()% 1000);
            r.setMaKhoanVay(context.vay.getMaKhoanVay());
            r.setChiTiet("Khach hang dang no qua 50 trieu");
            r.setNgayPhatHien(LocalDate.now());

            RuiRoManager manager
                    = new RuiRoManager();

            manager.setStrategy(
                    new RuiRoTinDungStrategy());

            manager.danhGiaRuiRo(r);
        }

        handleNext(context);
    }
}
