package chainofresponsibility.risk;

import java.time.LocalDate;

import model.risk.RuiRo;
import strategy.risk.*;

public class DocumentRiskHandler extends RiskHandler {

    @Override
    public void handle(RiskContext context) {

        if (context.khachHang.getCccd() == null
                || context.khachHang.getCccd().trim().isEmpty()) {

            RuiRo r = new RuiRo();

            r.setMaRuiRo("RR" + System.nanoTime()% 1000);
            r.setMaKhoanVay(context.vay.getMaKhoanVay());
            r.setChiTiet("Khach hang thieu CCCD");
            r.setNgayPhatHien(LocalDate.now());

            RuiRoManager manager
                    = new RuiRoManager();

            manager.setStrategy(
                    new RuiRoHoSoStrategy());

            manager.danhGiaRuiRo(r);
        }

        handleNext(context);
    }
}
