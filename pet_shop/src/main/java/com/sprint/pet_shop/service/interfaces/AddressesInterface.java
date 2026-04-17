package com.sprint.pet_shop.service.interfaces;
import java.util.List;
import java.util.Optional;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.GroomingServices;

import jakarta.validation.Valid;

public interface AddressesInterface {
	List<Addresses> saveaddresses(@Valid List<Addresses> addresses);
	List<Addresses> getaddresses();
	Addresses getaddressesByID(long id);
	void deleteaddress(long id);
	Addresses updateaddress(long id,@Valid Addresses updatedaddress);
	
	
}
