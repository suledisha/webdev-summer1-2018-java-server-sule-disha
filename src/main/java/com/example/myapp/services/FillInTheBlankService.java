package com.example.myapp.services;


import com.example.myapp.models.Exam;
import com.example.myapp.models.FillInTheBlankQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.FillInTheBlankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlankService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    FillInTheBlankRepository fillInTheBlankRepository;

    @PostMapping("/api/exam/{EID}/blanks")
    public FillInTheBlankQuestion createFillInTheBlankQuestion(@PathVariable("EID") int examId,
                                                      @RequestBody FillInTheBlankQuestion newFBQuestion){
        Optional<Exam> data = examRepository.findById(examId);
        if (data.isPresent()) {
            Exam exam = data.get();
            newFBQuestion.setExam(exam);
            return fillInTheBlankRepository.save(newFBQuestion);
        }
        return null;
    }

    @PutMapping("/api/blanks/{FBID}")
    public FillInTheBlankQuestion updateFillInTheBlanks(@PathVariable("FBID") int fbId,
                                     @RequestBody FillInTheBlankQuestion newFBQuestion){
        Optional<FillInTheBlankQuestion> data = fillInTheBlankRepository.findById(fbId);
        if(data.isPresent()) {
            FillInTheBlankQuestion fillInTheBlankQuestion= data.get();
            fillInTheBlankQuestion.setTitle(newFBQuestion.getTitle());
            fillInTheBlankQuestion.setPoints(newFBQuestion.getPoints());
            fillInTheBlankQuestion.setSubtitle(newFBQuestion.getSubtitle());
            fillInTheBlankQuestion.setVariables(newFBQuestion.getVariables());
            fillInTheBlankRepository.save(fillInTheBlankQuestion);
            return fillInTheBlankQuestion;
        }
        return null;
    }
}
