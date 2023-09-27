package com.pro1.service;

import com.pro1.domain.Cafe;

public interface CafeServiceRepository {

    public String findCheapStore(String menu); // find cheap store from cafe data
    public boolean checkCoupon(String cafe, String menu) throws InterruptedException; // check coupon by input menu
    public void useCoupon(boolean couponUse, String store, String menu); // decision use coupon
    public Cafe paymentInfo(String store, String menu); // return current payment data
    public void choosePayment(Cafe cafe, String input) throws InterruptedException; // choose payment

}
