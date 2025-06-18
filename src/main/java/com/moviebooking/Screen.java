package com.moviebooking;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Screen {
    private String screenName;
    private Theatre theatre;
    private Movie movie;
    private List<Seat> seatList;

    public Screen(String screenName, Theatre theatre, int seatCount) {
        this.screenName = screenName;
        this.theatre = theatre;
        this.seatList = new ArrayList<>();
        for (int i = 0; i < seatCount; i++) {
            this.seatList.add(new Seat("S_" + i, this));
        }
    }
}
