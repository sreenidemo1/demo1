package com.example.demo.web.controller;

import com.example.demo.data.domain.Address;
import com.example.demo.data.domain.Employee;
import com.example.demo.data.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sreenivasulu on 5/20/2017.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/{id}")
    @ResponseBody
    public Employee getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

    @GetMapping()
    @ResponseBody
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    @ResponseBody
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id})")
    @ResponseBody
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
