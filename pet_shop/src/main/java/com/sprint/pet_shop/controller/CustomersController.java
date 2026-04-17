package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.service.CustomersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {
@Autowired
public CustomersService customersService;
@PostMapping
public List<Customers> savecustomers(@Valid @RequestBody List<Customers> customers) {
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
@DeleteMapping("/{id}")
public String deletecustomer(@PathVariable long id) {
	customersService.deletecustomer(id);
	return "deleted Successfully";
}
@PutMapping("/{id}")
public Customers updatecustomers(@PathVariable long id,@Valid @RequestBody Customers customer) {
	return customersService.updatecustomer(id,customer);
}
}
