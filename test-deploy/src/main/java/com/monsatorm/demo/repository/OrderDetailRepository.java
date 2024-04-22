package com.monsatorm.demo.repository;

import com.monsatorm.demo.model.dbo.OrderDetailDbo;
import com.monsatorm.demo.model.projections.OrderDetailDtoP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailDbo, Long> {
    @Query(value = "SELECT * FROM getorderdetails(:tableId, :orderId)",
            nativeQuery = true)
    List<OrderDetailDtoP> getOrderDetailByTableId(@Param("tableId") Integer tableId, @Param("orderId") Optional<String > orderId);

    @Query(value = "SELECT * FROM getorderdetails(:tableId)",
            nativeQuery = true)
    List<OrderDetailDtoP> getOrderDetailOnlyByTableId(@Param("tableId") Integer tableId);
}