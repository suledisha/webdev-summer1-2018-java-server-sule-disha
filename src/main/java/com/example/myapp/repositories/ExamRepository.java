package com.example.myapp.repositories;


import com.example.myapp.models.Exam;
import org.springframework.data.repository.CrudRepository;

public interface ExamRepository
        extends CrudRepository<Exam, Integer> {

}
