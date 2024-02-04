package com.nighthawk.spring_portfolio.mvc.cybernews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// list of service functions
@Service
public class DataService {

    @Autowired
    private Link dataRepository;

    public List<Template> getAllData() {
        return dataRepository.findAll();
    }

    @Autowired
    private CustomDataRepository customDataRepository;

    public List<Template> getDataByOpSys(String OpSysName) {
        return customDataRepository.findByOpSys(OpSysName);
    }


    // Other business logic methods
}
