package com.nighthawk.spring_portfolio.mvc.cybernews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Spring Data JPA repository interface that provides  standard CRUD operations for Template entity 
// Includes custom query method (findByOpSys) for fetching data 
@Repository
public interface Link extends JpaRepository<Template, Long> {

    @Query(nativeQuery = true, name = "Data.findByOpSys")
    List<Template> findByOpSys(String OpSysName); 
}