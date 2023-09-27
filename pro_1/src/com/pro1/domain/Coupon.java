package com.pro1.domain;

public class Coupon {
    private String store; // 가게이름
    private String menu; // 메뉴이름

    public Coupon() {
    }

    public Coupon(String store, String menu) {
        this.store = store;
        this.menu = menu;
    }

    public String getStore() {
        return store;
    }

    public String getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "store='" + store + '\'' +
                ", menu='" + menu + '\'' +
                '}';
    }

}
