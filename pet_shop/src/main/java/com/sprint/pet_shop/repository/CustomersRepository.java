package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.pet_shop.entity.Customers;

//  Database repository for Customer records.
//  Acts as our data access layer to retrieve or update customer details easily.
public interface CustomersRepository extends JpaRepository<Customers,Long> {
	
	//  Checks if a customer already exists using their email address.
    boolean existsByEmail(String email);

    
    @Query("SELECT c FROM Customers c")
    List<Customers> getAll();

    //  Finds customers who haven't made any purchases or transactions yet.
    //  Useful for marketing or follow-up emails!
    @Query("SELECT c FROM Customers c WHERE c.transactions IS EMPTY")

    // 
    List<Customers> findCustomersWithNoTransactions();
    @Query("SELECT c FROM Customers c ORDER BY c.customerId ASC")
    List<Customers> findAllSorted();
}
