package com.nighthawk.spring_portfolio.mvc.cybernews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// link to the file that has the function to query
@Repository
public interface Link extends JpaRepository<Template, Long> {

    @Query(nativeQuery = true, name = "Data.findByOpSys")
    List<Template> findByOpSys(String OpSysName); //change to operating system
}