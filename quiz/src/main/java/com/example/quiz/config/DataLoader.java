package com.example.quiz.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.quiz.entity.Question;
import com.example.quiz.entity.enums.Category;
import com.example.quiz.entity.enums.DifficultyLevel;
import com.example.quiz.repository.QuestionRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final QuestionRepository questionRepository;

    public DataLoader(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) {
        if (questionRepository.count() == 0) {

            Question q1 = new Question();
            q1.setQuestionTitle("Which command is used to create a new branch in Git?");
            q1.setOption1("git branch new-branch");
            q1.setOption2("git checkout new-branch");
            q1.setOption3("git commit new-branch");
            q1.setOption4("git init new-branch");
            q1.setCorrectAnswer("git branch new-branch");
            q1.setDifficultyLevel(DifficultyLevel.EASY);
            q1.setCategory(Category.GITHUB); 
            Question q2 = new Question();
            q2.setQuestionTitle("Which language runs in a web browser?");
            q2.setOption1("Java");
            q2.setOption2("C");
            q2.setOption3("Python");
            q2.setOption4("JavaScript");
            q2.setCorrectAnswer("JavaScript");
            q2.setDifficultyLevel(DifficultyLevel.MEDIUM);
            q2.setCategory(Category.SPRING);

            Question q3 = new Question();
            q3.setQuestionTitle("What is the time complexity of binary search?");
            q3.setOption1("O(n)");
            q3.setOption2("O(log n)");
            q3.setOption3("O(n log n)");
            q3.setOption4("O(1)");
            q3.setCorrectAnswer("O(log n)");
            q3.setDifficultyLevel(DifficultyLevel.HARD);
            q3.setCategory(Category.GITHUB);  // Changed from ALGORITHMS to GITHUB

            questionRepository.saveAll(Arrays.asList(q1, q2, q3));
        }
    }
}
