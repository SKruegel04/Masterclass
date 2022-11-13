package com.example.masterclass.domain.courses;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CourseCreateRequest {
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
    Date date;
    @Min(15)
    int duration;

    public CourseCreateRequest(String title, String description, String category,
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
