package strategy.vay;

import model.loan.Vay;

public class VayDaiHanStrategy implements VayStrategy {

    @Override
    public void xuLyKhoanVay(Vay vay) {
        System.out.println("[STRATEGY-VAY] VayDaiHanStrategy is running.");

        if (vay.getHanTraNo() == null) {
            vay.setHanTraNo(vay.getNgayVay().plusMonths(60));
        }

        System.out.println("[STRATEGY-VAY] Xu ly vay dai han: " + vay.getMaKhoanVay());
    }
}