package com.monsatorm.demo.model.projections;

import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHistoryDtoPImpl {
    private Integer requestId;
    private String sessionId;
    private Timestamp requestTimeStamp;
    private String description;
    private Integer tableId;
    private Boolean requestStatus;
}
