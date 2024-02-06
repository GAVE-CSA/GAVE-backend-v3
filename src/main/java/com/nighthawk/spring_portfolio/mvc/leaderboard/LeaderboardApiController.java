package com.nighthawk.spring_portfolio.mvc.leaderboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;

import java.text.SimpleDateFormat;
import java.util.List;
//import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/leaderboard")  // all requests in file begin with this URI
public class LeaderboardApiController {

    @Autowired
    private LeaderboardJpaRepository repository;
    
    /* GET */
    @GetMapping("/")
    public ResponseEntity<List<Leaderboard>> getLeaderboard() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    //create a leaderboard
    @PostMapping("/create")
    public ResponseEntity<Leaderboard> createleaderboard(@RequestBody Leaderboard leaderboard) {
        Leaderboard savedLeaderboard = repository.save(leaderboard);
        return new ResponseEntity<>(savedLeaderboard, HttpStatus.CREATED);
    }

    //import information from person api to track data per person
    //get name of user
    //create time and plays (post)
    //update time and plays (post)

    // @GetMapping("/timefetch/{gameName}")
    // public double getTime(@PathVariable String gameName) {
    //     // Find the leaderboard by gameName
    //     Leaderboard existingLeaderboard = repository.findByGameName(gameName);
    //     double gameTime = existingLeaderboard.getTime();
    //     return(gameTime);
    // }
    
    
    // update ptime
    @PostMapping("/updatePtime")
    public ResponseEntity<Leaderboard> updatetime(@PathVariable String gameName, @RequestBody Leaderboard leaderboard) {
 
        Leaderboard existingLeaderboard = repository.findByGameName(gameName);

         if (existingLeaderboard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found if not exists
        }
        double newTime = leaderboard.getPtime();

        existingLeaderboard.setPtime(newTime);
 
        Leaderboard savedLeaderboard = repository.save(existingLeaderboard);

        return new ResponseEntity<>(savedLeaderboard, HttpStatus.OK);
    }

    // update stime
    @PostMapping("/updateStime")
    public ResponseEntity<Leaderboard> updateStime(@PathVariable String gameName, @RequestBody Leaderboard leaderboard) {
 
        Leaderboard existingLeaderboard = repository.findByGameName(gameName);

         if (existingLeaderboard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found if not exists
        }
        double newTime = leaderboard.getStime();

        existingLeaderboard.setStime(newTime);
 
        Leaderboard savedLeaderboard = repository.save(existingLeaderboard);

        return new ResponseEntity<>(savedLeaderboard, HttpStatus.OK);
    }
}
 