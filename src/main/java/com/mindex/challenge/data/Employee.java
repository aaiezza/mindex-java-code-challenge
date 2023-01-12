package com.mindex.challenge.data;

import java.util.List;
import java.util.UUID;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import com.fasterxml.jackson.annotation.JsonValue;

public class Employee {
    @BsonRepresentation(BsonType.OBJECT_ID)
    @BsonId
    private Employee.Id employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    @lombok.EqualsAndHashCode
    public static class Id {
        @JsonValue
        private final String value;

        public Id(final String value) {
            this(UUID.fromString(value));
        }

        public Id(final UUID value) {
            this.value = value.toString();
        }

        public static Employee.Id random() {
            return new Employee.Id(UUID.randomUUID());
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public Employee() {
    }

    public Employee.Id getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee.Id employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }
}
