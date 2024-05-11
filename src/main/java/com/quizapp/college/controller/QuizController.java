//////package com.quizapp.college.controller;
//////
//////import org.springframework.http.HttpStatus;
//////import org.springframework.http.ResponseEntity;
//////import org.springframework.web.bind.annotation.*;
//////
//////@RestController
//////@RequestMapping("quiz")
//////public class QuizController {
//////
//////    @PostMapping("create")
//////    public ResponseEntity<String> createQuiz(@RequestParam String category,
//////                                             @RequestParam int numQ,
//////                                             @RequestParam String title){
//////        // Check if title parameter is present
//////        if (title == null || title.isEmpty()) {
//////            // Return a 400 Bad Request response if 'title' parameter is missing
//////            return new ResponseEntity<>("Title parameter is required", HttpStatus.BAD_REQUEST);
//////        }
//////
//////        // Your logic for creating the quiz goes here
//////        return new ResponseEntity<>("Quiz created successfully", HttpStatus.OK);
//////    }
//////}
////package com.quizapp.college.controller;
////
////
////import com.quizapp.college.model.QuestionWrapper;
////import com.quizapp.college.model.Response;
////import com.quizapp.college.service.QuizService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////
////@RestController
////@RequestMapping("quiz")
////public class QuizController {
////
////    @Autowired
////    QuizService quizService;
////
////    @PostMapping("create")
////    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
////        return quizService.createQuiz(category,numQ,title);
////
////    }
////
////    @GetMapping("get/{id}")
////    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
////        return quizService.getQuizQuestions(id);
////
////    }
////
////
////    @PostMapping("submit/{id}")
////    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
////        return quizService.calculateResult(id, responses);
////    }
////
////}
//
//// QuizController.java
//
package com.quizapp.college.controller;

import com.quizapp.college.model.QuestionWrapper;
import com.quizapp.college.model.Response;
import com.quizapp.college.model.Quiz;
import com.quizapp.college.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(category, numQ, title));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(quizService.getQuizQuestions(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        try {
            return ResponseEntity.ok(quizService.calculateResult(id, responses));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

