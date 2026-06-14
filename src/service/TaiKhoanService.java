package service;

import model.account.TaiKhoan;
import repository.TaiKhoanRepository;
public class TaiKhoanService {
    private final TaiKhoanRepository repository;

    public TaiKhoanService() {
        repository = new TaiKhoanRepository();
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

    public void deleteTaiKhoan(String maTaiKhoan) {
        repository.delete(maTaiKhoan);

        System.out.println("Đã xóa tài khoản" + maTaiKhoan);
    }

    public void lockTaiKhoan(String maTaiKhoan) {

        System.out.println("Khóa tài khoản");
    }
}
