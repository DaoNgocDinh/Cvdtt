package command.TaiKhoan;

import command.Command;
import model.account.TaiKhoan;
import service.TaiKhoanService ;

public class DeleteTaiKhoanCommand implements Command {
    private TaiKhoanService service;
    private String maTaiKhoan;

    public DeleteTaiKhoanCommand(
            TaiKhoanService service,
            String maTaiKhoan) {

        this.service = service;
        this.maTaiKhoan = maTaiKhoan;
    }

    @Override
    public void execute() {

        service.deleteTaiKhoan(maTaiKhoan);

    }
}
