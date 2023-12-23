package com.bharat.quizapp.controller;


import com.bharat.quizapp.model.Question1;
import com.bharat.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question1> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{topic}")
    public List<Question1> getQuestionsByCategory(@PathVariable String topic){
        return questionService.getQuestionsByCategory(topic);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question1 question1){
        return questionService.addQuestion(question1);
    }
    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);}

//    @PostMapping("update/{id}")
//        public String addQuestion(@PathVariable Integer id ,@RequestBody Question1 question1){
//            return questionService.updateQuestion(id,question1);
//        }
//
}

