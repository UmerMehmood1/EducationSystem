package com.appsqueeze.springserver.controller;

import com.appsqueeze.springserver.model.schedule.Schedule;
import com.appsqueeze.springserver.model.schedule.ScheduleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleDao scheduleDao;

    @GetMapping("/schedules/get-all")
    public List<Schedule> getAllSchedules() {
        return scheduleDao.getAllSchedule();
    }
    @PostMapping("/schedule/save")
    public ResponseEntity<Schedule> save(@RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleDao.save(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }
    @GetMapping("/schedule/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Optional<Schedule> schedule = scheduleDao.getScheduleById(id);
        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/schedule/update/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule scheduleDetails) {
        Optional<Schedule> optionalSchedule = scheduleDao.getScheduleById(id);
        if (optionalSchedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Schedule schedule = optionalSchedule.get();
        schedule.setCourse(scheduleDetails.getCourse());

        Schedule updatedSchedule = scheduleDao.save(schedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/schedule/delete/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleDao.delete(id);
        return ResponseEntity.noContent().build();
    }
}
