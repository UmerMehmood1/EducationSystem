package com.appsqueeze.springserver.model.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class CourseDao {

    @Autowired
    private CourseRepository repository;

    public Course save(Course course) {
        return repository.save(course);
    }

    public List<Course> getAllCourse() {
        List<Course> courses = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(courses::add);
        return courses;
    }

    public void delete(long courseId) {
        repository.deleteById(courseId);
    }
}
