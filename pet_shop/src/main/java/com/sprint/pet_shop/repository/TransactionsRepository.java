package com.sprint.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.pet_shop.entity.TransactionsEntity;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity,Long>{

}
