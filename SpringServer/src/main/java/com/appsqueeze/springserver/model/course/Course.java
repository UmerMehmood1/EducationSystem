package com.appsqueeze.springserver.model.course;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
public class Course {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Course(Long id, String name, String instructorName, String scheduleDateTime, int availableSeats, int approxTimeHour, String subtitles, String skillsTobeGained) {
        this.id = id;
        this.name = name;
        this.instructorName = instructorName;
        ScheduleDateTime = scheduleDateTime;
        AvailableSeats = availableSeats;
        this.approxTimeHour = approxTimeHour;
        this.subtitles = subtitles;
        this.skillsTobeGained = skillsTobeGained;
    }

    public Course() {

    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getScheduleDateTime() {
        return ScheduleDateTime;
    }

    public void setScheduleDateTime(String scheduleDateTime) {
        ScheduleDateTime = scheduleDateTime;
    }

    public int getAvailableSeats() {
        return AvailableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        AvailableSeats = availableSeats;
    }

    public int getApproxTimeHour() {
        return approxTimeHour;
    }

    public void setApproxTimeHour(int approxTimeHour) {
        this.approxTimeHour = approxTimeHour;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getSkillsTobeGained() {
        return skillsTobeGained;
    }

    public void setSkillsTobeGained(String skillsTobeGained) {
        this.skillsTobeGained = skillsTobeGained;
    }

    private String instructorName;
    private String ScheduleDateTime;
    private int AvailableSeats;
    private int approxTimeHour;
    private String subtitles;
    private String skillsTobeGained;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
