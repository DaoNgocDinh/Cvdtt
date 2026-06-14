package strategy.risk;

import model.risk.RuiRo;

public class RuiRoHoSoStrategy implements RuiRoStrategy {

    @Override
    public void danhGiaRuiRo(RuiRo ruiRo) {

        System.out.println("[STRATEGY-RISK] RuiRoHoSoStrategy is running.");

        ruiRo.setTenRuiRo("Rui ro ho so");
        ruiRo.setLoaiRuiRo("Ho so");

        System.out.println(
                "[STRATEGY-RISK] Danh gia rui ro ho so: "
                + ruiRo.getMaRuiRo()
                + " | Muc do: "
                + getMucDoRuiRo()
        );
    }

    @Override
    public String getMucDoRuiRo() {
        return "LOW";
    }
}