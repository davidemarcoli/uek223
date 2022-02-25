package com.noseryoung.uek223.domain.utils;

public class Stopwatch {
    private final long startTime = System.currentTimeMillis();

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
}
