package com.example.wipro.mouni.quiz.entity;

import com.example.quiz.entity.QuestionWrapper;
import com.example.quiz.entity.Response;
import com.example.quiz.entity.Quiz;
import com.example.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Quizzes")
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @Operation(summary = "Create a quiz (provide title and questionIds)")
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        @SuppressWarnings("unchecked")
        List<Integer> ids = (List<Integer>) body.get("questionIds");
        List<Long> qids = ids.stream().map(Integer::longValue).toList();
        Quiz quiz = service.createQuiz(title, qids);
        return ResponseEntity.status(201).body(quiz);
    }

    @Operation(summary = "Get wrapped quiz questions (without answers)")
    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long id) {
        return ResponseEntity.ok(service.getQuizQuestions(id));
    }

    @Operation(summary = "Submit quiz answers (list of {questionId, response})")
    @PostMapping("/{id}/submit")
    public ResponseEntity<Map<String, Integer>> submitQuiz(@PathVariable Long id, @Valid @RequestBody List<Response> responses) {
        int score = service.submitQuiz(id, responses);
        return ResponseEntity.ok(Map.of("score", score));
    }
}

