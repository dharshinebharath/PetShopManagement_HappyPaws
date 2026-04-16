package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.entity.Customers;

import jakarta.validation.Valid;

public interface CustomersInterface {
	List<Customers> savecustomers(@Valid List<Customers> customers);
	List<Customers> getcustomers();
	void deletecustomer(long id);
	Customers getcustomerByID(long id);
	Customers updatecustomer(long id,@Valid Customers updatedcustomer);
}
