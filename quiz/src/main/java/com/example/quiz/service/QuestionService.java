package com.example.quiz.service;

import com.example.quiz.entity.Question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    Question createQuestion(Question question);
    Page<Question> getAllQuestions(Pageable pageable);
    Question getQuestionById(Long id);
    Question updateQuestion(Long id, Question updated);
    void deleteQuestion(Long id);
	List<Question> getQuestionsByDifficultyLevel(String difficultyLevel);
	List<Question> getQuestionsByCategory(String category);
	List<Question> getQuestionsByCategoryAndDifficulty(String category, String difficulty);


	
}
