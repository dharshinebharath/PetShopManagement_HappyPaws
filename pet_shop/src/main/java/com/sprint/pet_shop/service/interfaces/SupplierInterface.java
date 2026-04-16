package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.entity.Supplier;

public interface SupplierInterface {
	List<Supplier> supplierAll(List<Supplier> supplier);

    List<Supplier> getAll();

    Supplier getSupplierById(long supplierId);

    void deleteSupplier(long supplierId);

    Supplier updateSupplier(Long id, Supplier supplier);
}
