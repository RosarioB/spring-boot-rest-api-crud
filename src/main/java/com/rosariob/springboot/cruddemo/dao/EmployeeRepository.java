package com.rosariob.springboot.cruddemo.dao;

import com.rosariob.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {}
