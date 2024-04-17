package com.appsqueeze.springclient.model;

public class Course {

    private Long id;
    private String name;
    private String instructorName;
    private String scheduleDateTime;
    private int availableSeats;
    private int approxTimeHour;
    private String subtitles;
    private String skillsToBeGained;

    public Course() {
    }

    public Course(String name, String instructorName, String scheduleDateTime, int availableSeats, int approxTimeHour, String subtitles, String skillsToBeGained) {
        this.name = name;
        this.instructorName = instructorName;
        this.scheduleDateTime = scheduleDateTime;
        this.availableSeats = availableSeats;
        this.approxTimeHour = approxTimeHour;
        this.subtitles = subtitles;
        this.skillsToBeGained = skillsToBeGained;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(String scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
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

    public String getSkillsToBeGained() {
        return skillsToBeGained;
    }

    public void setSkillsToBeGained(String skillsToBeGained) {
        this.skillsToBeGained = skillsToBeGained;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", scheduleDateTime='" + scheduleDateTime + '\'' +
                ", availableSeats=" + availableSeats +
                ", approxTimeHour=" + approxTimeHour +
                ", subtitles='" + subtitles + '\'' +
                ", skillsToBeGained='" + skillsToBeGained + '\'' +
                '}';
    }
}
