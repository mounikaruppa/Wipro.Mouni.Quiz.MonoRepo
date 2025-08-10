package com.example.quiz.service;
import com.example.quiz.entity.Question;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id " + id));
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Question existing = getQuestionById(id);
        existing.setQuestionTitle(question.getQuestionTitle());
        existing.setOption1(question.getOption1());
        existing.setOption2(question.getOption2());
        existing.setOption3(question.getOption3());
        existing.setOption4(question.getOption4());
        existing.setCorrectAnswer(question.getCorrectAnswer());
        existing.setDifficultyLevel(question.getDifficultyLevel());
        existing.setCategory(question.getCategory());
        return questionRepository.save(existing);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> getQuestionsByDifficultyLevel(String difficultyLevel) {
        return questionRepository.findByDifficultyLevel(difficultyLevel);
    }

    @Override
    public List<Question> getQuestionsByCategoryAndDifficulty(String category, String difficulty) {
        return questionRepository.findByCategoryAndDifficultyLevel(category, difficulty);
    }

}
