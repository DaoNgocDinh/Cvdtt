package service;

import model.account.TaiKhoan;
import repository.TaiKhoanRepository;
import service.RiskService;
import model.risk.RiskLevel;

public class TaiKhoanService {
    private final TaiKhoanRepository repository;
    private final RiskService riskService;

    public TaiKhoanService() {
        repository = new TaiKhoanRepository();
        riskService = new RiskService();
    }

    public void createTaiKhoan(TaiKhoan taiKhoan) {

        repository.save(taiKhoan);

        System.out.println(
                "Tạo tài khoản thành công: "
                        + taiKhoan.getMaTaiKhoan());
    }
/// ///////////////////////////////////////////////////
///
///
    public void updateTaiKhoan(TaiKhoan taiKhoan) {

        repository.update(taiKhoan);

        System.out.println(
                "Đã cập nhật: "
                        + taiKhoan.getMaTaiKhoan());
    }

    public void deleteTaiKhoan(
            String maTaiKhoan) {

        TaiKhoan taiKhoan =
                repository.findById(
                        maTaiKhoan);

        if(taiKhoan == null){

            System.out.println(
                    "Tài khoản không tồn tại");

            return;
        }

        RiskLevel level =
                riskService
                        .evaluateDeleteRisk(
                                taiKhoan);

        switch(level){

            case HIGH:

                System.out.println(
                        "Rủi ro cao. Không thể xóa.");

                return;

            case MEDIUM:

                System.out.println(
                        "Cảnh báo: tài khoản đang hoạt động.");

                break;

            case LOW:

                break;
        }

        repository.delete(maTaiKhoan);

        System.out.println(
                "Xóa thành công.");
    }

    public void lockTaiKhoan(String maTaiKhoan) {

        System.out.println("Khóa tài khoản");
    }
}
