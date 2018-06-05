package com.example.myapp.repositories;

import com.example.myapp.models.TrueFalseQuestion;
import org.springframework.data.repository.CrudRepository;

public interface TrueFalseQuestionRepository
        extends CrudRepository<TrueFalseQuestion, Integer> {

}