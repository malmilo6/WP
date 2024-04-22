package com.monsatorm.demo.model.dbo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductDbo {
    @Id
    @GeneratedValue
    private Long productId;
    @Column(name = "categoryname", length = 100)
    private String categoryName;
    @Column(name = "productname", length = 100)
    private String productName;
    @Column(name = "productsku", length = 100)
    private String productSku;
    @Column(name = "productprice")
    private Integer productPrice;
    @Column(name = "productvalpercent")
    private Integer productValPercent;
    @Column(name = "producttype", length = 150)
    private String productType;
    @Column(name = "productunit")
    private Integer productUnit;
    @Column(name = "productmu", length = 100)
    private String productMu;
    @Column(name = "producttags", length = 150)
    private String productTags;
    @Column(name = "productimagename", length = 300)
    private String productImageName;
    @Column(name = "productunitquantitydescription", length = 100)
    private String productUnitQuantityDescription;
    @Column(name = "productshortdescription", length = 200)
    private String productShortDescription;
    @Column(name = "productdescription", length = 2000)
    private String productDescription;
}
