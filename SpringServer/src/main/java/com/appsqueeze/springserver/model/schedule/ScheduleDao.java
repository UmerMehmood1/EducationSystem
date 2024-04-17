package com.appsqueeze.springserver.model.schedule;

import com.appsqueeze.springserver.model.student.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleDao {

    @Autowired
    private ScheduleRepository repository;

    public Schedule save(Schedule schedule) {
        return repository.save(schedule);
    }

    public List<Schedule> getAllSchedule() {
        List<Schedule> schedules = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(schedules::add);
        return schedules;
    }

    public void delete(long scheduleId) {
        repository.deleteById(scheduleId);
    }
    public Optional<Schedule> getScheduleById(long studentId) {
        return repository.findById(studentId);
    }
    public Schedule updateSchedule(Schedule schedule) {
        return repository.save(schedule);
    }
}
