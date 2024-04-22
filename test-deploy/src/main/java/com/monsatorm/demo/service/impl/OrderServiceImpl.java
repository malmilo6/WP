package com.monsatorm.demo.service.impl;

import com.monsatorm.demo.repository.OrderRepository;
import com.monsatorm.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void closeOrder(String orderId) {
        orderRepository.closeOrder(orderId);
    }
}
