package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.service.interfaces.AddressesInterface;

@Service
public class AddressesService implements AddressesInterface {
@Autowired
private AddressesRepository addressesRepository;
@Override
public List<Addresses> saveaddresses(List<Addresses> addresses){
	return addressesRepository.saveAll(addresses);
}
@Override
public List<Addresses> getaddresses(){
	return addressesRepository.findAll();
}
@Override
public Addresses getaddressesByID(long id){
	return addressesRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + id));
}
@Override
public void deleteaddress(long id) {
    if (!addressesRepository.existsById(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Address not found with id: " + id);
    }
    addressesRepository.deleteById(id);
}
@Override
public Addresses updateaddress(long id,Addresses updatedaddress) {
	Addresses existing=addressesRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found with id"));
	
	existing.setCity(updatedaddress.getCity());
	existing.setState(updatedaddress.getState());
	existing.setStreet(updatedaddress.getStreet());
	existing.setZipCode(updatedaddress.getZipCode());
	return addressesRepository.save(existing);
}}
