package com.example.myapp.services;

import com.example.myapp.models.Course;
import com.example.myapp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/course")
    public Iterable<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/api/course")
    public Course createCourse
            (@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/api/course/{courseId}")
    public void deleteCourse(
            @PathVariable("courseId") int id) {
        courseRepository.deleteById(id);
    }

    @GetMapping("/api/course/{courseId}")
    public Course findCourseById(
            @PathVariable("courseId") int id) {
        Optional<Course> data = courseRepository.findById(id);
        if (data.isPresent()) {
            Course course = data.get();
            return course;
        }
        return null;
    }


}

