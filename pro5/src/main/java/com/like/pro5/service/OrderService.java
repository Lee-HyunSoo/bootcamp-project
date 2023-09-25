package com.like.pro5.service;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.PaymentDto;
import com.like.pro5.domain.entity.Order;
import com.like.pro5.domain.entity.OrderItem;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.entity.item.Item;
import com.like.pro5.domain.repository.OrderRepository;
import com.like.pro5.domain.repository.UserRepository;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ThreadLocalLogTrace trace;

    /**
     * 장바구니 아이템 전부 삭제
     * @param username 사용자 아이디를 통해 장바구니 목록을 가져옴
     * @param itemIds 장바구니 항목 번호
     */
    @Transactional
    public void deleteOrderItems(String username, List<String> itemIds) {
        TraceStatus status = trace.begin("OrderService.deleteOrderItems()");
        User user = userRepository.findItemsFetch(username)
                .orElseThrow(NoSuchElementException::new);

        for (String itemId : itemIds) {
            user.getItems().removeIf(item -> item.getId().equals(Long.parseLong(itemId)));
        }

        trace.end(status);
    }

    /**
     * 장바구니 리스트 불러오기
     */
    public List<ItemDto> cartList(String username) {
        List<ItemDto> items = userRepository.findItemsFetch(username)
                .orElseThrow(NoSuchElementException::new)
                .getItems()
                .stream()
                .map(item -> new ItemDto(
                        item.getTitle(),
                        item.getPrice(),
                        item.getQuantity()
                )).collect(Collectors.toList());
        return items;
    }

    /**
     * 결제하기 버튼눌렀을떄.....
     */
    @Transactional
    public PaymentDto createOrder(String username) {
        TraceStatus status = trace.begin("OrderService.createOrder()");
        User user = userRepository.findItemsFetch(username)
                .orElseThrow(NoSuchElementException::new);

        List<OrderItem> orderItems = setOrderItems(user);
        Order order = Order.createOrder(user, orderItems);

        orderRepository.save(order);

        trace.end(status);
        return setPaymentDto(user, order);
    }

    /**
     *  결제 상세 페이지 불러오기
     */
    public Order getOrderById(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        return order;
    }

    // == 내부 메서드 ==
    private PaymentDto setPaymentDto(User user, Order order) {
        return PaymentDto.builder()
                .merchantUid(String.valueOf(order.getId() + new Date().getTime()))
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .tel(user.getTel())
                .address(user.getAddress().getCity() + user.getAddress().getStreet())
                .postcode(user.getAddress().getZipcode())
                .price(order.getTotalPrice())
                .items(user.getItems().stream()
                        .map(item -> new ItemDto(
                                item.getTitle(), item.getPrice(), item.getQuantity()
                        ))
                        .collect(Collectors.toList()))
                .build();
    }

    private List<OrderItem> setOrderItems(User user) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Item item : user.getItems()) {
            OrderItem orderItem = OrderItem.orderItem(item, item.getPrice(), item.getQuantity());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

}