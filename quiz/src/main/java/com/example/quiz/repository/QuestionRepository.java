package com.example.wipro.mouni.quiz.repository;

import com.example.quiz.entity.Question;
import com.example.quiz.entity.enums.Category;
import com.example.quiz.entity.enums.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);
    List<Question> findByDifficultyLevel(DifficultyLevel difficultyLevel);
	List<Question> findByDifficultyLevel(String difficulty);
	List<Question> findByCategoryAndDifficultyLevel(String category, String difficulty);
}

