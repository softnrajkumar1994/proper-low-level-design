package com.moviebooking;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Seat {
    private String seatId;
    private Screen screen;

    public Seat(String seatId, Screen screen) {
        this.screen = screen;
        this.seatId = seatId;
    }

}
