package com.mindex.challenge.data;

import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Compensation {
    private Employee employee;
    private int salary;
    private LocalDateTime effectiveDate;
}
