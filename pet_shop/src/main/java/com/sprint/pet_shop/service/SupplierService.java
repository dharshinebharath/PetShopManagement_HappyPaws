package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.repository.SupplierRepository;


@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

    // POST ALL
	public List<Supplier> supplierAll(List<Supplier> supplier) {
	    return supplierRepository.saveAll(supplier);
	}
	// GET ALL
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }
	
    // GET BY ID
    public Supplier getSupplierById(long supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplierId));
    }
    //DELETE BY ID
    public void deleteSupplier(long supplierId) {

        Supplier existing = supplierRepository.findById(supplierId)
            .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplierId));

        supplierRepository.delete(existing);
    }
//PUT BY ID 
//    public Supplier updateSupplier(long supplierId, Supplier newData) {
//
//        Supplier existing = supplierRepository.findById(supplierId)
//            .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplierId));
//
//        // update fields
//        existing.setName(newData.getName());
//        existing.setContactPerson(newData.getContactPerson());
//        existing.setPhoneNumber(newData.getPhoneNumber());
//        existing.setEmail(newData.getEmail());
//
//        return supplierRepository.save(existing);
//    }
    //PUT BY ID
    public Supplier updateSupplier(Long id, Supplier supplier) {

        Supplier existing = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));

        if (supplier.getName() != null)
            existing.setName(supplier.getName());

        if (supplier.getContactPerson() != null)
            existing.setContactPerson(supplier.getContactPerson());

        if (supplier.getPhoneNumber() != null)
            existing.setPhoneNumber(supplier.getPhoneNumber());

        if (supplier.getEmail() != null)
            existing.setEmail(supplier.getEmail());

        return supplierRepository.save(existing);
    }
}
