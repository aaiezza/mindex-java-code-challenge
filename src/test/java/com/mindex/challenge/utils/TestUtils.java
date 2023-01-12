package com.mindex.challenge.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

public class TestUtils {
    public static void assertEmployeeEquivalence(final Employee expected, final Employee actual) {
        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        assertThat(actual.getDepartment()).isEqualTo(expected.getDepartment());
        assertThat(actual.getPosition()).isEqualTo(expected.getPosition());
    }

    public static void assertCompensationEquivalence(final Compensation expected, final Compensation actual) {
        assertEmployeeEquivalence(actual.getEmployee(), expected.getEmployee());
        assertThat(actual.getSalary()).isEqualTo(expected.getSalary());
        assertThat(actual.getEffectiveDate()).isEqualTo(expected.getEffectiveDate());
    }
}
