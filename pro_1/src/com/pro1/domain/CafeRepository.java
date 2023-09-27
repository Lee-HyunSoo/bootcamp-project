package com.pro1.domain;

public interface CafeRepository {

    public void selectAllCafe(); // select All cafe data
    public Cafe selectOneCafe(String store, String menu); // select one cafe data
    public void selectAllCoupon(); // select All coupon data
    public void updateCafeByCoupon(); // use coupon data, update cafe data
    public void updateCafeUseCoupon(boolean couponUse, String store, String menu); // if use coupon, update cafe data
    public void deleteUseCoupon(String store, String menu); // if use coupon, delete from coupon
    public void insertCafePayment(Cafe cafe); // insert payment info in cafepayment table

}
