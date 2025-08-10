package com.example.wipro.mouni.quiz.controller;

import com.example.quiz.entity.Question;
import com.example.quiz.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Questions")
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @Operation(summary = "Create question")
    @PostMapping
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question) {
        Question created = service.createQuestion(question);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Get paginated questions")
    @GetMapping
    public ResponseEntity<Page<Question>> getAllQuestions(Pageable pageable) {
        return ResponseEntity.ok(service.getAllQuestions(pageable));
    }

    @Operation(summary = "Get question by id")
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getQuestionById(id));
    }

    @Operation(summary = "Update question")
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @Valid @RequestBody Question question) {
        return ResponseEntity.ok(service.updateQuestion(id, question));
    }

    @Operation(summary = "Delete question")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        service.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get questions by category")
    @GetMapping("/questions/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        List<Question> questions = service.getQuestionsByCategory(category);
        return ResponseEntity.ok(questions);
    }


    @Operation(summary = "Get questions by difficulty level")
    @GetMapping("/questions/difficulty/{difficultyLevel}")
    public ResponseEntity<List<Question>> getQuestionsByDifficulty(@PathVariable String difficultyLevel) {
        List<Question> questions = service.getQuestionsByDifficultyLevel(difficultyLevel);
        return ResponseEntity.ok(questions);
    }
}


