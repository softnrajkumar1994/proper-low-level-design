package com.cricbuzz;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Innings {
    private int id;
    private Team battingTeam;
    private Team bowlingTeam;
    private List<Over> overList;

    private int totalScore = 0;
    private int wickets = 0;

    public Innings(int id, Team battingTeam, Team bowlingTeam) {
        this.id = id;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.overList = new ArrayList<>();
    }

    public void printScoreboard() {
        System.out.println("\nScoreboard - " + battingTeam.getTeamName());
        System.out.println("====================================");
        System.out.printf("%-20s %-10s %-10s\n", "Batsman", "Runs", "Status");

        Map<String, Integer> playerRuns = new HashMap<>();
        Map<String, String> playerStatus = new HashMap<>();

        for (Over over : overList) {
            for (Ball ball : over.getBalls()) {
                String name = ball.getStriker().getName();
                BallResult res = ball.getResult();
                if (!playerRuns.containsKey(name)) {
                    playerRuns.put(name, 0);
                    playerStatus.put(name, "NOT OUT");
                }
                if (res == BallResult.WICKET) {
                    playerStatus.put(name, "OUT");
                } else {
                    playerRuns.put(name, playerRuns.get(name) + res.ordinal());
                }
            }
        }

        for (String name : playerRuns.keySet()) {
            System.out.printf("%-20s %-10d %-10s\n", name, playerRuns.get(name), playerStatus.get(name));
        }

        System.out.println("------------------------------------");
        System.out.println("Total: " + totalScore + "/" + wickets);
        System.out.println("Overs: " + overList.size());
        System.out.println("====================================\n");
    }

}
