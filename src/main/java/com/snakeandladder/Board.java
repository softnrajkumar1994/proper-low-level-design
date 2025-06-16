package com.snakeandladder;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private HashMap<Player, Integer> playerPositionHashMap;
    private String boardId;
    private int maxPos;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board(String boardId, int maxPos) {
        this.boardId = boardId;
        this.maxPos = maxPos;
        playerPositionHashMap = new HashMap<>();
    }


}