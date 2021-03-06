package com.noseryoung.uek223.domain.utils;

import java.util.ArrayList;
import java.util.List;

public class MultiStopwatch {
    private long startTime = 0;
    private ArrayList<Long> elapsedTimes = new ArrayList<>();

    public void start() {
        elapsedTimes.clear();
        startTime = System.currentTimeMillis();
    }

    public void newTime() {
        elapsedTimes.add(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
    }

    public List<Long> getElapsedTimes() {
        return elapsedTimes;
    }

    public long getAverageTime() {
        long total = 0;
        for (long time : elapsedTimes) {
            total += time;
        }
        return total / elapsedTimes.size();
    }
}
