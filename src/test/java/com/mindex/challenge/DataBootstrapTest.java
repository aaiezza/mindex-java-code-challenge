package com.mindex.challenge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DataBootstrapTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void test() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getLastName()).isEqualTo("Lennon");
        assertThat(employee.getPosition()).isEqualTo("Development Manager");
        assertThat(employee.getDepartment()).isEqualTo("Engineering");
    }
}