package com.example.demo.data.repository;

import com.example.demo.data.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sreenivasulu on 5/20/2017.
 */

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAll();
}
