package com.mindex.challenge.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mindex.challenge.data.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>, EmployeeDirectReportRepository {
    Employee findByEmployeeId(String employeeId);
}
