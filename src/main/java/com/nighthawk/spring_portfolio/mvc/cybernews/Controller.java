package com.nighthawk.spring_portfolio.mvc.cybernews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Endpoint 
@RestController
@RequestMapping("/api/vulnerability")
public class Controller {

    @Autowired
    private DataService DataService;

    @GetMapping
    public List<Template> getAllData() {
        return DataService.getAllData();
    }

    @GetMapping("/software/{OpSysName}")
    public List<Template> getDataByOpSys(@PathVariable String OpSysName) {
        return DataService.getDataByOpSys(OpSysName);
    }
}
