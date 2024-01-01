package com.webex.employee.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.webex.employee.entity.EmployeeEntity;
import com.webex.employee.model.Employee;
import com.webex.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServicesImpl implements EmployeeServices {

    private final EmployeeRepository employeeRepository;

    public EmployeeServicesImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();

        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        System.out.println(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee>employees
                = employeeEntities.stream().map(emp->new Employee(emp.getId(),
                emp.getFirstname(),
                emp.getLastname(),
                emp.getEmailId())).collect(Collectors.toList()
        );
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public Employee getEmployeeId(Long id) {
        EmployeeEntity employeeEntity
                = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity
                = employeeRepository.findById(id).get();
        employeeEntity.setEmailId(employee.getEmailId());
        employeeEntity.setFirstname(employee.getFirstname());
        employeeEntity.setLastname(employee.getLastname());

        employeeRepository.save(employeeEntity);
        return employee;
    }

}
