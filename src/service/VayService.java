package service;

import model.account.TaiKhoan;
import model.loan.Vay;
import repository.TaiKhoanRepository;
import repository.VayRepository;

public class VayService {
    private VayRepository vayRepository;
    private TaiKhoanRepository taiKhoanRepository;

    public VayService() {
        vayRepository = new VayRepository();
        taiKhoanRepository = new TaiKhoanRepository();
    }

    public void createVay(Vay vay) {

        // Kiểm tra khách hàng tồn tại
        TaiKhoan khachHang =
                taiKhoanRepository.findById(
                        vay.getMaTaiKhoan());

        if (khachHang == null) {

            System.out.println(
                    "Khách hàng không tồn tại");

            return;
        }

        // Kiểm tra số tiền vay
        if (vay.getSoTienVay() == null
                || vay.getSoTienVay().doubleValue() <= 0) {

            System.out.println(
                    "Số tiền vay không hợp lệ");

            return;
        }

        // Lưu DB
        vayRepository.save(vay);

        System.out.println(
                "Tạo khoản vay thành công");
    }
}
