package command.TaiKhoan;

import command.Command;
import model.account.TaiKhoan;
import service.TaiKhoanService ;

public class CreateTaiKhoanCommand implements Command {

    private TaiKhoanService service;
    private TaiKhoan taiKhoan;

    public CreateTaiKhoanCommand(
            TaiKhoanService service,
            TaiKhoan taiKhoan) {

        this.service = service;
        this.taiKhoan = taiKhoan;
    }

    @Override
    public void execute() {

        service.createTaiKhoan(taiKhoan);

    }
}
