package com.like.pro5.domain.entity;

import com.like.pro5.domain.entity.auditing.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // [CANCEL, ORDER]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> orderItems = new ArrayList<>();

    // == 연관관계 메서드 ==
    public void addUserAndOrder(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void addOrderAndItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // == 생성 메서드 ==
    public static Order createOrder(User user, List<OrderItem> orderItems) {
        Order order = new Order();
        order.addUserAndOrder(user);

        for (OrderItem orderItem : orderItems) {
            order.addOrderAndItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        return order;
    }

    // == 비즈니스 로직 ==
    // 멤버변수 (엔티티 필드) 의 값을 바꾸는 로직
    public void cancel() {
        this.orderStatus = OrderStatus.CANCEL;
    }

    // == 조회 로직 ==
    // 약간의 계산이 필요한 로직
    // 필드에 굳이 변수를 생성하지 않고 값을 계산해서 반환
    // 만약 개발 중 접근량이 많은 변수는 멤버변수로 끌어올리면 된다.
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
