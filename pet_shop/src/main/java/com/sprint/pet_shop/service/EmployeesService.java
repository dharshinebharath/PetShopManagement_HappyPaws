package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.repository.EmployeesRepository;

@Service
public class EmployeesService {
    @Autowired
    private EmployeesRepository employeesRepository;

    public List<Employees> saveAll(List<Employees> employees) {
        return employeesRepository.saveAll(employees); 
    }
 // GET ALL
    public List<Employees> getAll() {
        return employeesRepository.findAll();
    }
 // GET BY ID
    public Employees getEmployeesById(long employeesId) {
        return employeesRepository.findById(employeesId)
                .orElseThrow(() -> new RuntimeException("Employees not found with id: " + employeesId));
    }
    //DELETE BY ID
    public void deleteEmployee(long employeeId) {

        Employees existing = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        employeesRepository.delete(existing);
    }
    
  
//    public Employees updateEmployee(long id, Employees employee) {
//
//        Employees existing = employeesRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
//
//        // update normal fields
//        existing.setFirstName(employee.getFirstName());
//        existing.setLastName(employee.getLastName());
//        existing.setPosition(employee.getPosition());
//        existing.setHireDate(employee.getHireDate());
//        existing.setPhoneNumber(employee.getPhoneNumber());
//        existing.setEmail(employee.getEmail());
//
//        return employeesRepository.save(existing);
//    }
//	
    //PUT BY ID
    public Employees updateEmployee(Long id, Employees employee) {

        Employees existing = employeesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        if (employee.getFirstName() != null) {
            existing.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            existing.setLastName(employee.getLastName());
        }
        if (employee.getPosition() != null) {
            existing.setPosition(employee.getPosition());
        }
        if (employee.getHireDate() != null) {
            existing.setHireDate(employee.getHireDate());
        }
        if (employee.getPhoneNumber() != null) {
            existing.setPhoneNumber(employee.getPhoneNumber());
        }
        if (employee.getEmail() != null) {
            existing.setEmail(employee.getEmail());
        }

        return employeesRepository.save(existing);
    }
}
 