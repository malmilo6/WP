package com.monsatorm.demo.service.impl;

import com.monsatorm.demo.repository.SessionRepository;
import com.monsatorm.demo.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    @Override
    public void updateSessionTimeOrAddSession(String sessionId, Integer tableId, String orderId, Timestamp expirationTime) {
        sessionRepository.addUpdateSession(sessionId, tableId, orderId, expirationTime);
    }

    @Override
    public boolean checkSessionExpiration(String sessionId) {
        return sessionRepository.checkSessionExpiration(sessionId);
    }

}
