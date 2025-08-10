package com.example.wipro.mouni.quiz.service;

import com.example.quiz.entity.Question;
import com.example.quiz.entity.QuestionWrapper;
import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.Response;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Quiz createQuiz(String title, List<Long> questionIds) {
        List<Question> questions = questionRepository.findAllById(questionIds);
        if (questions == null || questions.isEmpty()) {
            throw new ResourceNotFoundException("No questions found for given IDs");
        }
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public void deleteQuiz(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quiz not found with id: " + id);
        }
        quizRepository.deleteById(id);
    }

    @Override
    public List<QuestionWrapper> getQuizQuestions(Long quizId) {
        Quiz quiz = getQuizById(quizId);
        List<Question> questions = quiz.getQuestions() == null ? Collections.emptyList() : quiz.getQuestions();
        return questions.stream()
                .map(q -> new QuestionWrapper(
                        q.getId(),
                        q.getQuestionTitle(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public int submitQuiz(Long quizId, List<Response> responses) {
        Quiz quiz = getQuizById(quizId);
        Map<Long, Question> map = quiz.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        int score = 0;
        for (Response r : responses) {
            Question q = map.get(r.getQuestionId());
            if (q != null &&
                q.getCorrectAnswer() != null &&
                q.getCorrectAnswer().equalsIgnoreCase(r.getResponse().trim())) {
                score++;
            }
        }
        return score;
    }
}

