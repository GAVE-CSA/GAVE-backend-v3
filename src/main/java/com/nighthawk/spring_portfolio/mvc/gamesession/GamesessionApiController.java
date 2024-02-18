package com.nighthawk.spring_portfolio.mvc.gamesession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;

import groovyjarjarantlr4.v4.runtime.misc.Tuple2; 

@RestController // annotation to simplify the creation of RESTful web services
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:4100"})
@RequestMapping("/api/gamesession")  // all requests in file begin with this URI
public class GamesessionApiController {

    @Autowired
    private GamesessionJpaRepository repository;
    private PersonJpaRepository prepository;
    
    //get entire leaderboard
    @GetMapping("/")
    public ResponseEntity<List<Gamesession>> getWholeLeaderboard() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()

        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Gamesession> createleaderboard(@RequestBody Gamesession gamesession) {
            Gamesession savedLeaderboard = repository.save(gamesession);
            return new ResponseEntity<>(savedLeaderboard, HttpStatus.CREATED);
    }

    //get request for game time
    @GetMapping("/{gameId}")
    public ResponseEntity<List<Tuple2<Long, Long>>> getLeaderboard(@PathVariable int gameId) {
        // final goal
        // 1. read all data from the Gamesession table
        // 2. filter only data with the game id
        List<Gamesession> sessionList = repository.findAllByGameId(gameId);
        
        // 3. group the filtered data by user id
        Map<Long, List<Gamesession>> sessionsByUId 
            = sessionList.stream().collect(Collectors.groupingBy(s -> s.getUserId()));

        // 4. calculate the shortest time for each group above

        List<Tuple2<Long, Long>> result = new ArrayList<>();
        for (Long userId : sessionsByUId.keySet()) {
            List<Gamesession> gList = sessionsByUId.get(userId); 
            Long minSessionTime = Long.MAX_VALUE;
            
            //code to get name by id
            for (Gamesession gamesession : gList) {
                Long duration = gamesession.getEndTime() - gamesession.getStartTime();
                if (duration < minSessionTime) {
                    minSessionTime = duration;
                    }
            }
            Tuple2<Long, Long> tuple = new Tuple2<>(userId, minSessionTime);
            result.add(tuple);
        } 
        
        // sort users by time
        Comparator<Tuple2> sessionComparator = new Comparator<Tuple2>() {
            public int compare(Tuple2 t1, Tuple2 t2) {
                return (int)((Long)t1.getItem2() - (Long)t2.getItem2());
            }
        };
        result.sort(sessionComparator);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<Tuple2<Long, Long>>> getPlaysLeaderboard() {
            List<Gamesession> sessions = repository.findAll();
            
            //creates list of person id mapped to count
            Map<Long, Long> countMap = sessions.stream().collect(Collectors.groupingBy(s -> s.getUserId(), Collectors.counting()));

            //gets list of person 
            //List<Person> users = prepository.getNameList(new ArrayList<>(countMap.keySet()));

            //loop through user to get name from person and count from countmap. make into tuples
            List<Tuple2<Long, Long>> result = new ArrayList<>();
            for (Long id: countMap.keySet()) {
                Tuple2<Long, Long> t = new Tuple2<>(id, countMap.get(id));
                result.add(t);
            }
            
            Comparator<Tuple2> countComparator = new Comparator<Tuple2>() {
                public int compare(Tuple2 t1, Tuple2 t2) {
                    return (int)((Long)t2.getItem2() - (Long)t1.getItem2());
                }
            };
            result.sort(countComparator);
            
            return new ResponseEntity<>(result, HttpStatus.OK);
    }
}