package strategy.risk;

import model.risk.RuiRo;

public class RuiRoThanhToanStrategy implements RuiRoStrategy {

    @Override
    public void danhGiaRuiRo(RuiRo ruiRo) {

        System.out.println("[STRATEGY-RISK] RuiRoThanhToanStrategy is running.");

        ruiRo.setTenRuiRo("Rui ro thanh toan");
        ruiRo.setLoaiRuiRo("Thanh toan");

        System.out.println(
                "[STRATEGY-RISK] Danh gia rui ro thanh toan: "
                + ruiRo.getMaRuiRo()
                + " | Muc do: "
                + getMucDoRuiRo()
        );
    }

    @Override
    public String getMucDoRuiRo() {
        return "MEDIUM";
    }
}