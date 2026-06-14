package strategy.vay;

import model.loan.Vay;

public class VayTrungHanStrategy implements VayStrategy {

    @Override
    public void xuLyKhoanVay(Vay vay) {
        System.out.println("[STRATEGY-VAY] VayTrungHanStrategy is running.");

        if (vay.getHanTraNo() == null) {
            vay.setHanTraNo(vay.getNgayVay().plusMonths(24));
        }

        System.out.println("[STRATEGY-VAY] Xu ly vay trung han: " + vay.getMaKhoanVay());
    }
}