package com.example.deliveryservice.service;

import com.example.deliveryservice.controller.DeliveryController;
import com.example.deliveryservice.dao.OrderProductDao;
import com.example.deliveryservice.entity.OrderProduct;
import com.example.deliveryservice.entity.Product;
import com.example.deliveryservice.entity.UserInfo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.deliveryservice.controller.DeliveryController.DeliveryRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final OrderProductDao orderProductDao;
    public OrderProduct findOrderProductByOrderId(String orderId){
        return orderProductDao.findByOrderId(orderId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public OrderProduct saveOrderProduct(DeliveryRequest request) {
        UserInfo userInfo=new UserInfo();
        userInfo.setAddress(request.address());
        userInfo.setName(request.name());
        userInfo.setEmail(request.email());
        userInfo.setCreditCardNumber(request.creditCardNumber());
        userInfo.setPhone(request.phone());

        OrderProduct orderProduct=new OrderProduct();
        request.products()
                .stream()
                .map(p -> new Product(p.name(),p.price(),p.quantity()))
                .forEach(p -> orderProduct.addProduct(p));

        orderProduct.setUserInfo(userInfo);
        orderProduct.setOrderDate(LocalDate.now());
        orderProduct.setTotalAmount(request.totalAmount());
        orderProduct.setOrderId(generateOrderId());
        return  orderProductDao.save(orderProduct);
    }

    private String generateOrderId(){
        Random random=new Random();//999998 +
        int number=random.nextInt(1,899999) + 100000;
        return new StringBuilder()
                .append("GMA-")
                .append(number)
                .toString();
    }






}
