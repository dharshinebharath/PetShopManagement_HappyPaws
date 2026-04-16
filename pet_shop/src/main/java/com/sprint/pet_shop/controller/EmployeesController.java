//package com.sprint.pet_shop.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sprint.pet_shop.entity.Employees;
//import com.sprint.pet_shop.entity.Supplier;
//import com.sprint.pet_shop.service.EmployeesService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/v1/employees")
//public class EmployeesController {
//	@Autowired
//	private EmployeesService employeesService;
//	//POST ALL
//	@PostMapping
//	public List<Employees> saveAll(@Valid @RequestBody List<Employees> employees) {
//	    return employeesService.saveAll(employees);
//	}
//	 // GET ALL
//    @GetMapping
//    public List<Employees> getAllEmployees() {
//        return employeesService.getAll();
//    }
//
// // GET BY ID
//    @GetMapping("/{employeesId}")
//    public Employees getEmployeesById(@PathVariable long employeesId) {
//        return employeesService.getEmployeesById(employeesId);
//    }
//    
//    //DELETE BY ID
//  @DeleteMapping("/{employeeId}")
//    public String deleteEmployee(@PathVariable long employeeId) {
//        employeesService.deleteEmployee(employeeId);
//        return "Employee deleted successfully with id: " + employeeId;
//    }
//  // PUT BY ID
//  @PutMapping("/{employeesId}")
//  public Employees updateEmployee(@PathVariable long employeesId,
//                                  @Valid @RequestBody Employees employee) {
//      return employeesService.updateEmployee(employeesId, employee);
//  }
//}
package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.service.EmployeesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    // POST ALL
    @PostMapping
    public List<Employees> saveAll(@Valid @RequestBody List<Employees> employees) {
        return employeesService.saveAll(employees);
    }

    // GET ALL
    @GetMapping
    public List<Employees> getAllEmployees() {
        return employeesService.getAll();
    }

    // GET BY ID
    @GetMapping("/{employeesId}")
    public Employees getEmployeesById(@PathVariable long employeesId) {
        return employeesService.getEmployeesById(employeesId);
    }

    // DELETE BY ID
    @DeleteMapping("/{employeesId}")
    public String deleteEmployee(@PathVariable long employeesId) {
        employeesService.deleteEmployee(employeesId);
        return "Employee deleted successfully with id: " + employeesId;
    }

    // PUT BY ID
    @PutMapping("/{employeesId}")
    public Employees updateEmployee(@PathVariable long employeesId,
                                    @Valid @RequestBody Employees employee) {
        return employeesService.updateEmployee(employeesId, employee);
    }
}
