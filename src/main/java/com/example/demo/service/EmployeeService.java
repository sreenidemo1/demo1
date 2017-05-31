package com.example.demo.service;

import com.example.demo.data.domain.Address;
import com.example.demo.data.domain.Employee;
import com.example.demo.data.repository.AddressRepository;
import com.example.demo.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sreenivasulu on 5/20/2017.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public Employee getEmployee(Long id) {
        Employee employee = employeeRepository.findOne(id);
        employee.getAddresses();
        return employee;
    }

    @Transactional
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        employees.stream().forEach(employee -> employee.getAddresses());
        return employees;
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        for(Address address:employee.getAddresses()){
            addressRepository.save(address);
        }
        return employeeRepository.save(employee);
    }


    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.delete(id);
    }
}
