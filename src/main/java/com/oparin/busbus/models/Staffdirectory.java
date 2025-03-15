package com.oparin.busbus.models;

public class Staffdirectory {
    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String job;
    private String schedule;

    public Staffdirectory(String lastName, String firstName, String email, String job, String schedule) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.job = job;
        this.schedule = schedule;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}