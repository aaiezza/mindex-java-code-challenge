package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.GetReportingStructureService;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private GetReportingStructureService getReportingStructureService;

    @GetMapping("/reporting-structure/{id}")
    public ReportingStructure read(@PathVariable Employee.Id id) {
        LOG.debug("Received reporting structure get request for employee id [{}]", id);

        return getReportingStructureService.execute(id);
    }
}
