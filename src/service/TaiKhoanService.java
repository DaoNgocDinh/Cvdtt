package service;

import model.account.TaiKhoan;
import notification.UpdateNotificationPayload;
import repository.TaiKhoanRepository;
import service.RiskService;
import model.risk.RiskLevel;
import observer.Subject;
import observer.observers.AuditLogObserver;
import observer.observers.NotificationObserver;
import utils.AuthSession;

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

        TaiKhoan beforeUpdate = repository.findById(taiKhoan.getMaTaiKhoan());
        repository.update(taiKhoan);

        String changeDescription = buildChangeDescription(beforeUpdate, taiKhoan);
        UpdateNotificationPayload payload = new UpdateNotificationPayload(
                AuthSession.getCurrentUser(),
                beforeUpdate,
                taiKhoan,
                changeDescription
        );

        notifyObservers("taiKhoan.updated", payload);

        System.out.println(
                "Đã cập nhật: "
                        + taiKhoan.getMaTaiKhoan());
    }

    private String buildChangeDescription(TaiKhoan before, TaiKhoan after) {
        if (before == null || after == null) {
            return "Tai khoan da duoc cap nhat.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Admin ");
        sb.append(AuthSession.isLoggedIn() ? AuthSession.getCurrentUser().getHoTen() : "System");
        sb.append(" da cap nhat tai khoan ");
        sb.append(after.getMaTaiKhoan());
        sb.append(". ");

        if (!equals(before.getHoTen(), after.getHoTen())) {
            sb.append("Ho ten: '" + before.getHoTen() + "' -> '" + after.getHoTen() + "'. ");
        }
        if (!equals(before.getEmail(), after.getEmail())) {
            sb.append("Email: '" + before.getEmail() + "' -> '" + after.getEmail() + "'. ");
        }
        if (!equals(before.getChucVu(), after.getChucVu())) {
            sb.append("Chuc vu: '" + before.getChucVu() + "' -> '" + after.getChucVu() + "'. ");
        }
        if (!equals(before.getRoleName(), after.getRoleName())) {
            sb.append("Role: '" + before.getRoleName() + "' -> '" + after.getRoleName() + "'. ");
        }
        if (before.getLocker() != null && after.getLocker() != null && !before.getLocker().equals(after.getLocker())) {
            sb.append("Trang thai: '" + (before.getLocker() ? "Bi khoa" : "Hoat dong") + "' -> '" + (after.getLocker() ? "Bi khoa" : "Hoat dong") + "'. ");
        }
        return sb.toString();
    }

    private boolean equals(String a, String b) {
        return a == null ? b == null : a.equals(b);
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
