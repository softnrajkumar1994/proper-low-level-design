package com.battleship;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Player {
    private String playerName;
    private int xFrom;
    private int xTo;
    private List<Ship> shipList;

    public Player(String playerName) {
        this.playerName = playerName;
        this.shipList = new ArrayList<>();
    }
}
