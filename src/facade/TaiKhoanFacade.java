package facade;

import command.TaiKhoan.CreateTaiKhoanCommand;
import factory.TaiKhoanFactory;
import model.account.TaiKhoan;
import service.TaiKhoanService;
import command.TaiKhoan.DeleteTaiKhoanCommand;
import command.TaiKhoan.UpdateTaiKhoanCommand;

import java.math.BigDecimal;

public class TaiKhoanFacade {
    private final TaiKhoanService taiKhoanService;

    public TaiKhoanFacade() {
        taiKhoanService = new TaiKhoanService();
    }

    // ==========================
    // ADMIN
    // ==========================

    public void createAdmin(
            String maTaiKhoan,
            String hoTen,
            String email,
            String matKhau) {

        TaiKhoan taiKhoan =
                TaiKhoanFactory.createAdmin(
                        maTaiKhoan,
                        hoTen,
                        email,
                        matKhau);

        CreateTaiKhoanCommand command =
                new CreateTaiKhoanCommand(
                        taiKhoanService,
                        taiKhoan);

        command.execute();
    }
    public void createNhanVien(
            String maTaiKhoan,
            String hoTen,
            String email,
            String matKhau) {
        createNhanVien(maTaiKhoan, hoTen, email, matKhau, "Chuyên viên tín dụng", "NHANVIEN");
    }

    public void createNhanVien(
            String maTaiKhoan,
            String hoTen,
            String email,
            String matKhau,
            String chucVu,
            String roleName) {

        TaiKhoan taiKhoan =
                TaiKhoanFactory.createNhanVien(
                        maTaiKhoan,
                        hoTen,
                        email,
                        matKhau,
                        chucVu,
                        roleName);

        CreateTaiKhoanCommand command =
                new CreateTaiKhoanCommand(
                        taiKhoanService,
                        taiKhoan);

        command.execute();
    }
    public void createKhachHang(
            String maTaiKhoan,
            String hoTen,
            String email,
            String matKhau,
            String soDienThoai,
            String cccd,
            BigDecimal soTienConNo) {

        createKhachHang(maTaiKhoan, hoTen, email, matKhau, soDienThoai, cccd, soTienConNo, "Ca nhan");
    }

    public void createKhachHang(
            String maTaiKhoan,
            String hoTen,
            String email,
            String matKhau,
            String soDienThoai,
            String cccd,
            BigDecimal soTienConNo,
            String chucVu) {

        TaiKhoan taiKhoan =
                TaiKhoanFactory.createKhachHang(
                        maTaiKhoan,
                        hoTen,
                        email,
                        matKhau,
                        soDienThoai,
                        cccd,
                        soTienConNo,
                        chucVu);

        CreateTaiKhoanCommand command =
                new CreateTaiKhoanCommand(
                        taiKhoanService,
                        taiKhoan);

        command.execute();
    }

    public void deleteTaiKhoan(String maTaiKhoan) {

        DeleteTaiKhoanCommand command =
                new DeleteTaiKhoanCommand(
                        taiKhoanService,
                        maTaiKhoan);

        command.execute();
    }

    public void updateTaiKhoan(
            TaiKhoan taiKhoan) {

        UpdateTaiKhoanCommand command =
                new UpdateTaiKhoanCommand(
                        taiKhoanService,
                        taiKhoan);

        command.execute();
    }

    public TaiKhoan getTaiKhoanById(String maTaiKhoan) {
        return taiKhoanService.findById(maTaiKhoan);
    }

    public java.util.List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanService.findAll();
    }
}
