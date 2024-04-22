package com.monsatorm.demo.service.impl;

import com.monsatorm.demo.model.projections.RequestHistoryDtoPImpl;
import com.monsatorm.demo.repository.RequestHistoryRepository;
import com.monsatorm.demo.repository.RequestRepository;
import com.monsatorm.demo.service.RequestService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final RequestHistoryRepository requestHistoryRepository;
    private final EntityManager entityManager;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void createRequest(String orderId, Integer requestTypeId) {
        requestRepository.createRequest(orderId, requestTypeId);
    }

    @Override
    public void closeRequest(Integer requestId) {
        requestRepository.closeRequest(requestId);
    }

    @Override
    public List<RequestHistoryDtoPImpl> getRecentRequests() {
//        return requestHistoryRepository.getRecentRequests().stream()
//                .map(requestHistoryDtoP -> RequestHistoryDtoPImpl.builder()
//                        .sessionId(requestHistoryDtoP.getSessionId())
//                        .description(requestHistoryDtoP.getDescription())
//                        .tableId(requestHistoryDtoP.getTableId())
//                        .requestTimeStamp(requestHistoryDtoP.getRequestTimestamp())
//                        .requestStatus(requestHistoryDtoP.getRequestStatus())
//                        .build()
//                ).toList();
//    }
        List<Object[]> results = entityManager.createNativeQuery("SELECT * FROM getrecentrequest()").getResultList();
        return results.stream().map(result -> new RequestHistoryDtoPImpl(
                (Integer) result[0], // req id
                (String) result[1], // sessionid
                (Timestamp) result[2], // requesttimestamp
                (String) result[3], // description
                (Integer) result[4], // tableid
                (Boolean) result[5] // requeststatus
        )).collect(Collectors.toList());
    }

    @Override
    public void broadcastRecentRequests() {
        List<RequestHistoryDtoPImpl> recentRequests = this.getRecentRequests(); // Fetch recent requests
        messagingTemplate.convertAndSend("/topic/recent-requests", recentRequests); // Broadcast to subscribers
    }

}
