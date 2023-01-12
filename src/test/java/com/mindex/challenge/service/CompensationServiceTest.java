package com.mindex.challenge.service;

import static com.mindex.challenge.utils.TestUtils.assertCompensationEquivalence;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceTest {

    private String employeeCompensationUrl;

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        employeeCompensationUrl = "http://localhost:" + port + "/employee/{id}/compensation";
    }

    @Test
    public void testCreateReadUpdate() {
        final Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("ad55e276-f148-4d37-afa2-6747295c91a4");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        final Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(250750);
        testCompensation.setEffectiveDate(LocalDateTime.parse("2023-02-01T00:00:00"));

        // Create checks
        final Compensation createdCompensation = restTemplate.postForEntity(employeeCompensationUrl, testCompensation,
                Compensation.class, testCompensation.getEmployee().getEmployeeId()).getBody();

        assertThat(createdCompensation.getEmployee().getEmployeeId()).isNotNull();
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // Read checks
        final Compensation readCompensation = restTemplate.getForEntity(employeeCompensationUrl, Compensation.class,
                createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertThat(readCompensation.getEmployee().getEmployeeId())
                .isEqualTo(createdCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }
}
