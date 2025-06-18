package com.cricbuzz;

import java.util.*;

public class MatchService {
    public static Team play(Match match) {
        playInnings(match, match.getFirstInnings(), false);
        playInnings(match, match.getSecondInnings(), true);

        int score1 = match.getFirstInnings().getTotalScore();
        int score2 = match.getSecondInnings().getTotalScore();

        if (score1 > score2) match.setWinner(match.getFirstInnings().getBattingTeam());
        else if (score2 > score1) match.setWinner(match.getSecondInnings().getBattingTeam());
        else match.setMatchStatus(MatchStatus.DRAW);

        return match.getWinner();
    }

    public static void playInnings(Match match, Innings innings, boolean isSecondInnings) {
        int overs = match.getMatchType().getOvers();
        List<Player> batters = innings.getBattingTeam().getPlayerList();
        List<Player> bowlers = innings.getBowlingTeam().getPlayerList();

        Map<Player, Integer> bowlerOvers = new HashMap<>();
        int totalRuns = 0, wickets = 0;
        int strikerIdx = 0, nonStrikerIdx = 1;
        boolean matchOver = false;

        for (int i = 0; i < overs && !matchOver; i++) {
            Player bowler = getNextBowler(bowlerOvers, bowlers);
            Over over = new Over(i, innings, bowler);
            innings.getOverList().add(over);

            for (int j = 0; j < 6 && !matchOver; j++) {
                if (strikerIdx >= batters.size() || nonStrikerIdx >= batters.size()) break;

                Player striker = batters.get(strikerIdx);
                Player nonStriker = batters.get(nonStrikerIdx);
                Ball ball = Ball.builder().over(over).striker(striker).nonStriker(nonStriker).bowler(bowler).build();
                BallResult result = getBallResult();
                ball.setResult(result);
                over.getBalls().add(ball);

                if (result == BallResult.WICKET) {
                    wickets++;
                    strikerIdx = Math.max(strikerIdx, nonStrikerIdx) + 1;
                } else {
                    totalRuns += result.ordinal();
                    if (result.ordinal() % 2 == 1) {
                        int temp = strikerIdx;
                        strikerIdx = nonStrikerIdx;
                        nonStrikerIdx = temp;
                    }
                }

                if (isSecondInnings) {
                    int firstScore = match.getFirstInnings().getTotalScore();
                    if (totalRuns > firstScore) {
                        match.setMatchStatus(MatchStatus.END);
                        matchOver = true;
                        break;
                    } else if (totalRuns == firstScore) {
                        match.setMatchStatus(MatchStatus.DRAW);
                        matchOver = true;
                        break;
                    }
                }
            }
            bowlerOvers.put(bowler, bowlerOvers.getOrDefault(bowler, 0) + 1);
        }

        innings.setTotalScore(totalRuns);
        innings.setWickets(wickets);
    }

    private static BallResult getBallResult() {
        return BallResult.values()[new Random().nextInt(BallResult.values().length)];
    }

    private static Player getNextBowler(Map<Player, Integer> oversMap, List<Player> bowlers) {
        return bowlers.stream()
                .sorted(Comparator.comparingInt(p -> oversMap.getOrDefault(p, 0)))
                .findFirst()
                .orElseThrow();
    }
}
