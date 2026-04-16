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

import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.service.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
// POST ALL
    @PostMapping
    public List<Supplier> savedAll(@Valid @RequestBody List<Supplier> supplier){ 
        return supplierService.supplierAll(supplier);
    }
 // GET ALL
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAll();
    }
 // GET BY ID
    @GetMapping("/{supplierId}")
    public Supplier getSupplierById(@PathVariable long supplierId) {
        return supplierService.getSupplierById(supplierId);
    }
    //DELETE BY ID
    @DeleteMapping("/{supplierId}")
    public String deleteSupplier(@PathVariable long supplierId) {
        supplierService.deleteSupplier(supplierId);
        return "Supplier deleted successfully with id: " + supplierId;
    }
    //PUT BY ID
    @PutMapping("/{supplierId}")
    public Supplier updateSupplier(@PathVariable long supplierId,
                                   @Valid @RequestBody Supplier supplier) {
        return supplierService.updateSupplier(supplierId, supplier);
    }

}
