
package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.repository.SupplierRepository;
import com.sprint.pet_shop.service.interfaces.SupplierInterface;

@Service
public class SupplierService implements SupplierInterface {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> supplierAll(List<Supplier> supplier) {
        return supplierRepository.saveAll(supplier);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(long supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplierId));
    }

    @Override
    public void deleteSupplier(long supplierId) {
        Supplier existing = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplierId));

        supplierRepository.delete(existing);
    }

    @Override
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
