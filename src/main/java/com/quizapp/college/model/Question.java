package com.quizapp.college.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_title") // Added explicit column mapping
    private String questionTitle;

    @Column(name = "option_1") // Adjusted column names for consistency
    private String option1;

    @Column(name = "option_2")
    private String option2;

    @Column(name = "option_3")
    private String option3;

    @Column(name = "option_4")
    private String option4;

    @Column(name = "right_answer")
    private String rightAnswer;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name ="category")
    private String category;

    //Constructors, getters, and setters
}
