package com.example.myapp.repositories;

import com.example.myapp.models.MultipleChoiceQuestion;
import org.springframework.data.repository.CrudRepository;

public interface MultipleChoiceQuestionRepository extends CrudRepository<MultipleChoiceQuestion, Integer> {

}