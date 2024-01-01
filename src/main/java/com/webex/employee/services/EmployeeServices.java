package com.webex.employee.services;

import com.webex.employee.model.Employee;

import java.util.List;

public interface EmployeeServices {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployee();

    boolean deleteEmployee(Long id);

    Employee getEmployeeId(Long id);

    Employee updateEmployee(Long id, Employee employee);
}
