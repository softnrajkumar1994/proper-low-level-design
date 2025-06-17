package com.cricbuzz;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Team {
    private String teamName;
    private List<Player> playerList;

    public Team(String name, int batsman, int bowler) {
        this.teamName = name;
        int totalPlayers = batsman + bowler;
        if (totalPlayers != 11) {
            System.out.println("11 players is required ");
            throw new RuntimeException("11 players is required");
        }
        playerList = new ArrayList<>();
        for (int i = 0; i < batsman; i++) {
            playerList.add(new Batsman("" + teamName + "_batsman_" + i));
        }
        for (int i = 0; i < bowler; i++) {
            playerList.add(new Bowler("" + teamName + "_bowler_" + i));
        }

    }
}
