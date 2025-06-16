package com.snakeandladder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Player {
    private String id;
    private String playerName;

    public Player(String id, String playerName) {
        this.id = id;
        this.playerName = playerName;
    }
}