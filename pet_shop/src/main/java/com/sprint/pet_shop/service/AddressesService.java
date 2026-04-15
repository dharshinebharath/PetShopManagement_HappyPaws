package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.repository.AddressesRepository;

@Service
public class AddressesService {
@Autowired
private AddressesRepository addressesRepository;
public List<Addresses> saveaddresses(List<Addresses> addresses){
	return addressesRepository.saveAll(addresses);
}
public List<Addresses> getaddresses(){
	return addressesRepository.findAll();
}
public Addresses getaddressesByID(long id){
	return addressesRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + id));
}
}
