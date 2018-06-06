package com.example.myapp.services;

import com.example.myapp.models.Exam;
import com.example.myapp.models.TrueFalseQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.TrueFalseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueFalseService{
        @Autowired
        ExamRepository examRepository;
        @Autowired
        TrueFalseQuestionRepository trueFalseQuestionRepository;

    @PostMapping("/api/exam/{EID}/truefalse")
    public TrueFalseQuestion createTFQuestion(@PathVariable("EID") int examId,
                                              @RequestBody TrueFalseQuestion newTFQuestion){
        Optional<Exam> data = examRepository.findById(examId);
        if (data.isPresent()) {
            Exam exam = data.get();
            newTFQuestion.setExam(exam);
            return trueFalseQuestionRepository.save(newTFQuestion);
        }
        return null;
    }

    @PutMapping("/api/truefalse/{TFID}")
    public TrueFalseQuestion updateMCQuestion(@PathVariable("TFID") int tfId,
                                                   @RequestBody TrueFalseQuestion newTFQuestion){
        Optional<TrueFalseQuestion> data = trueFalseQuestionRepository.findById(tfId);
        if(data.isPresent()) {
            TrueFalseQuestion trueFalseQuestion= data.get();
            trueFalseQuestion.setTitle(newTFQuestion.getTitle());
            trueFalseQuestion.setPoints(newTFQuestion.getPoints());
            trueFalseQuestion.setSubtitle(newTFQuestion.getSubtitle());
            trueFalseQuestion.setTrue(newTFQuestion.isTrue());
            trueFalseQuestionRepository.save(trueFalseQuestion);
            return trueFalseQuestion;
        }
        return null;
    }

}
