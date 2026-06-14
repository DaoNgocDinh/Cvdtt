package model.audit;

public class AuditLog {

    private String thoiGian;
    private String nguoiDung;
    private String vaiTro;
    private String hanhDong;
    private String doiTuong;
    private String ketQua;

    public AuditLog(
            String thoiGian,
            String nguoiDung,
            String vaiTro,
            String hanhDong,
            String doiTuong,
            String ketQua) {

        this.thoiGian = thoiGian;
        this.nguoiDung = nguoiDung;
        this.vaiTro = vaiTro;
        this.hanhDong = hanhDong;
        this.doiTuong = doiTuong;
        this.ketQua = ketQua;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public String getNguoiDung() {
        return nguoiDung;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public String getKetQua() {
        return ketQua;
    }
}
