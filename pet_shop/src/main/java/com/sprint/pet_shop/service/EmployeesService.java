
package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.repository.EmployeesRepository;
import com.sprint.pet_shop.service.interfaces.EmployeesInterface;

@Service
public class EmployeesService implements EmployeesInterface {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Override
    public List<Employees> saveAll(List<Employees> employees) {
        return employeesRepository.saveAll(employees);
    }

    @Override
    public List<Employees> getAll() {
        return employeesRepository.findAll();
    }

    @Override
    public Employees getEmployeesById(long employeesId) {
        return employeesRepository.findById(employeesId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeesId));
    }

    @Override
    public void deleteEmployee(long employeeId) {
        Employees existing = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        employeesRepository.delete(existing);
    }

    @Override
    public Employees updateEmployee(Long id, Employees employee) {

        Employees existing = employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        if (employee.getFirstName() != null)
            existing.setFirstName(employee.getFirstName());

        if (employee.getLastName() != null)
            existing.setLastName(employee.getLastName());

        if (employee.getPosition() != null)
            existing.setPosition(employee.getPosition());

        if (employee.getHireDate() != null)
            existing.setHireDate(employee.getHireDate());

        if (employee.getPhoneNumber() != null)
            existing.setPhoneNumber(employee.getPhoneNumber());

        if (employee.getEmail() != null)
            existing.setEmail(employee.getEmail());

        return employeesRepository.save(existing);
    }
}