package com.nighthawk.spring_portfolio.mvc.cybernews;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// get data from database 
@Repository
public class CustomDataRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Template> findByOpSys(String OpSysName) {
        String sql = "SELECT * FROM " + OpSysName; // Ensure OpSysName is validated or mapped
        Query query = entityManager.createNativeQuery(sql, Template.class);
        return query.getResultList();
    }
}
