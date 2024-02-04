package com.nighthawk.spring_portfolio.mvc.questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "Question.findByCourse", query = "SELECT * FROM ?1", resultClass = Question.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "severity")
    private String severety;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "iacontrols")
    private String iacontrols;

    @Column(name = "ruleID")
    private String ruleid;

    @Column(name = "fixid")
    private String fixid;

    @Column(name = "fixtext")
    private String fixtext;

    @Column(name = "checkid")
    private String checkid;

    @Column(name = "checktext")
    private String checktext;

    // Getters and setters
}
