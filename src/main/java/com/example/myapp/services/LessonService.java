package com.example.myapp.services;

import com.example.myapp.models.Lesson;
import com.example.myapp.models.Module;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    CourseRepository courseRepository;


    @PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
    public Lesson createLesson(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId,
                               @RequestBody Lesson newLesson) {

        Optional<Module> data = moduleRepository.findById(moduleId);
        if(data.isPresent()) {
            Module module = data.get();
            newLesson.setModule(module);
            return lessonRepository.save(newLesson);
        }
        return null;

    }

    @GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
    public List<Lesson> findAllModulesForCourse(
            @PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId) {
        Optional<Module> data =
                moduleRepository.findById(moduleId);
        if(data.isPresent()) {
            Module module = data.get();
            return module.getLessons();
        }
        return null;
    }

    @DeleteMapping("/api/lesson/{lId}")
    public void deleteModule(
            @PathVariable("lId")
                    int lessonId) {
        lessonRepository.deleteById(lessonId);
    }


}
