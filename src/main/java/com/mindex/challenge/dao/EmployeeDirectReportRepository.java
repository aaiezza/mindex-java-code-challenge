package com.mindex.challenge.dao;

import java.util.List;

import com.mindex.challenge.data.Employee;

public interface EmployeeDirectReportRepository {
    List<Employee> findEmployeeAndDirectReports(String employeeId);
}
