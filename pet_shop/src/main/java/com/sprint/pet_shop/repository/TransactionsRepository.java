package com.sprint.pet_shop.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.TransactionsEntity;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity,Long>{
	@Query("SELECT t FROM TransactionsEntity t WHERE t.transactionDate BETWEEN :start AND :end")
    List<TransactionsEntity> findByDateRange(@Param("start") Date start,
                                            @Param("end") Date end);

}
