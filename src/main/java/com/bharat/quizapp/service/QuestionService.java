package com.bharat.quizapp.service;


import com.bharat.quizapp.model.Question1;
import com.bharat.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question1> getAllQuestions() {
         return questionDao.findAll();
    }

    public List<Question1> getQuestionsByCategory(String topic) {
        return questionDao.findByCategory(topic);
    }

    public String addQuestion(Question1 question1) {
        questionDao.save(question1);
        return "success";
    }
    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "success";
    }

//    public String updateQuestion(Integer id,Question1 question1) {
//        questionDao.saveAndFlush(question1);
//        return "success";
//    }


}
