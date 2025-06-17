package com.cricbuzz;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ball {
    private Over over;
    private Player striker;
    private Player nonStriker;
    private Player bowler;
    private BallResult result;
}
