package com.monsatorm.demo.service;

import com.monsatorm.demo.model.projections.NoIdOrderDetailDtoPImpl;
import com.monsatorm.demo.model.projections.OrderDetailDtoPImpl;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetailDtoPImpl> getOrderDetailByTableId(Integer tableId, Optional<String> orderId);
//    List<OrderDetailDtoPImpl> getOrderDetailByOnlyTableId(String orderId);

    List<NoIdOrderDetailDtoPImpl> getOrderDetailByOnlyTableId(Integer tableId);
}
