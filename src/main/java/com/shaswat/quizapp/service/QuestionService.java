package com.shaswat.quizapp.service;

import com.shaswat.quizapp.Question;
import com.shaswat.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<List<Question>> getQuestionCategory(String category){
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> createQuestion(Question question){
            questionDao.save(question);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
    public ResponseEntity<String> deleteQuestion(Question question){
        questionDao.delete(question);
        return new ResponseEntity<>("deleted",HttpStatus.GONE);
    }
}
