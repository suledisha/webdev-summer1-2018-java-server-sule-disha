package com.example.myapp.services;
import com.example.myapp.models.Assignment;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.AssignmentRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    LessonRepository lessonRepository;


    @GetMapping("/api/assignment")
    public List<Assignment> findAllAssignments(){
        return (List<Assignment>) assignmentRepository.findAll();
    }

    @GetMapping("/api/assignment/{aId}")
    public Assignment getAssignment(@PathVariable("aId") int aId){
        Optional<Assignment> data = assignmentRepository.findById(aId);
        if (data.isPresent()) {
            Assignment assignment = data.get();
            return assignment;
        }
        return null;
    }

    @GetMapping("/api/lesson/{lessonId}/assignment")
    public List<Assignment> findAllAssignmentsForLesson(
            @PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data =
                lessonRepository.findById(lessonId);
        List<Assignment> assignments = new ArrayList<>();
        if (data.isPresent()) {
            Lesson lesson = data.get();
            List<Widget> widgets = lesson.getWidgets();
            for (Widget widget : widgets) {
                if (widget.getWidgetType().equalsIgnoreCase("Assignment")) {
                    Optional<Assignment> assignment = assignmentRepository.findById(widget.getId());
                    if (assignment.isPresent()) {
                        assignments.add(assignment.get());
                    }
                }
            }
            return assignments;
        }
        return null;
    }


    @PostMapping("/api/lesson/{lessonId}/assignment")
    public Assignment createAssignment(@PathVariable("lessonId") int lessonId,
                           @RequestBody Assignment newAssignment) {

        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if(data.isPresent()) {
            Lesson lesson = data.get();
            newAssignment.setLesson(lesson);
            return assignmentRepository.save(newAssignment);
        }
        return null;
    }

    @PutMapping("/api/assignment/{aId}")
    public Assignment updateAssignment(
            @PathVariable("aId") int aId, @RequestBody Assignment newAssign) {

        Optional<Assignment> data = assignmentRepository.findById(aId);
        if(data.isPresent()) {
            //System.out.println("IN UPDATE EXAM "+examId);
            Assignment assignment= data.get();
            //assignment.setName(newAssign.getName());
            assignment.setTitle(newAssign.getTitle());
            assignment.setDescription(newAssign.getDescription());
            assignment.setPoints(newAssign.getPoints());
            assignmentRepository.save(assignment);
            return assignment;
        }
        return null;

    }

    @DeleteMapping("/api/assignment/{aId}")
    public void deleteExam(
            @PathVariable("aId")
                    int aId) {
        assignmentRepository.deleteById(aId);
    }

}
