package com.springboot.java.ch7;

import java.util.Optional;

public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Progress getProgress() {
        return progress;
    }

    public Optional<Progress> getOptionalProgress() {
//        return progress == null ? null : Optional.ofNullable(progress); // don't use this way
        return Optional.ofNullable(progress);
    }

    // don't use this way
//    public void setOptionalProgress(Optional<Progress> progress) {
//        progress.ifPresent(p -> this.progress = p);
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "OnlineClass{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", closed=" + closed +
                '}';
    }
}
