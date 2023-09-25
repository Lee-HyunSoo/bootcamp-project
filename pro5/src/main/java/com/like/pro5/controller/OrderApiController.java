package com.like.pro5.controller;


import com.like.pro5.controller.dto.PaymentDto;
import com.like.pro5.security.custom.CustomUserDetails;
import com.like.pro5.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    private final OrderService orderService;

//    @PostMapping("/createOrder" )
//    public ResponseEntity<PaymentDto> createOrder(@AuthenticationPrincipal CustomUserDetails userDetail) {
//        PaymentDto paymentDto = orderService.createOrder(userDetail.getUsername());
//        if (paymentDto == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(paymentDto);
//    }


    @DeleteMapping("/deleteOrderItems")
    public ResponseEntity<?> deleteOrderItem(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody List<String> orderItemId) {
        log.info("orderItemId : {}", orderItemId);
        orderService.deleteOrderItems(userDetails.getUsername(), orderItemId);
        return ResponseEntity.ok().build();
    }


}