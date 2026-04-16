package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.CustomersRepository;
import com.sprint.pet_shop.service.interfaces.CustomersInterface;

@Service
public class CustomersService implements CustomersInterface{
@Autowired
private CustomersRepository customersRepository;
@Override
public List<Customers> savecustomers(List<Customers> customers) {
    for (Customers c : customers) {
        if (c.getFirstName() == null || c.getFirstName().trim().isEmpty()) {
            throw new InvalidDataException("First name cannot be empty");
        }
        if (c.getLastName() == null || c.getLastName().trim().isEmpty()) {
            throw new InvalidDataException("Last name cannot be empty");
        }
       if (c.getEmail() == null || c.getEmail().trim().isEmpty()) {
            throw new InvalidDataException("Email cannot be empty");
        }

        if (c.getPhoneNumber() == null || c.getPhoneNumber().trim().isEmpty()) {
            throw new InvalidDataException("Phone number cannot be empty");
        }
        if (customersRepository.existsByEmail(c.getEmail())) {
            throw new DuplicateResourceException(
                    "Customer already exists with email: " + c.getEmail()
            );
        }
    }

    return customersRepository.saveAll(customers);
}
@Override
public List<Customers> getcustomers(){
	return customersRepository.getAll();
}
@Override
public void deletecustomer(long id) {
    if (!customersRepository.existsById(id)) {
        throw new ResourceNotFoundException( 
                "Address not found with id: " + id);
    }
    customersRepository.deleteById(id);
}
@Override
public Customers getcustomerByID(long id){
	return customersRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found with id: " + id));
}
@Override
public Customers updatecustomer(long id,Customers updatedcustomer) {
	Customers existing=customersRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found with id" +id));
	 if (updatedcustomer.getFirstName() == null || updatedcustomer.getFirstName().trim().isEmpty()) {
	        throw new InvalidDataException("First name cannot be empty");
	    }
	    if (updatedcustomer.getLastName() == null || updatedcustomer.getLastName().trim().isEmpty()) {
	        throw new InvalidDataException("Last name cannot be empty");
	    }
	    if (updatedcustomer.getEmail() == null || updatedcustomer.getEmail().trim().isEmpty()) {
	        throw new InvalidDataException("Email cannot be empty");
	    }
	    if (updatedcustomer.getPhoneNumber() == null || updatedcustomer.getPhoneNumber().trim().isEmpty()) {
	        throw new InvalidDataException("Phone number cannot be empty");
	    }
	    boolean emailExists = customersRepository.existsByEmail(updatedcustomer.getEmail());
	    if (emailExists && !existing.getEmail().equals(updatedcustomer.getEmail())) {
	        throw new DuplicateResourceException(
	                "Email already used by another customer: " + updatedcustomer.getEmail()
	        );
	    }
	existing.setFirstName(updatedcustomer.getFirstName());
	existing.setLastName(updatedcustomer.getLastName());
	existing.setEmail(updatedcustomer.getEmail());
	existing.setPhoneNumber(updatedcustomer.getPhoneNumber());
	return customersRepository.save(existing);
}
}
