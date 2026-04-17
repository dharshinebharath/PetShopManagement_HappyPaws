package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.service.interfaces.AddressesInterface;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.exception.InvalidDataException;

@Service
public class AddressesService implements AddressesInterface {
@Autowired
private AddressesRepository addressesRepository;
@Override
public List<Addresses> saveaddresses(List<Addresses> addresses){
	for (Addresses addr : addresses) {
        if (addr.getStreet() == null || addr.getStreet().trim().isEmpty()) {
            throw new InvalidDataException("Street cannot be empty");
        }
        if (addr.getCity() == null || addr.getCity().trim().isEmpty()) {
            throw new InvalidDataException("City cannot be empty");
        }
        if (addr.getState() == null || addr.getState().trim().isEmpty()) {
            throw new InvalidDataException("State cannot be empty");
        }
        if (addr.getZipCode() == null || addr.getZipCode().trim().isEmpty()) {
            throw new InvalidDataException("Zip code cannot be empty");
        }}
	return addressesRepository.saveAll(addresses);
}
@Override
public List<Addresses> getaddresses(){
	return addressesRepository.findAll();
}
@Override
public Addresses getaddressesByID(long id){
	return addressesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found with id: " + id));
}
@Override
public void deleteaddress(long id) {
    if (!addressesRepository.existsById(id)) {
        throw new ResourceNotFoundException("Address not found with id: " + id);
    }
    addressesRepository.deleteById(id);
}
@Override
public Addresses updateaddress(long id,Addresses updatedaddress) {
	Addresses existing=addressesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Address not found with id"));
	if (updatedaddress.getStreet() == null || updatedaddress.getStreet().trim().isEmpty()) {
        throw new InvalidDataException("Street cannot be empty");
    }
    if (updatedaddress.getCity() == null || updatedaddress.getCity().trim().isEmpty()) {
        throw new InvalidDataException("City cannot be empty");
    }
    if (updatedaddress.getState() == null || updatedaddress.getState().trim().isEmpty()) {
        throw new InvalidDataException("State cannot be empty");
    }
    if (updatedaddress.getZipCode() == null || updatedaddress.getZipCode().trim().isEmpty()) {
        throw new InvalidDataException("Zip code cannot be empty");
    }
	existing.setCity(updatedaddress.getCity());
	existing.setState(updatedaddress.getState());
	existing.setStreet(updatedaddress.getStreet());
	existing.setZipCode(updatedaddress.getZipCode());
	return addressesRepository.save(existing);
}}
