package com.example.quiz.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Quiz API", version = "1.0", description = "Quiz Application APIs"))
@Configuration
public class SwaggerConfig { }
