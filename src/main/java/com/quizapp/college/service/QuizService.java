// QuizService.java

package com.quizapp.college.service;

import com.quizapp.college.dao.QuestionDao;
import com.quizapp.college.dao.QuizDao;
import com.quizapp.college.model.Question;
import com.quizapp.college.model.QuestionWrapper;
import com.quizapp.college.model.Quiz;
import com.quizapp.college.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public Quiz createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return quizDao.save(quiz);
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Optional<Quiz> quizOptional = quizDao.findById(id);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            List<Question> questionsFromDB = quiz.getQuestions();
            List<QuestionWrapper> questionForUser = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getQuestionTitle(), q.getId(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionForUser.add(qw);
            }
            return questionForUser;
        } else {
            throw new NoSuchElementException("Quiz not found");
        }
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quizOptional = quizDao.findById(id);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            List<Question> questions = quiz.getQuestions();
            int right = 0;
            for (int i = 0; i < Math.min(questions.size(), responses.size()); i++) {
                if (responses.get(i).getResponse().equals(questions.get(i).getRightAnswer())) {
                    right++;
                }
            }
            return right;
        } else {
            throw new NoSuchElementException("Quiz not found");
        }
    }
}



////package com.quizapp.college.service;
////
////import com.quizapp.college.dao.QuestionDao;
////import com.quizapp.college.dao.QuizDao;
////import com.quizapp.college.model.Question;
////import com.quizapp.college.model.QuestionWrapper;
////import com.quizapp.college.model.Quiz;
////import com.quizapp.college.model.Response;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.stereotype.Service;
////
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Optional;
////
////@Service
////public class QuizService {
////
////    @Autowired
////    QuizDao quizDao;
////
////    @Autowired
////    QuestionDao questionDao;
////    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
////
////        List<Question> questions = questionDao.findRandomQuestionByCategory(category,numQ) ;
////        Quiz quiz = new Quiz();
////        quiz.setTitle(title);
////        quiz.setQuestions(questions);
////        quizDao.save(quiz);
////
////        return new ResponseEntity<>("Success", HttpStatus.CREATED);
////    }
////
////    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
////       Optional<Quiz> quiz = quizDao.findById(id);
////       List<Question>questionsFromDB = quiz.get().getQuestions();
////       List<QuestionWrapper> questionForUser = new ArrayList<>();
////       for(Question q : questionsFromDB){
////           QuestionWrapper qw =new QuestionWrapper(q.getQuestionTitle(), q.getId(), q.getOption1(),q.getOption2(), q.getOption3(), q.getOption4());
////           questionForUser.add(qw);
////       }
////
////
////       return new ResponseEntity<>(questionForUser, HttpStatus.OK);
////    }
////
////
////    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
////        Quiz quiz = quizDao.findById(id).get();
////        List<Question> questions = quiz.getQuestions();
////
////        int right=0;
////        int i=0;
////        for(Response response : responses){
////            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
////                right++;
////           i++;
////        }
////        return new ResponseEntity<>(right, HttpStatus.OK);
////    }
////}
//
//
//
////package com.quizapp.college.service;
////
////import com.quizapp.college.dao.QuestionDao;
////import com.quizapp.college.dao.QuizDao;
////import com.quizapp.college.model.Question;
////import com.quizapp.college.model.Quiz;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////@Service
////public class QuizService {
////
////    @Autowired
////    QuizDao quizDao;
////
////    @Autowired
////    QuestionDao questionDao; // Autowire the QuestionDao
////
////    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
////        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);
////        Quiz quiz = new Quiz();
////        quiz.setTitle(title);
////        quiz.setQuestions(questions);
////        quizDao.save(quiz);
////        return new ResponseEntity<>("Success", HttpStatus.CREATED);
////    }
////}
////
//
//
//
////
////package com.quizapp.college.service;
////
////import com.quizapp.college.dao.QuestionDao;
////import com.quizapp.college.dao.QuizDao;
////import com.quizapp.college.model.Question;
////import com.quizapp.college.model.QuestionWrapper;
////import com.quizapp.college.model.Quiz;
////import com.quizapp.college.model.Response;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.stereotype.Service;
////
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Optional;
////
////@Service
////public class QuizService {
////
////    @Autowired
////    private QuizDao quizDao;
////
////    @Autowired
////    private QuestionDao questionDao;
////
////    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
////        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);
////        if (questions.isEmpty()) {
////            return new ResponseEntity<>("No questions found for the specified category.", HttpStatus.NOT_FOUND);
////        }
////
////        Quiz quiz = new Quiz();
////        quiz.setTitle(title);
////        quiz.setQuestions(questions);
////        quizDao.save(quiz);
////
////        return new ResponseEntity<>("Quiz created successfully.", HttpStatus.CREATED);
////    }
////
////    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
////        Optional<Quiz> quizOptional = quizDao.findById(id);
////        if (quizOptional.isEmpty()) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////
////        Quiz quiz = quizOptional.get();
////        List<Question> questions = quiz.getQuestions();
////
////        List<QuestionWrapper> questionWrappers = new ArrayList<>();
////        for (Question question : questions) {
////            QuestionWrapper questionWrapper = new QuestionWrapper(
////                    question.getQuestionTitle(),
////                    question.getId(),
////                    question.getOption1(),
////                    question.getOption2(),
////                    question.getOption3(),
////                    question.getOption4()
////            );
////            questionWrappers.add(questionWrapper);
////        }
////
////        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
////    }
////
//////    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//////        Optional<Quiz> quizOptional = quizDao.findById(id);
//////        if (quizOptional.isEmpty()) {
//////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//////        }
//////
//////        Quiz quiz = quizOptional.get();
//////        List<Question> questions = quiz.getQuestions();
//////
//////        if (responses.size() != questions.size()) {
//////            return new ResponseEntity<>("Number of responses doesn't match number of questions.", HttpStatus.BAD_REQUEST);
//////        }
//////
//////        int correctResponses = 0;
//////        for (int i = 0; i < responses.size(); i++) {
//////            if (responses.get(i).getResponse().equals(questions.get(i).getRightAnswer())) {
//////                correctResponses++;
//////            }
//////        }
//////
//////        return new ResponseEntity<>(correctResponses, HttpStatus.OK);
//////    }
////public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
////    Optional<Quiz> quizOptional = quizDao.findById(id);
////    if (quizOptional.isEmpty()) {
////        return ResponseEntity.notFound().build();
////    }
////
////    Quiz quiz = quizOptional.get();
////    List<Question> questions = quiz.getQuestions();
////
////    if (responses.size() != questions.size()) {
////        return ResponseEntity.badRequest().body(0); // Or you can return a message instead of 0
////    }
////
////    int correctResponses = 0;
////    for (int i = 0; i < responses.size(); i++) {
////        if (responses.get(i).getResponse().equals(questions.get(i).getRightAnswer())) {
////            correctResponses++;
////        }
////    }
////
////    return ResponseEntity.ok(correctResponses);
////}
////
////}
