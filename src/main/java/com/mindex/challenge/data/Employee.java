package com.mindex.challenge.data;

import java.util.List;

@lombok.Data
@lombok.NoArgsConstructor
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;
}
