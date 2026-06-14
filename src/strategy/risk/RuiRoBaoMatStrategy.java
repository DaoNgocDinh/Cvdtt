package strategy.risk;

import model.risk.RuiRo;

public class RuiRoBaoMatStrategy implements RuiRoStrategy {

    @Override
    public void danhGiaRuiRo(RuiRo ruiRo) {
        System.out.println("[STRATEGY-RISK] RuiRoBaoMatStrategy is running.");

        ruiRo.setTenRuiRo("Rui ro bao mat");
        ruiRo.setLoaiRuiRo("Bao mat");

        System.out.println("[STRATEGY-RISK] Danh gia rui ro bao mat: "
                + ruiRo.getMaRuiRo());
    }
}