package com.monsatorm.demo.model.projections;

public interface OrderDetailDtoP {
    String getOrderId();
    String getProductName();

    Integer getQuantity();

    Double getProductPrice();
}
