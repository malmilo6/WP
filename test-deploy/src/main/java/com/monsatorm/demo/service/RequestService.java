package com.monsatorm.demo.service;

import com.monsatorm.demo.model.dbo.RequestHistoryDbo;
import com.monsatorm.demo.model.projections.RequestHistoryDtoP;
import com.monsatorm.demo.model.projections.RequestHistoryDtoPImpl;

import java.util.List;

public interface RequestService {
    void createRequest(String orderId, Integer requestTypeId);
    void closeRequest(Integer requestId);

    void broadcastRecentRequests();
    List<RequestHistoryDtoPImpl> getRecentRequests();
}
