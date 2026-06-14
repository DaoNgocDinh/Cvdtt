package command.loan;

import command.Command;
import model.loan.Vay;
import service.VayService;

public class CreateVayCommand implements Command {
    private VayService service;
    private Vay vay;

    public CreateVayCommand(
            VayService service,
            Vay vay) {

        this.service = service;
        this.vay = vay;
    }

    @Override
    public void execute() {

        service.createVay(vay);
    }
}
