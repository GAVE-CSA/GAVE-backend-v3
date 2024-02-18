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
    private Long userId;
    private int gameId;
    private Long startTime;
    private Long endTime; 
    
    @Column(unique=true)
    private int sessionId;
 
    public Gamesession(long userId, int gameId, int sessionId, Long startTime, Long endTime) {
        this.userId = userId;
        this.gameId = gameId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // public static Specification<Person> hasUsernameWithId(int userId) {
    // return (root, query, criteriaBuilder) -> {
    //     Join<Gamesession, Person> usernamesId = root.join("userId");
    //     return criteriaBuilder.equal(usernamesId.get("userId"), userId);
    // };
    //}
}