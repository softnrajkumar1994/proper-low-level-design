package com.cricbuzz;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bowler extends Player {
    public Bowler(String name) {
        super(name);
    }
}
