package com.pro1.service;

import com.pro1.domain.Cafe;
import com.pro1.domain.CafeImpl;

import java.util.ArrayList;

public class CafeServiceImpl implements CafeServiceRepository {
    private final CafeImpl cafes = new CafeImpl();
    @Override
    public String findCheapStore(String menu) {
        ArrayList<String> stores = new ArrayList<>(cafes.cafes.keySet());
        String cheapStore = "";
        int min = Integer.MAX_VALUE;
        for (String s : stores) {
            for (Cafe c : cafes.cafes.get(s)) {
                if (c.getMenu().equals(menu) && c.getPrice() < min) {
                    cheapStore = s;
                    min = c.getPrice();
                }
            }
        }
        return cheapStore;
    }

    @Override
    public boolean checkCoupon(String store, String menu) throws InterruptedException {
        Thread.sleep(2000);
        for (Cafe c : cafes.cafes.get(store))
            if (c.getMenu().equals(menu) && c.getCoupon() == 1)
                return true;
        return false;
    }

    @Override
    public void useCoupon(boolean couponUse, String store, String menu) {
        cafes.updateCafeUseCoupon(couponUse, store, menu);
        cafes.deleteUseCoupon(store, menu);
    }

    @Override
    public Cafe paymentInfo(String store, String menu) {
        return cafes.selectOneCafe(store, menu);
    }

    @Override
    public void choosePayment(Cafe cafe, String input) throws InterruptedException {
        if (input.equals("예")) {
            Thread.sleep(2000);
            cafes.insertCafePayment(cafe);
            System.out.println("결제가 완료되었습니다.");
        }
        else
            System.out.println("결제가 취소되었습니다.");
    }

    public CafeImpl getCafes() {
        return cafes;
    }
}
