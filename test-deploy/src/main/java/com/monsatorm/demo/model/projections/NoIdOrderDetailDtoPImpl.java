package com.monsatorm.demo.model.projections;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoIdOrderDetailDtoPImpl {
    private String key;
    private String productName;
    private Integer quantity;
    private Double productPrice;
}
