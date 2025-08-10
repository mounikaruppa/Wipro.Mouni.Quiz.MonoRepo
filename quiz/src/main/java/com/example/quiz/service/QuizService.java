package com.example.wipro.mouni.quiz.service;

import com.example.quiz.entity.QuestionWrapper;
import com.example.quiz.entity.Response;
import com.example.quiz.entity.Quiz;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(String title, List<Long> questionIds);

    Quiz getQuizById(Long id);

    List<Quiz> getAllQuizzes();

    void deleteQuiz(Long id);

    List<QuestionWrapper> getQuizQuestions(Long quizId);

    int submitQuiz(Long quizId, List<Response> responses);
}

