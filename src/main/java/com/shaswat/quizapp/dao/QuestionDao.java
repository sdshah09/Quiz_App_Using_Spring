package com.shaswat.quizapp.dao;

import com.shaswat.quizapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    // To let know the JpaRepository to find a coloumn and it's data you have to write the name of that function in a format findBy+"coloumn name"
    List<Question> findByCategory(String category);

}
