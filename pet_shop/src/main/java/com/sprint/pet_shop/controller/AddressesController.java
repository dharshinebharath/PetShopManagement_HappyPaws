package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.service.AddressesService;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressesController {
@Autowired
private AddressesService addressesService;
@PostMapping
public List<Addresses> saveaddresses(@RequestBody List<Addresses> addresses){
	return addressesService.saveaddresses(addresses);
}
@GetMapping
public List<Addresses> getaddresses(){
	return addressesService.getaddresses();
}
@GetMapping("/{id}")
public Addresses getaddressesbyId(@PathVariable Long id){
	return addressesService.getaddressesByID(id);
}

}
