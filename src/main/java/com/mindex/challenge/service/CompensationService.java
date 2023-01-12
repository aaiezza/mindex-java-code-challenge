package com.mindex.challenge.service;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.EmployeeCompensationHistory;
import com.mindex.challenge.data.EmployeeCompensationHistory.CompensationData;

@Service
public class CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationService.class);

    @Autowired
    private CompensationRepository compensationRepository;

    public Compensation create(final Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        compensationRepository.insert(compensation);

        return compensation;
    }

    public EmployeeCompensationHistory read(final String employeeId) {
        LOG.debug("Fetching compensation for employee with id [{}]", employeeId);

        final List<Compensation> employeeCompensations = compensationRepository.findByEmployeeEmployeeId(employeeId);

        if (employeeCompensations == null || employeeCompensations.isEmpty()) {
            throw new RuntimeException("Unknown employeeId: " + employeeId);
        }

        final Employee employee = employeeCompensations.get(0).getEmployee();

        return employeeCompensations.stream()
                .map(comp -> new CompensationData(comp.getSalary(), comp.getEffectiveDate()))
                .collect(Collectors.collectingAndThen(toUnmodifiableList(),
                        compHistory -> new EmployeeCompensationHistory(employee, compHistory)));
    }
}
