package strategy.vay;

import model.loan.Vay;

public class VayNganHanStrategy implements VayStrategy {

    @Override
    public void xuLyKhoanVay(Vay vay) {
        System.out.println("[STRATEGY-VAY] VayNganHanStrategy is running.");

        if (vay.getHanTraNo() == null) {
            vay.setHanTraNo(vay.getNgayVay().plusMonths(6));
        }

        System.out.println("[STRATEGY-VAY] Xu ly vay ngan han: " + vay.getMaKhoanVay());
    }
}