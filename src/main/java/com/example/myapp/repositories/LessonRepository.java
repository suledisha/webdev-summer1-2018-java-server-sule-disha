package com.example.myapp.repositories;

import com.example.myapp.models.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface LessonRepository extends CrudRepository<Lesson, Integer> {}
