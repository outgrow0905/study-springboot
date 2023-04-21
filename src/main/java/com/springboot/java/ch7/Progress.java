package com.springboot.java.ch7;

import java.time.Duration;

public class Progress {
    private Duration duration;
    private boolean finished;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
