package com.mindex.challenge.dao;

import com.mindex.challenge.data.Employee;

public interface EmployeeDirectReportRepository {
    Employee findEmployeeWithHydratedDirectReports(String employeeId);
}
