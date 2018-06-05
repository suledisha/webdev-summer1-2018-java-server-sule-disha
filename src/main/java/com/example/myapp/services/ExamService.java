package com.example.myapp.services;
import com.example.myapp.models.Exam;
import com.example.myapp.models.Lesson;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ExamRepository examRepository;

    @PostMapping("/api/lesson/{lessonId}/exam")
    public Exam createExam(@PathVariable("lessonId") int lessonId,
                             @RequestBody Exam newExam) {

        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if(data.isPresent()) {
            Lesson lesson = data.get();
            newExam.setLesson(lesson);
            return examRepository.save(newExam);
        }
        return null;
    }

    @DeleteMapping("/api/exam/{eId}")
    public void deleteExam(
            @PathVariable("eId")
                    int examId) {
        examRepository.deleteById(examId);
    }

}
