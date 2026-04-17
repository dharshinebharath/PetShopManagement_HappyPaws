package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.entity.Employees;

public interface EmployeesInterface {
	  List<Employees> saveAll(List<Employees> employees);

	    List<Employees> getAll();

	    Employees getEmployeesById(long employeesId);

	    void deleteEmployee(long employeeId);

	    Employees updateEmployee(Long id, Employees employee);
}
