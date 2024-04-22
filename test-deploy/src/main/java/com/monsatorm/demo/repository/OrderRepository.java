package com.monsatorm.demo.repository;

import com.monsatorm.demo.model.dbo.OrderDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDbo, Long> {
    @Query("select closeorder(:orderId)")
    void closeOrder(@Param("orderId") String orderId);
}
