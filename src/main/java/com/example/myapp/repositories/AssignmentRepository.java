package com.example.myapp.repositories;

import com.example.myapp.models.Assignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
}
