package com.like.pro5.controller.dto;

import com.like.pro5.domain.entity.Address;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaymentDto {

    private String merchantUid; // 결제번호 (OrderId + new Date) -> OrderId
    private int price; // 가격 : 총 가격 Order.getTotalPrice()


    /* 유저정보 */
    private String username; // 사용자 아이디
    private String name; // 사용자 이름
    private String email;
    private String tel;
    private String address;
    private String postcode;
    private List<ItemDto> items;

}