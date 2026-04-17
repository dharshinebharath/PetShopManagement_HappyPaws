package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.dto.requestDto.AddressesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.AddressesResponseDTO;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.service.AddressesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressesController {
@Autowired
private AddressesService addressesService;
@PostMapping
public List<AddressesResponseDTO> saveaddresses(@Valid @RequestBody List<AddressesRequestDTO> addresses){
	return addressesService.saveaddresses(addresses);
}
@GetMapping
public List<AddressesResponseDTO> getaddresses(){
	return addressesService.getaddresses();
}
@GetMapping("/{id}")
public AddressesResponseDTO getaddressesbyId(@PathVariable long id){
	return addressesService.getaddressesByID(id);
}
@DeleteMapping("/{id}")
public String delete(@PathVariable long id) {
    addressesService.deleteaddress(id);
    return "Deleted successfully";
}
@PutMapping("/{id}")
public AddressesResponseDTO updateaddresses(@PathVariable long id,@Valid @RequestBody AddressesRequestDTO address) {
	return addressesService.updateaddress(id,address);
}



}
