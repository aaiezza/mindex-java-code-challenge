package com.mindex.challenge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;

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

    public Compensation read(final String employeeId) {
        LOG.debug("Fetching compensation for employee with id [{}]", employeeId);

        Compensation compensation = compensationRepository.findByEmployeeEmployeeId(employeeId);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        return compensation;
    }
}
