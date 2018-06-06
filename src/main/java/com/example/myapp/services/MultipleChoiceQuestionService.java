package com.example.myapp.services;

import com.example.myapp.models.Exam;
import com.example.myapp.models.MultipleChoiceQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.MultipleChoiceQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoiceQuestionService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;


    @PostMapping("/api/exam/{EID}/choice")
    public MultipleChoiceQuestion createMCQuestion(@PathVariable("EID") int examId,
                                                               @RequestBody MultipleChoiceQuestion newMCQuestion){
        Optional<Exam> data = examRepository.findById(examId);
        if (data.isPresent()) {
            Exam exam = data.get();
            newMCQuestion.setExam(exam);
            return multipleChoiceQuestionRepository.save(newMCQuestion);
        }
        return null;
    }

    @PutMapping("/api/choice/{MCID}")
    public MultipleChoiceQuestion updateMCQuestion(@PathVariable("MCID") int mcId,
                                                        @RequestBody MultipleChoiceQuestion newMCQuestion){
        Optional<MultipleChoiceQuestion> data = multipleChoiceQuestionRepository.findById(mcId);
        if(data.isPresent()) {
            MultipleChoiceQuestion multipleChoiceQuestion= data.get();
            multipleChoiceQuestion.setTitle(newMCQuestion.getTitle());
            multipleChoiceQuestion.setPoints(newMCQuestion.getPoints());
            multipleChoiceQuestion.setSubtitle(newMCQuestion.getSubtitle());
            multipleChoiceQuestion.setCorrectOption(newMCQuestion.getCorrectOption());
            multipleChoiceQuestion.setOptions(newMCQuestion.getOptions());
            multipleChoiceQuestionRepository.save(multipleChoiceQuestion);
            return multipleChoiceQuestion;
        }
        return null;
    }
}
