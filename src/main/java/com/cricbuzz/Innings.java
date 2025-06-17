package com.cricbuzz;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Innings {
    private Team battingTeam;
    private Team bowlingTeam;
    private int id;
}
