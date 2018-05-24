package com.example.myapp.repositories;

import com.example.myapp.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface CourseRepository
        extends CrudRepository<Course, Integer> {
}
