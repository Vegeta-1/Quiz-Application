package com.bharat.quizapp.controller;

import com.bharat.quizapp.model.Question1;
import com.bharat.quizapp.model.QuestionWrapper;
import com.bharat.quizapp.model.Response;
import com.bharat.quizapp.service.QuizService;
import jakarta.persistence.SqlResultSetMappings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam Integer numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);

    }
    @GetMapping("get/{id}")
        public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);

    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id , @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);

    }

}
