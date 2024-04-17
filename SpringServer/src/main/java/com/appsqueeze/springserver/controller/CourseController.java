package com.appsqueeze.springserver.controller;

import com.appsqueeze.springserver.model.course.Course;
import com.appsqueeze.springserver.model.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/course/get-all")
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/course/save")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @PutMapping("/course/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course course = optionalCourse.get();
        course.setName(courseDetails.getName());
        course.setApproxTimeHour(courseDetails.getApproxTimeHour());
        course.setAvailableSeats(courseDetails.getAvailableSeats());
        course.setInstructorName(courseDetails.getInstructorName());
        course.setSubtitles(courseDetails.getSubtitles());
        course.setScheduleDateTime(courseDetails.getScheduleDateTime());
        course.setSkillsTobeGained(courseDetails.getSkillsTobeGained());

        Course updatedCourse = courseRepository.save(course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
