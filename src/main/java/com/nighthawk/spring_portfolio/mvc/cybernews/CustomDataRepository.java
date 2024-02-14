package com.nighthawk.spring_portfolio.mvc.cybernews;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// Get data from database using JPA 
@Repository
public class CustomDataRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to find data by operating system name using native SQL query.
    public List<Template> findByOpSys(String OpSysName) {
        // Build SQL query to select all columns from the table specified by OpSysName.
        String sql = "SELECT * FROM " + OpSysName; 
        Query query = entityManager.createNativeQuery(sql, Template.class);
        return query.getResultList();
    }
}
