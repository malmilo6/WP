package com.monsatorm.demo.model.projections;

import java.sql.Timestamp;

public interface RequestHistoryDtoP {
     String getSessionId();
     Timestamp getRequestTimestamp();
     String getDescription();
     Integer getTableId();
     Boolean getRequestStatus();
}
