package com.example.masterclass.domain.courses;

import com.example.masterclass.domain.users.UserListResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseDetailResponse {
    Long id;
    String title;
    String description;
    String category;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    Date date;
    int duration;
    UserListResponse user;

    public CourseDetailResponse(Long id, String title, String description, String category,
                                Date date, int duration, UserListResponse user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.duration = duration;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserListResponse getUser() {
        return user;
    }

    public void setUser(UserListResponse user) {
        this.user = user;
    }
}
