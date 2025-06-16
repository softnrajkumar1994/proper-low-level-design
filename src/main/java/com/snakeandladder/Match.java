package com.snakeandladder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class Match {
    private String matchId;
    private List<Player> playerList;
    private Player winner = null;
    private MatchStatus matchStatus;
    private Board board;
    private Dice dice;

    public Match(String matchId) {
        this.matchId = matchId;
        this.matchStatus = MatchStatus.NOT_STARTED;
    }
}