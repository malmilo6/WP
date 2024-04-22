package com.monsatorm.demo.service.impl;

import com.monsatorm.demo.model.projections.NoIdOrderDetailDtoPImpl;
import com.monsatorm.demo.model.projections.OrderDetailDtoPImpl;
import com.monsatorm.demo.repository.OrderDetailRepository;
import com.monsatorm.demo.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailDtoPImpl> getOrderDetailByTableId(Integer tableId, Optional<String > orderId) {
        return orderDetailRepository.getOrderDetailByTableId(tableId, orderId).stream()
                .map(orderDetailDtoP -> OrderDetailDtoPImpl.builder()
                        .orderid(orderDetailDtoP.getOrderId())
                        .productPrice(orderDetailDtoP.getProductPrice())
                        .productName(orderDetailDtoP.getProductName())
                        .quantity(orderDetailDtoP.getQuantity())
                        .build())
                .toList();
    }

    @Override
    public List<NoIdOrderDetailDtoPImpl> getOrderDetailByOnlyTableId(Integer tableId) {
        return orderDetailRepository.getOrderDetailOnlyByTableId(tableId).stream()
                .map(noOrderIdDetailDtoP -> NoIdOrderDetailDtoPImpl.builder()
                    .key(UUID.randomUUID().toString())
                    .productPrice(noOrderIdDetailDtoP.getProductPrice())
                    .productName(noOrderIdDetailDtoP.getProductName())
                    .quantity(noOrderIdDetailDtoP.getQuantity())
                    .build())
                .toList();
    }


}
