package com.moviebooking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Show {
    private String showId;
    private ShowTiming showTimings;
    private Screen screen;
}
