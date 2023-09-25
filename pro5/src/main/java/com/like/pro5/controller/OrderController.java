package com.like.pro5.controller;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.PaymentDto;
import com.like.pro5.domain.entity.Order;
import com.like.pro5.domain.entity.item.Item;
import com.like.pro5.domain.repository.ItemRepository;
import com.like.pro5.security.custom.CustomUserDetails;
import com.like.pro5.service.ItemService;
import com.like.pro5.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;

    @PostMapping("/add/cart")
    public String addCart(@AuthenticationPrincipal CustomUserDetails userDetails, @ModelAttribute ItemDto itemDto){
        itemService.addCart(userDetails.getUsername(), itemDto);
        return "redirect:/cart/list";
    }

    @GetMapping("/cart/list")
    public String getOrderList(@AuthenticationPrincipal CustomUserDetails userDetails, Model model){
        List<ItemDto> itemDto = orderService.cartList(userDetails.getUsername());
        model.addAttribute("itemDto", itemDto);
        return "order/cart";
    }

//    /**
//     * 결제하기를 누르면 해당 목록을 가져옴
//     * @param orderId
//     * @param model
//     * @return
//     */
//    @GetMapping("/pay")
//    public String orderDetail(@RequestParam String orderId, Model model){
//        Order order = orderService.getOrderById(Long.valueOf(orderId));
//        model.addAttribute("order", order);
//        return "order/payment2";  // orderId에 따른 주문 상세 목록을 가져옴
//    }

    @GetMapping("/createOrder" )
    public String createOrder(@AuthenticationPrincipal CustomUserDetails userDetail, Model model) {
        PaymentDto paymentDto = orderService.createOrder(userDetail.getUsername());
        log.info("paymentDto : {}", paymentDto);
        model.addAttribute("paymentDto", paymentDto);

        return "order/payment";
    }

}
