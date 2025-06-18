package com.moviebooking;

import lombok.Getter;

@Getter
public enum ShowTiming {
    AM10_1PM(10, 13), AM11_2PM(11, 14), PM3_6PM(15, 18);
    private int startTime;
    private int endTime;

    ShowTiming(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
