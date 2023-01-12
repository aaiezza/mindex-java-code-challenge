package com.mindex.challenge.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GraphLookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.mindex.challenge.data.Employee;

@Component
public class EmployeeDirectReportRepositoryImpl implements EmployeeDirectReportRepository {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public Employee findEmployeeWithHydratedDirectReports(final String employeeId) {
        final MatchOperation matchToEmployeeId = Aggregation.match(Criteria.where("employeeId").is(employeeId));

        final ProjectionOperation projectDirectReportEmployeeId = Aggregation
                .project(Employee.class)
                .and("directReports.employeeId")
                .as("directReports");

        final GraphLookupOperation graphLookup = GraphLookupOperation.builder()
                .from("employee")
                .startWith("$directReports")
                .connectFrom("directReports")
                .connectTo("employeeId")
                .as("directReports");

        final TypedAggregation<Employee> aggregation = Aggregation.newAggregation(Employee.class,
                matchToEmployeeId,
                projectDirectReportEmployeeId,
                graphLookup);

        return mongoOperations.aggregate(aggregation, Employee.class).getUniqueMappedResult();
    }
}
