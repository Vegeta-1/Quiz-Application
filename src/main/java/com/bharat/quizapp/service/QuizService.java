package com.bharat.quizapp.service;


import com.bharat.quizapp.dao.QuestionDao;
import com.bharat.quizapp.dao.QuizDao;
import com.bharat.quizapp.model.Question1;
import com.bharat.quizapp.model.QuestionWrapper;
import com.bharat.quizapp.model.Quiz;
import com.bharat.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {

        List<Question1> questions1 = questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions1(questions1);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question1> questionsFromDB = quiz.get().getQuestions1();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question1 q: questionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz =quizDao.findById(id);
        List<Question1> questions1= quiz.get().getQuestions1();
        int correct=0;
        int i=0;
        for(Response response :responses){
            if(response.getResponse().equals(questions1.get(i).getRightAnswer())){
                correct++;

            }
            i++;

        }
        return new ResponseEntity<>(correct,HttpStatus.OK);
    }
}
