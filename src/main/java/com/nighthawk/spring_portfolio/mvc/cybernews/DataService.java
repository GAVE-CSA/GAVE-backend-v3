package com.nighthawk.spring_portfolio.mvc.cybernews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Intermediary between controller & data repositories
@Service
public class DataService {

    @Autowired
    private Link dataRepository;

    // Method to retrieve all data from the injected data repository
    public List<Template> getAllData() {
        return dataRepository.findAll();
    }

    @Autowired
    private CustomDataRepository customDataRepository;

    // Method to retrieve data by operating system name using the injected custom data repository
    public List<Template> getDataByOpSys(String OpSysName) {
        return customDataRepository.findByOpSys(OpSysName);
    }

}
