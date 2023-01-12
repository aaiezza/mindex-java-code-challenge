package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.EmployeeCompensationHistory;
import com.mindex.challenge.service.CompensationService;

@RestController
@lombok.RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    private final CompensationService compensationService;

    @PostMapping("/employee/{id}/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation.getEmployee());

        return compensationService.create(compensation);
    }

    @GetMapping("/employee/{id}/compensation")
    public EmployeeCompensationHistory read(@PathVariable String id) {
        LOG.debug("Received employee get request for id [{}]", id);

        return compensationService.read(id);
    }
}
