package com.monsatorm.demo.model.dbo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requesttype")
public class RequestTypeDbo {
    @Id
    @GeneratedValue
    private Integer requestTypeId;
    @Column(length = 300)
    private String description;
}
