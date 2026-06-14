package test;

import facade.TaiKhoanFacade;

import java.math.BigDecimal;
import facade.VayFacade;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TaiKhoanTest {
    public static void main(String[] args) {
        VayFacade facade = new VayFacade();

        // ==========================
        // TEST CREATE
        // ==========================


        facade.createVay(
                "KV999",
                "KH001",
                new BigDecimal("50000000"),
                LocalDate.now(),
                LocalDate.now().plusMonths(12)
        );
    }
}
