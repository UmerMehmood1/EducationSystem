package com.appsqueeze.springserver.controller;

import com.appsqueeze.springserver.model.student.Student;
import com.appsqueeze.springserver.model.student.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @GetMapping("/student/get-all")
    public List<Student> getAllStudents() {
        return studentDao.getAllStudent();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentDao.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/student/save")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentDao.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping("/student/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Optional<Student> optionalStudent = studentDao.getStudentById(id);
        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student student = optionalStudent.get();
        student.setUsername(studentDetails.getUsername());
        student.setPassword(studentDetails.getPassword());
        // Update other fields as needed

        Student updatedStudent = studentDao.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentDao.delete(id);
        return ResponseEntity.noContent().build();
    }
}
