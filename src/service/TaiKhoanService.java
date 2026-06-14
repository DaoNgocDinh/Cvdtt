package service;

import model.account.TaiKhoan;
import repository.TaiKhoanRepository;
import service.RiskService;
import model.risk.RiskLevel;
import observer.Subject;
import observer.observers.AuditLogObserver;
import observer.observers.NotificationObserver;

public class TaiKhoanService extends Subject {
    private final TaiKhoanRepository repository;
    private final RiskService riskService;

    public TaiKhoanService() {
        repository = new TaiKhoanRepository();
        riskService = new RiskService();
        registerObserver(new AuditLogObserver());
        registerObserver(new NotificationObserver());
    }

    public void createTaiKhoan(TaiKhoan taiKhoan) {

        repository.save(taiKhoan);
        notifyObservers("taiKhoan.created", taiKhoan);

        System.out.println(
                "Tạo tài khoản thành công: "
                        + taiKhoan.getMaTaiKhoan());
    }
/// ///////////////////////////////////////////////////
///
///
    public void updateTaiKhoan(TaiKhoan taiKhoan) {

        repository.update(taiKhoan);
        notifyObservers("taiKhoan.updated", taiKhoan);

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
        notifyObservers("taiKhoan.deleted", taiKhoan);

        System.out.println(
                "Xóa thành công.");
    }

    public TaiKhoan findById(String maTaiKhoan) {
        return repository.findById(maTaiKhoan);
    }

    public java.util.List<TaiKhoan> findAll() {
        return repository.findAll();
    }

    public void lockTaiKhoan(String maTaiKhoan) {

        System.out.println("Khóa tài khoản");
    }
}
