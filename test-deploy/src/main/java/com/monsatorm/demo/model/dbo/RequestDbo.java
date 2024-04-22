package com.monsatorm.demo.model.dbo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")
public class RequestDbo {
    @Id
    @GeneratedValue
    @Column(name = "requestid")
    private Long requestId;
    @OneToOne
    @JoinColumn(name = "orderid")
    private OrderDbo orderDbo;
    @OneToOne
    @JoinColumn(name = "requesttypeid")
    private RequestTypeDbo requestTypeDbo;
    @CreationTimestamp
    @Column(name = "requesttimestamp")
    private LocalDateTime requestTimeStamp;
    @Column(name = "isopen")
    private Boolean isOpen;
}
