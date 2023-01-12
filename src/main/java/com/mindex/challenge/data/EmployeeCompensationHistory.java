package com.mindex.challenge.data;

import java.time.LocalDateTime;
import java.util.List;

@lombok.Value
public class EmployeeCompensationHistory {
    private final Employee employee;
    private final List<CompensationData> compensationHistory;

    @lombok.Value
    public static class CompensationData {
        private final int salary;
        private final LocalDateTime effectiveDate;
    }
}
