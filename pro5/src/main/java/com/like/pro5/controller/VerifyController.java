package com.like.pro5.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/verifyIamport")
public class VerifyController {

    /** Iamport 결제 검증 컨트롤러 **/
    private final IamportClient iamportClient;

    public VerifyController() {
        this.iamportClient = new IamportClient("1112151020785620",
                "FtDIyGuhSgKOAS582JtWScGG4brshV3rLKBHCoRL3uNNRcvZ0VVUyziEMTGtnTnTmXIIaGD5sXV9khpE");
    }

    @ResponseBody
    @RequestMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
        return iamportClient.paymentByImpUid(imp_uid);
    }


//    // 생성자를 통해 REST API 와 REST API secret 입력
//    public VerifyController(){
//        this.iamportClient = new IamportClient("1112151020785620",
//                "FtDIyGuhSgKOAS582JtWScGG4brshV3rLKBHCoRL3uNNRcvZ0VVUyziEMTGtnTnTmXIIaGD5sXV9khpE");
//    }
//
//    /** 프론트에서 받은 PG사 결괏값을 통해 아임포트 토큰 발행 **/
//    @PostMapping("/{imp_uid}")
//    public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid) throws
//            IamportResponseException, IOException {
//        log.info("paymentByImpUid 진입");
//        return iamportClient.paymentByImpUid(imp_uid);
//    }

//    @PostMapping("/verifyIamport/{imp_uid}")
//    public ResponseEntity<PaymentResponse> verifyIamport(@PathVariable("imp_uid") String impUid) {
//        // 결제 정보를 담은 객체를 생성하고 반환합니다.
//        PaymentResponse paymentResponse = new PaymentResponse();
//        paymentResponse.getItems().
//                IsetId(123L);
//        paymentResponse.setName("IT 강의");
//        paymentResponse.setPrice(1400);
//        paymentResponse.setBuyerEmail("COVISCON@naver.com");
//        paymentResponse.setBuyerName("COVISCON");
//        paymentResponse.setBuyerTel("010-1234-5678");
//        paymentResponse.setBuyerAddr("서울특별시 서초구 효령로");
//        paymentResponse.setBuyerPostcode("123-456");
//
//        return ResponseEntity.ok(paymentResponse);
//    }


    //    @PostMapping("/verifyIamport/{imp_uid}")
//    public ResponseEntity<PaymentResponse> verifyIamport(@PathVariable("imp_uid") String impUid) {
//        // 결제 정보를 담은 객체를 생성하고 반환합니다.
//        PaymentResponse paymentResponse = new PaymentResponse();
//        paymentResponse.getItems().
//                IsetId(123L);
//        paymentResponse.setName("IT 강의");
//        paymentResponse.setPrice(1400);
//        paymentResponse.setBuyerEmail("COVISCON@naver.com");
//        paymentResponse.setBuyerName("COVISCON");
//        paymentResponse.setBuyerTel("010-1234-5678");
//        paymentResponse.setBuyerAddr("서울특별시 서초구 효령로");
//        paymentResponse.setBuyerPostcode("123-456");
//
//        return ResponseEntity.ok(paymentResponse);
//    }


//    @PostMapping("/createOrder" )
//    public String createOrder(@AuthenticationPrincipal CustomUserDetails userDetail, Model model) {
//        PaymentDto paymentDto = orderService.createOrder(userDetail.getUsername());
//        model.addAttribute("paymentDto", paymentDto);
//
//        return "order/payment";
//    }

}