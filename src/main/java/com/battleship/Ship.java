package com.battleship;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Ship {
    private String shipName;
    private List<Cell> coordinates;

    public Ship(String shipName) {
        this.shipName = shipName;
        this.coordinates = new ArrayList<>();
    }
}
