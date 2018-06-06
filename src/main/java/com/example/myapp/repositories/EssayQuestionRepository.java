package com.example.myapp.repositories;

import com.example.myapp.models.EssayQuestion;
import org.springframework.data.repository.CrudRepository;

public interface EssayQuestionRepository extends CrudRepository<EssayQuestion, Integer> {
}
