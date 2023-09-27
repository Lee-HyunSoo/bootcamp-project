package com.pro1.domain;

import com.pro1.util.CurrentDateTime;

public class Cafe extends CurrentDateTime {
    private String store; // 가게이름
    private String menu; // 메뉴이름
    private int price; // 메뉴가격
    private int isCoupon; // 쿠폰 보유여부

    public Cafe() {
    }

    public Cafe(String store, String menu, int price, int isCoupon) {
        this.store = store;
        this.menu = menu;
        this.price = price;
        this.isCoupon = isCoupon;
    }

    public String getStore() {
        return store;
    }

    public String getMenu() {
        return menu;
    }

    public int getPrice() {
        return price;
    }

    public int getCoupon() {
        return isCoupon;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "store='" + store + '\'' +
                ", menu='" + menu + '\'' +
                ", price=" + price +
                '}';
    }
}
