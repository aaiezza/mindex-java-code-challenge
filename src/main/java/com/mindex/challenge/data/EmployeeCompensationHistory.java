package com.mindex.challenge.data;

import java.time.LocalDateTime;
import java.util.List;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EmployeeCompensationHistory {
    private Employee employee;
    private List<CompensationData> compensationHistory;

    @lombok.Data
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class CompensationData {
        private int salary;
        private LocalDateTime effectiveDate;
    }
}
