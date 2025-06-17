package com.cricbuzz;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Batsman extends Player {
    public Batsman(String name) {
        super(name);
    }
}
