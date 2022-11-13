package com.example.masterclass.domain.courses;

import java.util.Date;

public class CourseUpdateRequest {
    String title;
    String description;
    String category;
    Date date;
    int duration;

    public CourseUpdateRequest(String title, String description, String category,
                               Date date, int duration) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
