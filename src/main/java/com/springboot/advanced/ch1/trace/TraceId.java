package com.springboot.advanced.ch1.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {
    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextTraceId() {
        return new TraceId(id, level++);
    }

    public TraceId createPreviousTraceId() {
        return new TraceId(id, level--);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }
}
