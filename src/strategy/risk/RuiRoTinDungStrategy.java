package strategy.risk;

import model.risk.RuiRo;

public class RuiRoTinDungStrategy implements RuiRoStrategy {

    @Override
    public void danhGiaRuiRo(RuiRo ruiRo) {

        System.out.println("[STRATEGY-RISK] RuiRoTinDungStrategy is running.");

        ruiRo.setTenRuiRo("Rui ro tin dung");
        ruiRo.setLoaiRuiRo("Tin dung");

        System.out.println(
                "[STRATEGY-RISK] Danh gia rui ro tin dung: "
                + ruiRo.getMaRuiRo()
                + " | Muc do: "
                + getMucDoRuiRo()
        );
    }

    @Override
    public String getMucDoRuiRo() {
        return "HIGH";
    }
}