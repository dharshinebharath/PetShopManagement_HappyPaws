package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.repository.CustomersRepository;

@Service
public class CustomersService {
@Autowired
private CustomersRepository customersRepository;
public List<Customers> savecustomers(List<Customers> customers){
	 return customersRepository.saveAll(customers);
}
public List<Customers> getcustomers(){
	return customersRepository.getAll();
}
public void deletecustomer(long id) {
    if (!customersRepository.existsById(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Address not found with id: " + id);
    }
    customersRepository.deleteById(id);
}

public Customers getcustomerByID(long id){
	return customersRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + id));
}
public Customers updatecustomer(long id,Customers updatedcustomer) {
	Customers existing=customersRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id" +id));
	existing.setFirstName(updatedcustomer.getFirstName());
	existing.setLastName(updatedcustomer.getLastName());
	existing.setEmail(updatedcustomer.getEmail());
	existing.setPhoneNumber(updatedcustomer.getPhoneNumber());
	return customersRepository.save(existing);
}

}
