package com.example.myapp.services;
import com.example.myapp.models.Exam;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Question;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ExamRepository examRepository;


    @GetMapping("/api/lesson/{lessonId}/exam")
    public List<Exam> findAllExamsForLesson(
            @PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data =
                lessonRepository.findById(lessonId);
        List<Exam> exams = new ArrayList<>();
        if(data.isPresent()) {
            Lesson lesson = data.get();
            List<Widget> widgets= lesson.getWidgets();
            for (Widget widget : widgets) {
                if (widget.getWidgetType().equalsIgnoreCase("Exam")) {
                    Optional<Exam> exam = examRepository.findById(widget.getId());
                    if (exam.isPresent()) {
                        Exam ex=exam.get();
                        exams.add(ex);
                    }
                }
            }

            return exams;
        }
        return null;
    }

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

    @PutMapping("/api/exam/{eId}")
    public Exam updateExam(
            @PathVariable("eId") int examId, @RequestBody Exam newExam) {

        Optional<Exam> data = examRepository.findById(examId);
        if(data.isPresent()) {
            //System.out.println("IN UPDATE EXAM "+examId);
            Exam exam= data.get();
            exam.setName(newExam.getName());
            examRepository.save(exam);
            return exam;
        }
        return null;

    }

    @GetMapping("/api/exam/{examId}/question")
    public List<Question> findAllQuestionsForExam(
            @PathVariable("examId") int examId) {
        Optional<Exam> data =
                examRepository.findById(examId);
        if(data.isPresent()) {
            Exam exam = data.get();
            List<Question> questions= exam.getQuestions();
            return questions;
        }
        return null;
    }


}
