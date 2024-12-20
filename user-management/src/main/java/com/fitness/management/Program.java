package com.fitness.management;

import java.io.Serializable;
import java.util.List;

public class Program implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String duration;
    private String difficulty;
    private String goals;
    private double price;
    private String schedule;
    private List<String> videos;
    private List<String> documents;
    private int enrollment; 
    private String progressSummary;
    private boolean isActive; 

    public Program(String title, String duration, String difficulty, String goals, double price, String schedule, List<String> videos, List<String> documents) {
        this.title = title;
        this.duration = duration;
        this.difficulty = difficulty;
        this.goals = goals;
        this.price = price;
        this.schedule = schedule;
        this.videos = videos;
        this.documents = documents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }
    @Override
    public String toString() {
        return "Program{" +
                "title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", goals='" + goals + '\'' +
                ", price=" + price +
                ", schedule='" + schedule + '\'' +
                ", videos=" + videos +
                ", documents=" + documents +
                '}';
    }
    
    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public String getProgressSummary() {
        return progressSummary;
    }

    public void setProgressSummary(String progressSummary) {
        this.progressSummary = progressSummary;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
