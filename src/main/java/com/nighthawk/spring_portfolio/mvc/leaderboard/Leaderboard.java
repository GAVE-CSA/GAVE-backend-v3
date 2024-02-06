package com.nighthawk.spring_portfolio.mvc.leaderboard;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.vladmihalcea.hibernate.type.json.JsonType;

import jakarta.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
//@AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
@Convert(attributeName ="leaderboard", converter = JsonType.class)
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;
    private int plays;
    private double ptime;
    private double stime;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "6jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 

    //constructor with user name, # of plays, time for both games 
    public Leaderboard(String name, int plays, double ptime, double stime) {
        this.name = name;
        this.plays = plays;
        this.ptime = ptime;
        this.stime = stime;
    }

    //setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
		this.name = name;
	}

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays){
        this.plays = plays;
    }

    public double getPtime() {
        return ptime;
    }

    public void setPtime(double ptime) {
        this.ptime = ptime;
    }

    public double getStime() {
        return stime;
    }

    public void setStime(double stime) {
        this.stime = stime;
    }

    public static List<Leaderboard> createIntialData() {
        List<Leaderboard> initialData = new ArrayList<>();
        initialData.add(new Leaderboard("vivian", 1000000, 1200, 1200));
        return initialData;
    }

    public static List<Leaderboard> init() {
        return createIntialData();
    }
}