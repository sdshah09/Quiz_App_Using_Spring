package com.shaswat.quizapp.service;

import com.shaswat.quizapp.model.Question;
import com.shaswat.quizapp.dao.QuestionDao;
import com.shaswat.quizapp.dao.QuizDao;
import com.shaswat.quizapp.model.QuestionWrapper;
import com.shaswat.quizapp.model.Quiz;
import com.shaswat.quizapp.model.Response;
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

    public ResponseEntity<String> createQuiz(String category,int num){
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,num);
//        System.out.println(questions);
        Quiz quiz = new Quiz();
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(int id){
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> qFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> userQuestions = new ArrayList<>();
        for(Question q: qFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getOption1(),q.getOption2(),q.getOption3());
            userQuestions.add(qw);
        }
        return new ResponseEntity<>(userQuestions,HttpStatus.OK);
    }
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
