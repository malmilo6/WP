package com.monsatorm.demo.model.dto.request;

import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientRequestDto {
    private Integer requestTypeId;
    private String orderId;
}
