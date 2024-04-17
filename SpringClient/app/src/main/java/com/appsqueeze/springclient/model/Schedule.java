package com.appsqueeze.springclient.model;

public class Schedule {

    private Long id;
    private Course course;

    public Schedule() {
    }

    public Schedule(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", course=" + course +
                '}';
    }
}
