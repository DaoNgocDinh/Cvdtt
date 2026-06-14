package facade;
import command.loan.CreateVayCommand;
import factory.VayFactory;
import model.loan.Vay;
import service.VayService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class VayFacade {
    private VayService service;

    public VayFacade() {

        service =
                new VayService();
    }

    public void createVay(
            String maKhoanVay,
            String maTaiKhoan,
            BigDecimal soTienVay,
            LocalDate ngayVay,
            LocalDate hanTraNo) {

        Vay vay =
                VayFactory.createVay(
                        maKhoanVay,
                        maTaiKhoan,
                        soTienVay,
                        ngayVay,
                        hanTraNo);

        CreateVayCommand command =
                new CreateVayCommand(
                        service,
                        vay);

        command.execute();
    }

    public List<Vay> getAllVay() {
        return service.getAllLoans();
    }
}
