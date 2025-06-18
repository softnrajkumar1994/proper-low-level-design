package com.moviebooking;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Screen {
    private String screenName;
    private Theatre theatre;
    private Movie movie;
    private List<Seat> seatList;
}
