package com.example.deliveryservice.dao;

import com.example.deliveryservice.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderProductDao extends JpaRepository<OrderProduct,Integer> {

    Optional<OrderProduct> findByOrderId(String orderId);
}
