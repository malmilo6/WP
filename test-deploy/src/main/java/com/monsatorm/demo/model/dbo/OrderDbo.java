package com.monsatorm.demo.model.dbo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDbo {
    @Id
    @GeneratedValue
    @Column(name = "orderid")
    private Long orderId;
    @OneToOne
    @JoinColumn(name = "tableid")
    private TableDbo tableDbo;
    @CreationTimestamp
    @Column(name = "starttimestamp")
    private LocalDateTime startTimeStamp;
    @Column(name = "isopen")
    private Boolean isOpen;
}
