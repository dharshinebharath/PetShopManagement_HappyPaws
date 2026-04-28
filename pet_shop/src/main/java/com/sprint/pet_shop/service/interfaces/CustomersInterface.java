package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.CustomerRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.CustomerResponseDTO;


/**
 * Defines the business rules for managing Customers.
 * It outlines what operations we support for customers, including creating accounts, 
 * updating profiles, and finding customers who haven't made a purchase yet.
 */
public interface CustomersInterface {
	ApiResponse<List<CustomerResponseDTO>> savecustomers(List<CustomerRequestDTO> customers);

    ApiResponse<List<CustomerResponseDTO>> getcustomers();

    ApiResponse<CustomerResponseDTO> getcustomerByID(long id);

    ApiResponse<CustomerResponseDTO> updatecustomer(long id, CustomerRequestDTO updatedcustomer);

    ApiResponse<String> deletecustomer(long id);
    
    ApiResponse<List<CustomerResponseDTO>> getCustomersWithNoTransactions();
}
