package com.cricbuzz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Match {
    private Innings firstInnings;
    private Innings secondInnings;
    private MatchStatus matchStatus;
    private Team team1;
    private Team team2;
    private Team winner;
    private MatchType matchType;

    public Match(MatchType matchType) {
        this.matchType = matchType;
        this.matchStatus = MatchStatus.NOT_STARTED;
    }
}
