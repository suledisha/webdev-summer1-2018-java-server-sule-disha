package com.example.myapp.services;


import com.example.myapp.models.Course;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets(){
        return (List<Widget>) widgetRepository.findAll();
    }

    @PostMapping("api/widget/save")
    public void saveAllWidgets(@RequestBody List<Widget> widgets){

        widgetRepository.deleteAll();
        for(Widget widget: widgets){
            widgetRepository.save(widget);
        }

    }

    @PostMapping("/api/lesson/{lessonId}/widgets")
    public void saveAllWidgetsForLesson(
            @PathVariable("lessonId") int lessonId,
            @RequestBody List<Widget> widgets) {

        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if(data.isPresent()) {
            Lesson lesson = data.get();
            List<Widget> widgetslist=lesson.getWidgets();
            for(Widget widget: widgetslist){
                widgetRepository.deleteById(widget.getId());
            }
            for(Widget widget: widgets){
                widget.setLesson(lesson);
                widgetRepository.save(widget);
            }
        }
    }

    @GetMapping("/api/lesson/{lessonId}/widgets")
    public List<Widget> findAllWidgetsForLesson(
            @PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data =
                lessonRepository.findById(lessonId);
        if(data.isPresent()) {
            Lesson lesson = data.get();
            List<Widget> widgets= lesson.getWidgets();

            Collections.sort(widgets, new Comparator<Widget>() {
                @Override
                public int compare(Widget w1, Widget w2) {
                    return Integer.compare(w1.getWidgetOrder(), w2.getWidgetOrder());
                    //return w1.getWidgetOrder() > w2.getWidgetOrder()? 1: w1.getWidgetOrder() < w2.getWidgetOrder()? -1 : 0;
                }
            });
            return widgets;
        }
        return null;
    }





}
