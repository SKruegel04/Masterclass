package com.example.masterclass.domain.courses;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CourseUpdateRequest {
    @Length(min = 4)
    @NotBlank
    String title;
    @Length(min = 4)
    @NotBlank
    String description;
    @Length(min = 2)
    @NotBlank
    String category;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    Date date;
    @Min(15)
    int duration;
    @NotNull
    Long userId;

    public CourseUpdateRequest(String title, String description, String category,
                               Date date, int duration, Long userId) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.duration = duration;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
