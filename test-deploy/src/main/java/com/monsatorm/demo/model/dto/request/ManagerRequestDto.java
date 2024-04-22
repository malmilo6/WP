package com.monsatorm.demo.model.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ManagerRequestDto {
    private String action;
    private LocalDateTime dateTime;
}
