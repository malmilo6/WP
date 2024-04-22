package com.monsatorm.demo.model.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHistoryDbo {
    @Id
    @GeneratedValue
    private String sessionId;
    private Timestamp requestTimestamp;
    private String description;
    private Integer tableId;
    private Boolean requestStatus;
}
