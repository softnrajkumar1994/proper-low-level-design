package com.cricbuzz;

public class CricbuzzDemo {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Team team1 = new Team("India", 6, 5);
            Team team2 = new Team("Australia", 7, 4);

            Match match = new Match(MatchType.T20);

            match.setTeam1(team1);
            match.setTeam2(team2);

            Innings innings1 = new Innings(1, team1, team2);
            Innings innings2 = new Innings(2, team2, team1);

            match.setFirstInnings(innings1);
            match.setSecondInnings(innings2);

            MatchService.play(match);
            System.out.println(match.getWinner().getTeamName());
            System.out.println(match.getTeam1().getTeamName() + " " + match.getFirstInnings().getTotalScore() + " " + match.getFirstInnings().getWickets());
            System.out.println(match.getTeam2().getTeamName() + " " + match.getSecondInnings().getTotalScore() + " " + match.getSecondInnings().getWickets());
            System.out.println();

            match.getFirstInnings().printScoreboard();
            match.getSecondInnings().printScoreboard();

            Thread.sleep(1000);
        }

    }
}
