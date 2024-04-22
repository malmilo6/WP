package com.monsatorm.demo.repository;

import com.monsatorm.demo.model.dbo.RequestHistoryDbo;
import com.monsatorm.demo.model.dto.response.RequestHistoryDto;
import com.monsatorm.demo.model.projections.RequestHistoryDtoP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestHistoryRepository extends JpaRepository<RequestHistoryDbo, Long> {

    @Query(value = "select getrecentrequest()")
    List<RequestHistoryDtoP> getRecentRequests();
}
