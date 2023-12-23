package com.bharat.quizapp.dao;

import com.bharat.quizapp.model.Question1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository< Question1, Integer> {

    List<Question1> findByCategory(String topic);


    @Query(value="SELECT * FROM question1 q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question1> findRandomQuestionsByCategory(String category, Integer numQ);
}
