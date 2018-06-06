package com.example.myapp.repositories;

import com.example.myapp.models.FillInTheBlankQuestion;
import org.springframework.data.repository.CrudRepository;

public interface FillInTheBlankRepository extends CrudRepository<FillInTheBlankQuestion, Integer>  {
}
