package com.pro1.domain;

import com.pro1.db.MySQLQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CafeImpl implements CafeRepository {

    public Map<String, ArrayList<Cafe>> cafes = new HashMap<>(); // classify cafe data
    private ArrayList<Coupon> coupons = new ArrayList<>(); // coupon data
    private final MySQLQuery mySQLQuery = new MySQLQuery(); // query method

    public CafeImpl() {
        this.selectAllCafe();
        this.selectAllCoupon();
        this.updateCafeByCoupon();
    }

    @Override
    public void selectAllCafe() {
        cafes = mySQLQuery.selectAllCafe();
    }

    @Override
    public Cafe selectOneCafe(String store, String menu) {
        return mySQLQuery.selectOneCafe(store, menu);
    }

    @Override
    public void selectAllCoupon() {
        coupons = mySQLQuery.selectAllCoupon();
    }

    @Override
    public void updateCafeByCoupon() {
        mySQLQuery.updateCafeByCoupon(coupons);
    }

    @Override
    public void updateCafeUseCoupon(boolean couponUse, String store, String menu) {
        mySQLQuery.updateCafeUseCoupon(couponUse, store, menu);
    }

    @Override
    public void deleteUseCoupon(String store, String menu) {
        mySQLQuery.deleteUseCoupon(store, menu);
    }

    @Override
    public void insertCafePayment(Cafe cafe) {
        mySQLQuery.insertCafePayment(cafe);
    }

}
