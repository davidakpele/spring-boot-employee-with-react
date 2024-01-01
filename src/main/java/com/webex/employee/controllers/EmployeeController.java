package com.webex.employee.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webex.employee.model.Employee;
import com.webex.employee.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeServices.createEmployee(employee);
    }

    @GetMapping("/list")
    public List<Employee> getAllEmployee(){
        return employeeServices.getAllEmployee();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        boolean delete = false;
        delete= employeeServices.deleteEmployee(id);
        Map<String, Boolean> response=new HashMap<>();
        response.put("Delete", delete);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable Long id){
        Employee employee= null;
        employee = employeeServices.getEmployeeId(id);

        return  ResponseEntity.ok(employee);
    }

    @PutMapping("/employee/edit/{id}")
    public ResponseEntity<Employee> upEmployeeDetails(@PathVariable Long id, @RequestBody Employee employee){

        employee = employeeServices.updateEmployee(id, employee);

        return  ResponseEntity.ok(employee);
    }
}
