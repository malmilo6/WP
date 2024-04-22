package com.monsatorm.demo.model.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHistoryDto {
    private String sessionId;
    private Timestamp requestTimestamp;
    private String description;
    private Integer tableId;
    private Boolean requestStatus;
}
