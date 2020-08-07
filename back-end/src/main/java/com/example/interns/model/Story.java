package com.example.interns.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stories")
public class Story {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    @NotBlank
    @Indexed(unique = true)
    private String title;

    @NotNull
    private List<String> location;

    @NotBlank
    private String content;

    @NotBlank
    private String imageSource;

    @NotBlank
    private String date;

    public Story(String title, List<String> location, String content, String imageSource, String date) {
        this.title = title;
        this.location = location;
        this.content = content;
        this.imageSource = imageSource;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLocation() {
        return location;
    }

    public String getContent() {
        return content;
    }

    public String getImageSource() {
        return imageSource;
    }

    public String getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(List<String> location) {
        this.location = location;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Story [id=" + id + ", title=" + title + ", address=" + location + ", content=" + content
                + ", imageSource=" + imageSource + ", date=" + date + "]";
    }

}