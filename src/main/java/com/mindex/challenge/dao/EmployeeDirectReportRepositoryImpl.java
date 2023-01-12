package com.mindex.challenge.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GraphLookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.mindex.challenge.data.Employee;

@Component
public class EmployeeDirectReportRepositoryImpl implements EmployeeDirectReportRepository {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Employee> findEmployeeAndDirectReports(final String employeeId) {
        final MatchOperation match = Aggregation.match(Criteria.where("employeeId").is(employeeId));

        final GraphLookupOperation graphLookup = GraphLookupOperation.builder().from("employee")
                .startWith("directReports").connectFrom("employeeId").connectTo("employeeId").as("directs");

        final Aggregation aggregation = Aggregation.newAggregation(match, graphLookup);

        AggregationResults<Employee> results = mongoOperations.aggregate(aggregation, "directs", Employee.class);

        return results.getMappedResults();
    }
}
