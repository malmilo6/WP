package com.monsatorm.demo.model.dbo;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@With
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    @ElementCollection
    private Map<String, Integer> OrderDetails;
    private Float totalAmount;
}
