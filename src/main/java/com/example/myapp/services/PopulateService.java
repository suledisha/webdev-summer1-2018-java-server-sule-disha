package com.example.myapp.services;


import com.example.myapp.models.*;
import com.example.myapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class PopulateService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    TrueFalseQuestionRepository trueFalseQuestionRepository;

    @GetMapping("/api/create/course")
    public Course createCourse() {
        Course c = new Course();
        c.setId('1');
        c.setTitle("CS101");
        return courseRepository.save(c);
    }

    @GetMapping("/api/create/course/{courseId}/module")
    public Module createModule(@PathVariable("courseId") int courseId){
        Optional<Course> data = courseRepository.findById(courseId);
        Module m= new Module();
        if (data.isPresent()) {
            Course course = data.get();
            m.setId('1');
            m.setTitle("Module1");
            m.setCourse(course);
            return moduleRepository.save(m);
        }
        return null;
    }


    @GetMapping("/api/create/module/{moduleId}/lesson")
    public Lesson createLesson(@PathVariable("moduleId") int moduleId){
        Optional<Module> data = moduleRepository.findById(moduleId);
        Lesson l= new Lesson();
        if (data.isPresent()) {
            Module module = data.get();
            l.setId('1');
            l.setTitle("Lesson1");
            l.setModule(module);
            return lessonRepository.save(l);
        }
        return null;
    }

    @GetMapping("/api/create/lesson/{lessonId}/exam")
    public Exam createExam(@PathVariable("lessonId") int lessonId){
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        Exam e= new Exam();
        if (data.isPresent()) {
            Lesson lesson = data.get();
            e.setId('1');
            e.setTitle("Exam1");
            e.setLesson(lesson);
            return examRepository.save(e);
        }
        return null;
    }


    @GetMapping("/api/create/exam/{examId}/TFQuestion")
    public TrueFalseQuestion createQuestion(@PathVariable("examId") int examId){
        Optional<Exam> data = examRepository.findById(examId);
        TrueFalseQuestion tfq= new TrueFalseQuestion();
        if (data.isPresent()) {
            Exam exam = data.get();
            tfq.setId('1');
            tfq.setTitle("True Or False Question 1");
            tfq.setExam(exam);
            return trueFalseQuestionRepository.save(tfq);
        }
        return null;
    }
}


