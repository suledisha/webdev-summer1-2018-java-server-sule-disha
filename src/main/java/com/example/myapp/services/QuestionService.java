package com.example.myapp.services;

import com.example.myapp.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @DeleteMapping("/api/question/{qId}")
    public void deleteQuestion(
            @PathVariable("qId")
                    int questionId) {
        questionRepository.deleteById(questionId);
    }
}
