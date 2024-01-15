package com.example.affablebeanui.config;

import com.example.affablebeanui.data.AccountRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${payment.backend.url}",value = "payment-feign-client")
public interface PaymentFeignClient {
    @PostMapping(value={"/payment/make-payment"})
    ResponseEntity makePayment(@RequestBody AccountRequest request);
}
