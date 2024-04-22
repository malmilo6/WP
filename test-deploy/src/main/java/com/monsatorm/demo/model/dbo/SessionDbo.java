package com.monsatorm.demo.model.dbo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "session")
public class SessionDbo {
    @Id
    @GeneratedValue
    @Column(name = "sessionpk")
    private Integer sessionPk;
    @Column(name = "sessionid")
    private UUID sessionId;
    @OneToOne
    @JoinColumn(name = "tableid")
    private TableDbo tableDbo;
    @CreationTimestamp
    @Column(name = "starttime")
    private LocalDateTime startTime;
    @Column(name = "expirationtime")
    private LocalDateTime expirationTime;
}
