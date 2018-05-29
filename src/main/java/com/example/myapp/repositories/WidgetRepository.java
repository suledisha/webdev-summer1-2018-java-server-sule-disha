package com.example.myapp.repositories;


import com.example.myapp.models.Widget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface WidgetRepository extends CrudRepository<Widget, Integer>  {
}
