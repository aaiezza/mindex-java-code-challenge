package com.mindex.challenge.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

@Service
public class GetReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(GetReportingStructureService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public ReportingStructure execute(final String employeeId) {
        Employee rootEmployee = employeeRepository.findByEmployeeId(employeeId);

        if (rootEmployee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        final AtomicInteger numberOfReports = new AtomicInteger();
        final Deque<String> employeeIdsToTraverse = new ArrayDeque<>();

        rootEmployee.getDirectReports().stream().map(Employee::getEmployeeId).forEach(employeeIdsToTraverse::add);

        while (!employeeIdsToTraverse.isEmpty()) {
            final String childEmployeeId = employeeIdsToTraverse.pop();
            numberOfReports.incrementAndGet();

            final Employee employee = employeeRepository.findByEmployeeId(childEmployeeId);
            if (employee.getDirectReports() != null) {
                employee.getDirectReports().stream().map(Employee::getEmployeeId).forEach(employeeIdsToTraverse::add);
            }
        }

        LOG.debug("Found that employee id [{}] has {} direct reports", employeeId, numberOfReports.get());

        return new ReportingStructure(rootEmployee, numberOfReports.get());
    }
}
