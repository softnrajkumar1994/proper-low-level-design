package com.cricbuzz;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Over {
    private int overId;
    private Innings innings;
    private Player bowler;
    private List<Ball> balls;

    public Over(int overId, Innings innings, Player bowler) {
        this.overId = overId;
        this.innings = innings;
        this.bowler = bowler;
        this.balls = new ArrayList<>();
    }
}
