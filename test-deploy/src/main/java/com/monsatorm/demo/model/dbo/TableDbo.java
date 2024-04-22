package com.monsatorm.demo.model.dbo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tables")
public class TableDbo {
    @Id
    @GeneratedValue
    private Long tableId;
    @Column(name = "tableuid", length = 150)
    private String tableUid;

}
