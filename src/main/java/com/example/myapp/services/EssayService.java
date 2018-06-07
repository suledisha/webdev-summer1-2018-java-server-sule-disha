package com.example.myapp.services;


import com.example.myapp.models.EssayQuestion;
import com.example.myapp.models.Exam;
import com.example.myapp.repositories.EssayQuestionRepository;
import com.example.myapp.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    EssayQuestionRepository essayQuestionRepository;

    @PostMapping("/api/exam/{EID}/essay")
    public EssayQuestion createEssayQuestion(@PathVariable("EID") int examId,
                                             @RequestBody EssayQuestion newEssayQuestion){
        Optional<Exam> data = examRepository.findById(examId);
        //EssayQuestion eq= new EssayQuestion();
        if (data.isPresent()) {
            Exam exam = data.get();
            //eq.setId('1');
            //eq.setTitle("Essay Question 1");
            //eq.setType("Essay");
            //eq.setPoints(0);
            newEssayQuestion.setExam(exam);
            return essayQuestionRepository.save(newEssayQuestion);
    }
    return null;
}
    @PutMapping("/api/essay/{essayId}")
    public EssayQuestion updateEssay(@PathVariable("essayId") int essayId,
                                     @RequestBody EssayQuestion newEssayQuestion){
        Optional<EssayQuestion> data = essayQuestionRepository.findById(essayId);
        if(data.isPresent()) {
            EssayQuestion essayQuestion= data.get();
            essayQuestion.setTitle(newEssayQuestion.getTitle());
            essayQuestion.setPoints(newEssayQuestion.getPoints());
            essayQuestion.setSubtitle(newEssayQuestion.getSubtitle());
            essayQuestionRepository.save(essayQuestion);
            return essayQuestion;
        }
        return null;
    }
}