package com.nighthawk.spring_portfolio.mvc.gamesession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;

import groovyjarjarantlr4.v4.runtime.misc.Tuple2; 

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/gamesession")  // all requests in file begin with this URI
public class GamesessionApiController {

    @Autowired
    private GamesessionJpaRepository repository;
    private PersonJpaRepository prepository;
    
    //get request for game time
    @GetMapping("/{gameId}")
    public ResponseEntity<List<Tuple2<Integer, Double>>> getLeaderboard(@PathVariable int gameId) {
        // final goal
        // 1. read all data from the Gamesession table
        // 2. filter only data with the game id
        List<Gamesession> sessionList = repository.findAllByGameId(gameId);
        
        // 3. group the filtered data by user id
        Map<Integer, List<Gamesession>> sessionsByUId 
            = sessionList.stream().collect(Collectors.groupingBy(s -> s.getUserId()));

        // 4. calculate the shortest time for each group above

        List<Tuple2<Integer, Double>> result = new ArrayList<>();
        for (Integer userId : sessionsByUId.keySet()) {
            List<Gamesession> gList = sessionsByUId.get(userId); 
            Double minSessionTime = Double.MAX_VALUE;
            
            //code to get name by id
            for (Gamesession gamesession : gList) {
                Double duration = gamesession.getEndTime() - gamesession.getStartTime();
                if (duration < minSessionTime) {
                    minSessionTime = duration;
                    }
            }
            Tuple2<Integer, Double> tuple = new Tuple2<>(userId, minSessionTime);
            result.add(tuple);
        } 
        
        // sort users by time
        Comparator<Tuple2> sessionComparator = new Comparator<Tuple2>() {
            public int compare(Tuple2 t1, Tuple2 t2) {
                return (int)((Double)t1.getItem2() - (Double)t2.getItem2());
            }
        };
        result.sort(sessionComparator);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}