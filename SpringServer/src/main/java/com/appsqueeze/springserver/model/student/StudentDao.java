package com.appsqueeze.springserver.model.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentDao {

    @Autowired
    private StudentRepository repository;

    public Student save(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(students::add);
        return students;
    }

    public void delete(long studentId) {
        repository.deleteById(studentId);
    }
    public Optional<Student> getStudentById(long studentId) {
        return repository.findById(studentId);
    }
    public Student updateStudent(Student student) {
        return repository.save(student);
    }
}
