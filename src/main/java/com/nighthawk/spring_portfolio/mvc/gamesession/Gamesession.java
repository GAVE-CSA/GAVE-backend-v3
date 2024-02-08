package com.nighthawk.spring_portfolio.mvc.gamesession;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.vladmihalcea.hibernate.type.json.JsonType;

import jakarta.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
//@AllArgsConstructor
@Entity 
@Convert(attributeName ="gamesession", converter = JsonType.class)
public class Gamesession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column 
    private int userId;
    private int gameId;
    private double startTime;
    private double endTime; 
    
    @Column(unique=true)
    private int sessionId;
 
    public Gamesession(int userId, int gameId, int sessionId, double startTime, double endTime) {
        this.userId = userId;
        this.gameId = gameId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}