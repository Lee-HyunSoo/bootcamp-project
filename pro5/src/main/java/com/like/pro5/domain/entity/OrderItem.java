package com.like.pro5.domain.entity;

import com.like.pro5.domain.entity.auditing.BaseTimeEntity;
import com.like.pro5.domain.entity.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private int quantity; // 주문수량
    private int price; // 가격
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 생성 메서드
    public static OrderItem orderItem(Item item, int price, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);
        return orderItem;
    }

    // 조회 로직
    public int getTotalPrice() {
        return quantity * price;
    }

}
