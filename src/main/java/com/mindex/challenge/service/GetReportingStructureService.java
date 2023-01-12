package com.mindex.challenge.service;

import java.util.List;

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
        final List<Employee> directReports = employeeRepository.findEmployeeAndDirectReports(employeeId);

        final ReportingStructure reportingStructure = new ReportingStructure(
                directReports.stream().filter(e -> e.getEmployeeId().equals(employeeId)).findFirst().get(),
                directReports.size() - 1);

        LOG.debug("Found that employee id [{}] has {} direct reports", employeeId,
                reportingStructure.getNumberOfReports());

        return reportingStructure;
    }
}
