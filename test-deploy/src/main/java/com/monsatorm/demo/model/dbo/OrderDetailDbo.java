package com.monsatorm.demo.model.dbo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderdetails")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDbo {
    @Id
    @GeneratedValue
    @Column(name = "orderdetailsid")
    private Long orderDetailsId;
    @Column(name = "quantity")
    private Integer quantity;
    @OneToOne
    @JoinColumn(name = "productid")
    private ProductDbo productDbo;
//    @OneToOne
//    @JoinColumn(name = "orderid")
//    private OrderDbo orderDbo;
}
