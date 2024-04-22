package com.monsatorm.demo.model.projections;

import lombok.*;

import javax.annotation.processing.SupportedOptions;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDtoPImpl {
    String orderid;
    private String productName;
    private Integer quantity;
    private Double productPrice;
}
