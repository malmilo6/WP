package com.monsatorm.demo.repository;

import com.monsatorm.demo.model.dbo.SessionDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<SessionDbo, UUID> {
//    @Modifying
//    @Transactional
//    @Query("SELECT addupdatesession(:sessionId, :tableId, :orderId, :expirationTime)")
//    @Procedure(name = "addupdatesession")
//    void addupdatesession(
//            @Param("sessionId") UUID sessionId,
//            @Param("tableId") Integer tableId,
//            @Param("orderId") UUID orderId,
//            @Param("expirationTime") Timestamp expirationTime
//    );

    @Transactional
    @Query(value = "select test(:sessionId, :tableId, :orderId, :expirationTime)", nativeQuery = true)
    void addUpdateSession(
            @Param("sessionId") String sessionId,
            @Param("tableId") Integer tableId,
            @Param("orderId") String orderId,
            @Param("expirationTime") Timestamp expirationTime
    );

    @Transactional
    @Query(value = "select checksessionexpiration(cast(:sessionId AS varchar))", nativeQuery = true)
    boolean checkSessionExpiration(
            @Param("sessionId") String sessionId
    );
}