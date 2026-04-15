package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.service.CustomersService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {
@Autowired
public CustomersService customersService;
@PostMapping
public List<Customers> savecustomers(@RequestBody List<Customers> customers) {
	return customersService.savecustomers(customers);
}
@GetMapping
public List<Customers> getcustomers(){
	return customersService.getcustomers();
}
@GetMapping("/{id}")
public Customers getcustomerbyId(@PathVariable Long id){
	return customersService.getcustomerByID(id);
}
}
