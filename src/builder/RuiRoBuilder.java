package builder;

import java.time.LocalDate;
import model.risk.RuiRo;

/**
 * Builder for the RuiRo model matching the SQL table schema.
 */
public class RuiRoBuilder {

    private RuiRo ruiRo;

    private RuiRoBuilder() {
        this.ruiRo = new RuiRo();
    }

    public static RuiRoBuilder builder() {
        return new RuiRoBuilder();
    }

    public RuiRoBuilder setMaRuiRo(String maRuiRo) {
        ruiRo.setMaRuiRo(maRuiRo);
        return this;
    }

    public RuiRoBuilder setMaKhoanVay(String maKhoanVay) {
        ruiRo.setMaKhoanVay(maKhoanVay);
        return this;
    }

    public RuiRoBuilder setTenRuiRo(String tenRuiRo) {
        ruiRo.setTenRuiRo(tenRuiRo);
        return this;
    }

    public RuiRoBuilder setLoaiRuiRo(String loaiRuiRo) {
        ruiRo.setLoaiRuiRo(loaiRuiRo);
        return this;
    }

    public RuiRoBuilder setChiTiet(String chiTiet) {
        ruiRo.setChiTiet(chiTiet);
        return this;
    }

    public RuiRoBuilder setNgayPhatHien(LocalDate ngayPhatHien) {
        ruiRo.setNgayPhatHien(ngayPhatHien);
        return this;
    }

    public RuiRo build() {
        return ruiRo;
    }
}
