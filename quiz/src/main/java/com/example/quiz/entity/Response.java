package com.example.wipro.mouni.quiz.entity;

public class Response {
    private Long questionId;
    private String response;

    public Response() {
    }

    public Response(Long questionId, String response) {
        this.questionId = questionId;
        this.response = response;
    }

    // Getter and Setter for questionId
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    // Getter and Setter for response
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}


