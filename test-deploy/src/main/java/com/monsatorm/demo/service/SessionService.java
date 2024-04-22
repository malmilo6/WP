package com.monsatorm.demo.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public interface SessionService {
    void updateSessionTimeOrAddSession(String sessionId, Integer tableId, String orderId, Timestamp expirationTime);
    boolean checkSessionExpiration(String sessionId);
}
