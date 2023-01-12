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
        final Employee employee = employeeRepository.findEmployeeWithHydratedDirectReports(employeeId);

        final ReportingStructure reportingStructure = new ReportingStructure(employee,
                getNumberOfDirectReports(employee));

        LOG.debug("Found that employee id [{}] has {} direct reports", employeeId,
                reportingStructure.getNumberOfReports());

        return reportingStructure;
    }

    private static int getNumberOfDirectReports(final Employee rootEmployee) {
        final AtomicInteger numberOfReports = new AtomicInteger();
        final Deque<Employee> employeesToTraverse = new ArrayDeque<>();

        rootEmployee.getDirectReports().stream().forEach(employeesToTraverse::add);

        while (!employeesToTraverse.isEmpty()) {
            final Employee subordinate = employeesToTraverse.pop();
            numberOfReports.incrementAndGet();

            if (subordinate.getDirectReports() != null) {
                subordinate.getDirectReports().stream().forEach(employeesToTraverse::add);
            }
        }
        
        return numberOfReports.get();
    }
}
